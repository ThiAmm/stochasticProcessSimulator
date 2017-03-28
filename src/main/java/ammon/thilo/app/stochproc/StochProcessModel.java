package ammon.thilo.app.stochproc;

import java.util.TreeMap;
import java.util.ArrayList;

class StochProcessModel {
    int dimension;
    ArrayList<RealisedValue> realisations;

    StochProcessModel(int dimension) {
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
        realisations.add(new RealisedValue(t, y));
    }

    public ArrayList<RealisedValue> getRealisations(){
        return realisations;
    }
}
