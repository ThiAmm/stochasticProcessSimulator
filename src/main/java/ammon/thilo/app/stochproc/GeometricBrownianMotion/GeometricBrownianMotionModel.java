package ammon.thilo.app.stochproc.GeometricBrownianMotion;

import ammon.thilo.app.stochproc.StochasticProcessModel;

/**
 * Created by thilo on 12.04.17.
 */
public class GeometricBrownianMotionModel extends StochasticProcessModel {
    private double mu;
    private double sigma;

    public GeometricBrownianMotionModel(int dimension) {
        super(dimension);
    }

    public void setMu(double mu){
        this.mu = mu;
    }

    public double getMu(){
        return mu;
    }

    public void setSigma(double sigma){
        this.sigma = sigma;
    }

    public double getSigma(){
        return sigma;
    }
}
