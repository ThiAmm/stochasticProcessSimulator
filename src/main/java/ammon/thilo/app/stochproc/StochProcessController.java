package ammon.thilo.app.stochproc;

import ammon.thilo.app.HelperFunctions.ArrayHelperFunctions;
import ammon.thilo.app.ProbabilityFactory.RandomNumberGenerator;

import java.util.ArrayList;

/**
 * Created by thilo on 07.09.16.
 */
public abstract class StochProcessController {
    ArrayList<RealisedValue> newRealisedValues;

    public StochProcessController() {
        newRealisedValues = new ArrayList<RealisedValue>();
    }

    abstract StochasticProcessModel getModel();

    public abstract RealisedValue simulateNextPoint(double time);

    public void createNewRealisedValue(double time){
        RealisedValue newRealisation = simulateNextPoint(time);
        getModel().addRealisation(newRealisation);
        newRealisedValues.add(newRealisation);
    }

    public ArrayList<RealisedValue> getRealisedValues(){
        return getModel().getRealisations();
    }

    public RealisedValue getCurrentRealisation(){
        if(getRealisedValues().isEmpty()){
            return null;
        }else{
            return getRealisedValues().get(getRealisedValues().size()-1);
        }
    }

    public abstract String getTypeNameOfStochasticProcess();

    abstract boolean IsRealValuedProcess();

    public void setId(int id){
        getModel().setId(id);
    }

    public int getId(){
        return getModel().getId();
    }

    ArrayList<RealisedValue> getNewRealisedValues(){
        return newRealisedValues;
    }

    public void clearNewRealisedValues(){
        newRealisedValues.clear();
    }

}
