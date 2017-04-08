package ammon.thilo.app.stochproc;

import javafx.util.Pair;

import java.util.ArrayList;

class RandomWalkModel extends StochasticProcessModel {
    ArrayList<Pair<Double,Double>> jumpProbPairs;
    int Id;
    RandomWalkModel(int dimension) {
        super(dimension);
    }

    public void setJumpProbPairs(ArrayList<Pair<Double,Double>> jumpProbPairs) {
        this.jumpProbPairs = jumpProbPairs;
    }

    public ArrayList<Pair<Double,Double>> getJumpProbPairs(){
        return jumpProbPairs;
    }
}
