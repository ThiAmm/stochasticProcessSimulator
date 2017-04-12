package ammon.thilo.app.stochproc.BrownianMotion;

import ammon.thilo.app.stochproc.StochasticProcessModel;

/**
 * Created by thilo on 11.04.17.
 */
public class BrownianMotionModel extends StochasticProcessModel {
    private double mu;
    private double sigma;

    BrownianMotionModel(int dimension){
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
