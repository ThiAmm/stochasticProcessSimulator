package ammon.thilo.app.stochproc;

import ammon.thilo.app.ProbabilityFactory.RandomNumberGenerator;

/**
 * Created by thilo on 11.04.17.
 */
public class BrownianMotionController extends MarkovProcessController{
    BrownianMotionModel brownianMotionModel = null;

    BrownianMotionController(){
        brownianMotionModel = new BrownianMotionModel(1);
    }

    StochasticProcessModel getModel() {
        return brownianMotionModel;
    }

    public RealisedValue simulateNextPoint(double time) {
        if(getRealisedValues().isEmpty()){
            return new RealisedValue(0,0);
        }
        RealisedValue realisedValue = brownianMotionModel.getLatestRealisedValue();
        double realisationOfNormalRV = RandomNumberGenerator.generateNormalRandomNumbersBoxMuller()[0];
        return new RealisedValue(
                time,
                brownianMotionModel.getMu()*time + brownianMotionModel.getSigma()*Math.sqrt(time-realisedValue.getTime())* realisationOfNormalRV);
    }

    public String getTypeNameOfStochasticProcess() {
        return "Brownian Motion";
    }

    boolean IsRealValuedProcess() {
        return true;
    }

    public void setMu(double mu) {
        brownianMotionModel.setMu(mu);
    }

    public void setSigma(double sigma){
        brownianMotionModel.setSigma(sigma);
    }
}
