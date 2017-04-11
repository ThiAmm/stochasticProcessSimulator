package ammon.thilo.app.stochproc;

import ammon.thilo.app.ProbabilityFactory.RandomNumberGenerator;

/**
 * Created by thilo on 07.04.17.
 */
public class PoissonProcessController extends MarkovProcessController {
    PoissonProcessModel poissonProcessModel = null;

    PoissonProcessController(){
        poissonProcessModel = new PoissonProcessModel(1);
    }

    StochasticProcessModel getModel() {
        return poissonProcessModel;
    }

    public RealisedValue simulateNextPoint(double time) {
        RealisedValue currentRealisation = getCurrentRealisation();
        if(currentRealisation == null){
            return new RealisedValue(0,0);
        }
        if(time!=0){
            poissonProcessModel.addRealisation(time-Double.MIN_VALUE, getCurrentRealisation().getValue());
            newRealisedValues.add(new RealisedValue(time-Double.MIN_VALUE, getCurrentRealisation().getValue()));
        }
        double timeDifference = time - currentRealisation.getTime();
        double newValue = currentRealisation.getValue().get(0) + RandomNumberGenerator.generatePoissonDistributedRandomVariable(timeDifference*1.0/getLambda());
        return new RealisedValue(time,newValue);
    }

    public void setLambda(double lambda){
        poissonProcessModel.setLambda(lambda);
    }

    public double getLambda(){
        return poissonProcessModel.getLambda();
    }

    public String getTypeNameOfStochasticProcess() {
        return "Poisson process";
    }

    boolean IsRealValuedProcess() {
        return true;
    }
}
