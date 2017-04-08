package ammon.thilo.app.stochproc;

import ammon.thilo.app.HelperFunctions.ArrayHelperFunctions;
import ammon.thilo.app.ProbabilityFactory.RandomNumberGenerator;
import javafx.util.Pair;

import java.util.ArrayList;

import static java.lang.Double.compare;

/**
 * Created by thilo on 07.09.16.
 */
public class RandomWalkController extends StochProcessController{
    RandomWalkModel rm = null;

    public RandomWalkController() {
        super();
        rm = new RandomWalkModel(1);
    }

    StochasticProcessModel getModel() {
        return rm;
    }

    public RealisedValue simulateNextPoint(double time) {
        if(time != 0){
            rm.addRealisation(time-Double.MIN_VALUE, getCurrentRealisation().getValue());
            newRealisedValues.add(new RealisedValue(time-Double.MIN_VALUE, getCurrentRealisation().getValue()));
        }else{
            return new RealisedValue(time,0);
        }

        double jump = RandomNumberGenerator.generateDiscreteFiniteRandomVariable(
                rm.getJumpProbPairs());

        return new RealisedValue(time, getCurrentRealisation().getValue().get(0) + jump);
    }

    public void setJumpsProbsPair(ArrayList<Double> jumps, ArrayList<Double> probabilities) {
        ArrayList<Pair<Double,Double>> jumpProbPairs = new ArrayList<Pair<Double,Double>>();
        for(int i = 0; i<jumps.size(); i++){
            Pair<Double,Double> jumpProbPair = new Pair<Double,Double>(jumps.get(i),probabilities.get(i));
            jumpProbPairs.add(jumpProbPair);
        }
        rm.setJumpProbPairs(jumpProbPairs);
    }

    public void validateJumpProbsPairs(ArrayList<Double> probabilities){
        double prob = 0;
        for(Double p : probabilities){
            prob = prob + p;
        }
        if(compare(prob,1)==0){
            throw new IllegalArgumentException("Probabilities have to sum up to one");
        }
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
}
