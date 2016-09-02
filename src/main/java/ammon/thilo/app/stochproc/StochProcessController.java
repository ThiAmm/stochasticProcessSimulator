package ammon.thilo.app.stochproc;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;

public class StochProcessController{
  StochProcessView2D spview = null;
  StochProcessModel spmodel = null;

  RandomWalkFrame rndwlkFrame = null;
  
  public StochProcessController(int dimension){
    spview = new StochProcessView2D(new NumberAxis(),new NumberAxis());
    spmodel = new StochProcessModel(dimension);
  
    rndwlkFrame = new RandomWalkFrame();
  }
  
  public LineChart getCoorSys(){
    return spview;
  }

  public void createMarkovProcess(){
    spmodel.createMarkovProcess(2);//ToDo: User decide
  }

  public void showRandomWalkFrame(){
    rndwlkFrame.show();
  }
}
