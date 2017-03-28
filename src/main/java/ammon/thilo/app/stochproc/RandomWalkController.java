package ammon.thilo.app.stochproc;

import ammon.thilo.app.HelperFunctions.ArrayHelperFunctions;
import ammon.thilo.app.ProbabilityFactory.RandomNumberGenerator;
import javafx.util.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

import static java.lang.Double.compare;

/**
 * Created by thilo on 07.09.16.
 */
public class RandomWalkController implements StochProcessController{
    RandomWalkModel rm = null;
    RandomWalkFrame rmview = null;
    double currentValue = 0;

    public RandomWalkController() {
        super();
        rm = new RandomWalkModel(1);
        rmview = new RandomWalkFrame(this);
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
        double val = RandomNumberGenerator.generateDiscreteFiniteRandomVariable(
                rm.getJumpProbPairs());
        currentValue = currentValue + val;
        rm.addRealisation(time, ArrayHelperFunctions.createArrayListFromDouble(currentValue));
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
}
