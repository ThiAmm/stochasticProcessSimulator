package stochproc;

class RandomWalkModel extends MarkovProcessModel{
  Kernel k;
  RandomWalkModel(Kernel k){
    this.k = k;
  }
}
