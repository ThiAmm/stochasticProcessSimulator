package ammon.thilo.app.stochproc;

import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;

import java.util.HashMap;

class StochProcessView2D extends LineChart{
  
  StochProcessView2D(Axis x, Axis y){
    super(x,y);
  }

  public void addDataSeries(String name, HashMap<Double,Double> pnts){
    XYChart.Series series = new XYChart.Series();
    series.setName(name);

    for(double xval : pnts.keySet()){
      double yval = pnts.get(xval);
      series.getData().add(new XYChart.Data(xval,yval));
    }
    this.getData().addAll(series);  
  }
}
