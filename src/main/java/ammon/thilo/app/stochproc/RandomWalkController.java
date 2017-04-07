package ammon.thilo.app.stochproc;

import ammon.thilo.app.HelperFunctions.ArrayHelperFunctions;
import ammon.thilo.app.ProbabilityFactory.RandomNumberGenerator;
import javafx.util.Pair;

import java.util.ArrayList;

import static java.lang.Double.compare;

/**
 * Created by thilo on 07.09.16.
 */
public class RandomWalkController implements StochProcessController{
    RandomWalkModel rm = null;
    double currentValue = 0;
    ArrayList<RealisedValue> newRealisedValues;

    public RandomWalkController() {
        super();
        rm = new RandomWalkModel(1);
        newRealisedValues = new ArrayList<RealisedValue>();
    }

    public void setJumpsProbsPair(ArrayList<Double> jumps, ArrayList<Double> probs) {
        ArrayList<Pair<Double,Double>> jumpProbPairs = new ArrayList<Pair<Double,Double>>();
        for(int i = 0; i<jumps.size(); i++){
            Pair<Double,Double> jumpProbPair = new Pair<Double,Double>(jumps.get(i),probs.get(i));
            jumpProbPairs.add(jumpProbPair);
        }
        rm.setJumpProbPairs(jumpProbPairs);
    }

    public void validateJumpProbsPairs(ArrayList<Double> probs){
        double prob = 0;
        for(Double p : probs){
            prob = prob + p;
        }
        if(compare(prob,1)==0){
            throw new IllegalArgumentException("Probabilities have to sum up to one");
        }
    }

    public void simulateNextPoint(double time){
        if(time != 0){
            rm.addRealisation(time-Double.MIN_VALUE, ArrayHelperFunctions.createArrayListFromDouble(currentValue));
            newRealisedValues.add(new RealisedValue(time-Double.MIN_VALUE, ArrayHelperFunctions.createArrayListFromDouble(currentValue)));
        }

        double jump = RandomNumberGenerator.generateDiscreteFiniteRandomVariable(
                rm.getJumpProbPairs());
        currentValue = currentValue + jump;

        rm.addRealisation(time, ArrayHelperFunctions.createArrayListFromDouble(currentValue));
        newRealisedValues.add(new RealisedValue(time, ArrayHelperFunctions.createArrayListFromDouble(currentValue)));
    }

    public void clearNewRealisedValues(){
        newRealisedValues.clear();
    }

    public ArrayList<RealisedValue> getRealisedValues(){
        return rm.getRealisations();
    }

    public String getTypeNameOfStochasticProcess(){
        return "Random Walk";
    }

    public boolean IsRealValuedProcess() {
        for(RealisedValue realisedValue : rm.getRealisations()){
            if(realisedValue.getValue().size()>1){
                return false;
            }
        }
        return true;
    }

    public void setId(int id){
        rm.setId(id);
    }

    public int getId(){
        return rm.getId();
    }

    public ArrayList<RealisedValue> getNewRealisedValues() {
        return newRealisedValues;
    }
}
