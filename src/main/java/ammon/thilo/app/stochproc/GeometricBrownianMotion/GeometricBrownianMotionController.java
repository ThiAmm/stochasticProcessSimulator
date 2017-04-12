package ammon.thilo.app.stochproc.GeometricBrownianMotion;

import ammon.thilo.app.stochproc.BrownianMotion.BrownianMotionController;
import ammon.thilo.app.stochproc.RealisedValue;
import ammon.thilo.app.stochproc.StochProcessController;
import ammon.thilo.app.stochproc.StochasticProcessModel;

/**
 * Created by thilo on 12.04.17.
 */
public class GeometricBrownianMotionController extends StochProcessController {
    GeometricBrownianMotionModel geometricBrownianMotionModel = null;
    BrownianMotionController brownianMotionController = null;
    GeometricBrownianMotionController(){
        geometricBrownianMotionModel = new GeometricBrownianMotionModel(1);
        brownianMotionController = new BrownianMotionController();
        brownianMotionController.setMu(0);
        brownianMotionController.setSigma(1);
    }
    protected StochasticProcessModel getModel() {
        return geometricBrownianMotionModel;
    }

    public RealisedValue simulateNextPoint(double time) {
        RealisedValue realisedValue = brownianMotionController.createNewRealisedValue(time);
        double mu = geometricBrownianMotionModel.getMu();
        double sigma = geometricBrownianMotionModel.getSigma();
        return new RealisedValue(time, Math.exp((mu-Math.pow(sigma,2)/2)*time+sigma*realisedValue.getValue().get(0)));
    }

    public String getTypeNameOfStochasticProcess() {
        return "Geometric brownian motion";
    }

    public boolean IsRealValuedProcess() {
        return true;
    }

    public void setMu(double mu) {
        geometricBrownianMotionModel.setMu(mu);
    }

    public void setSigma(double sigma){
        geometricBrownianMotionModel.setSigma(sigma);
    }
}
