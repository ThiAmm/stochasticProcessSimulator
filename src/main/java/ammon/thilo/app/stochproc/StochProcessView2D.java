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

  public void add2DDataSeries(String name, ArrayList<RealisedValue> realisedValues) {
      final XYChart.Series series = new XYChart.Series();
      series.setName(name);

      for (int i = 0; i < realisedValues.size(); i++) {
          series.getData().add(new XYChart.Data(realisedValues.get(i).getTime(), realisedValues.get(i).getValue().get(0)));
      }
      final ObservableList<Series<Number, Number>> data = this.getData();
      Platform.runLater(new Runnable() {
          public void run() {
              data.addAll(series);
          }
      });
  }

  public void update2DDataByName(String name, double time, double value){
      ObservableList<Series<Number, Number>> data = this.getData();
      for(Series<Number,Number> series : data){
          if(series.getName().equals(name)){
              series.getData().add(new XYChart.Data<Number,Number>(time,value));
          }
      }
  }

  public String[] getChartNames(){
      String[] ret = new String[this.getData().size()];
      int i = 0;
      for(Series<Number,Number> series : this.getData()){
          ret[i] = series.getName();
          i++;
      }
      return ret;
  }






}
