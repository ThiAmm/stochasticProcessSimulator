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
    RandomWalkFrame rmview = null;
    double currentValue = 0;
    ArrayList<Pair<Double,ArrayList<Double>>> newRealisedValues;

    public RandomWalkController() {
        super();
        rm = new RandomWalkModel(1);
        rmview = new RandomWalkFrame(this);
        newRealisedValues = new ArrayList<Pair<Double,ArrayList<Double>>>();
    }

    public void createRandomWalk(){
        rmview.show();
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
        newRealisedValues.clear();
        if(time != 0){
            rm.addRealisation(time-Double.MIN_VALUE, ArrayHelperFunctions.createArrayListFromDouble(currentValue));
            newRealisedValues.add(new Pair(time-Double.MIN_VALUE, ArrayHelperFunctions.createArrayListFromDouble(currentValue)));
        }

        double jump = RandomNumberGenerator.generateDiscreteFiniteRandomVariable(
                rm.getJumpProbPairs());
        currentValue = currentValue + jump;

        rm.addRealisation(time, ArrayHelperFunctions.createArrayListFromDouble(currentValue));
        newRealisedValues.add(new Pair(time, ArrayHelperFunctions.createArrayListFromDouble(currentValue)));
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

    public ArrayList<Pair<Double, ArrayList<Double>>> getNewRealisedValues() {
        return newRealisedValues;
    }
}
