package ammon.thilo.app.stochproc;

import javafx.util.Pair;

import java.util.ArrayList;

class RandomWalkModel extends StochProcessModel{
    int dimension;
    ArrayList<Pair<Double,Double>> jumpProbPairs;
    RandomWalkModel(int dimension) {
        super(dimension);
        this.dimension = dimension;
    }

    public void setJumpProbPairs(ArrayList<Pair<Double,Double>> jumpProbPairs) {
        this.jumpProbPairs = jumpProbPairs;
    }

    public ArrayList<Pair<Double,Double>> getJumpProbPairs(){
        return jumpProbPairs;
    }
}
