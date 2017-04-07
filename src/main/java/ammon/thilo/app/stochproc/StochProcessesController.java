package ammon.thilo.app.stochproc;

import javafx.application.Platform;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class StochProcessesController {
    StochasticProcessView2D spview = null;
    StochasticProcessesModel spsm = null;
    ArrayList<StochProcessController> stochProcCtrls = null;

    public StochProcessesController() {
        spview = new StochasticProcessView2D(new NumberAxis(), new NumberAxis());
        stochProcCtrls = new ArrayList<StochProcessController>();
    }

    public LineChart getCoorSys() {
        spview.setTitle("Realisation of added stochastic processes");
        return spview;
    }

    public void addProcess(StochProcessController randContr) {
        stochProcCtrls.add(randContr);
    }

    public ArrayList<StochProcessController> getStochProcCtrls(){
        return stochProcCtrls;
    }

    public void updateProcessesView(double time){
        if(time==0) {
            int id = 0;
            for (StochProcessController spCtrl : stochProcCtrls) {
                spCtrl.setId(id);
                id++;
                if (spCtrl.IsRealValuedProcess())
                    spview.add2DDataSeries(spCtrl.getTypeNameOfStochasticProcess() + "_" + spCtrl.getId(),
                            spCtrl.getRealisedValues()
                    );
            }
        }else {
            Platform.runLater(new Runnable() {
                public void run() {
                    for (final XYChart.Series series : spview.getData()) {
                        String id = series.getName().split("_", 2)[1];
                        for (StochProcessController stochasticProcessController : stochProcCtrls) {
                            if (stochasticProcessController.getId() == Integer.parseInt(id)) {
                                for (final RealisedValue newRealisedValue : stochasticProcessController.getNewRealisedValues()) {
                                    series.getData().add(
                                            new XYChart.Data<Number, Number>(newRealisedValue.getTime(), newRealisedValue.getValue().get(0))
                                    );
                                }
                                stochasticProcessController.clearNewRealisedValues();
                            }
                        }
                    }
                }
            });
        }
    }
}
