package ammon.thilo.app.stochproc;

import java.util.TreeMap;
import java.util.ArrayList;

abstract class StochasticProcessModel {
    int dimension;
    ArrayList<RealisedValue> realisations = null;
    int Id;
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

    public void addRealisation(RealisedValue realisedValue){
        realisations.add(realisedValue);
    }

    public ArrayList<RealisedValue> getRealisations(){
        return realisations;
    }

    public RealisedValue getLatestRealisedValue(){
        if(getRealisations().isEmpty()){
            return null;
        }else{
            return getRealisations().get(getRealisations().size()-1);
        }
    }

    public int getId(){
        return Id;
    }

    public void setId(int Id){
        this.Id = Id;
    }

}
