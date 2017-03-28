package ammon.thilo.app.stochproc;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;

import java.util.ArrayList;
import java.util.HashMap;

class StochProcessView2D extends LineChart<Number,Number>{
  
  StochProcessView2D(NumberAxis x, NumberAxis y){
    super(x,y);
  }

  public void add2DDataSeries(String name, ArrayList<RealisedValue> realisedValues){
    final XYChart.Series series = new XYChart.Series();
    series.setName(name);

    for(int i = 0; i< realisedValues.size(); i++) {
      series.getData().add(new XYChart.Data(realisedValues.get(i).getTime(), realisedValues.get(i).getValue().get(0)));
      if (i < realisedValues.size()-1) {
        series.getData().add(new XYChart.Data(realisedValues.get(i+1).getTime() - Double.MIN_VALUE, realisedValues.get(i).getValue().get(0)));
      }
    }
    final ObservableList<Series<Number,Number>> data = this.getData();
    Platform.runLater(new Runnable() {
      public void run() {
        data.addAll(series);
      }
    });
  }
}
