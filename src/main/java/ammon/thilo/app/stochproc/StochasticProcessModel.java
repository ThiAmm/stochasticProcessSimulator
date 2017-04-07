package ammon.thilo.app.stochproc;

import java.util.TreeMap;
import java.util.ArrayList;

class StochasticProcessModel {
    int dimension;
    ArrayList<RealisedValue> realisations;

    StochasticProcessModel(int dimension) {
        realisations = new ArrayList<RealisedValue>();
        this.dimension = dimension;
    }

    public int getNmbOfRealisations() {
        return realisations.size();
    }

    public void addRealisation(double t, ArrayList<Double> y) {
        if (dimension != y.size()) {
            throw new IllegalArgumentException("Dimension mismatch");
        }
        RealisedValue realisedValue = new RealisedValue(t, y);
        realisations.add(realisedValue);
    }

    public ArrayList<RealisedValue> getRealisations(){
        return realisations;
    }
}
