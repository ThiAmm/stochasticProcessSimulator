package ammon.thilo.app.stochproc;

/**
 * Created by thilo on 07.04.17.
 */
public class PoissonProcessModel extends StochasticProcessModel{
    double lambda;

    PoissonProcessModel(int dimension) {
        super(dimension);
    }

    public void setLambda(double lambda){
        this.lambda = lambda;
    }

    public double getLambda(){
        return lambda;
    }
}
