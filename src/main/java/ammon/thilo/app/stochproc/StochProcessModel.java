package stochproc;

import java.util.TreeMap;
import java.util.ArrayList;

class StochProcessModel{
  int dimension;
  TreeMap<Double,ArrayList<Double>> realisations;

  /**
   * @param dimension The dimension of the range 
   *                  space of the stochastic process
   **/
  StochProcessModel(int dimension) {
    realisations = new TreeMap<Double,ArrayList<Double>>();
    this.dimension = dimension; 
  }

  /**
   * The number of realisations of the path of the process.
   **/
  public int getNmbOfRealisations() {
    return realisations.size();
  }

  /**
   * Adds y to the list of realisations and t to the list of timeRealisations
   **/
  private void addRealisation(double t, ArrayList<Double> y) {
    if(!realisations.isEmpty() && realisations.lastKey()<=t){
      throw new IllegalArgumentException("Cannot add new realisation: time value smaller than existing time value");
    }
    if(dimension != y.size()){
      throw new IllegalArgumentException("Dimension missmatch");
    }
    realisations.put(t,y);
  }

  ArrayList<Double> getRealisationAtTime(double t) {
    return realisations.get(t);
  }
}
