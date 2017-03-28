package ammon.thilo.app.stochproc;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;

import java.util.ArrayList;

public class StochProcessesController {
    StochProcessView2D spview = null;
    StochProcessesModel spsm = null;
    ArrayList<StochProcessController> stochProcCtrls = null;

    public StochProcessesController(int dimension) {
        spview = new StochProcessView2D(new NumberAxis(), new NumberAxis());
        stochProcCtrls = new ArrayList<StochProcessController>();
    }

    public LineChart getCoorSys() {
        spview.setTitle("Realisation of added stochastic Processes");
        return spview;
    }

    public void addProcess(StochProcessController randContr) {
        stochProcCtrls.add(randContr);
    }

    public ArrayList<StochProcessController> getStochProcCtrls(){
        return stochProcCtrls;
    }

    public void updateProcessesView(){
        spview.getData().removeAll();
        for(StochProcessController spCtrl : stochProcCtrls){
            if(spCtrl.IsRealValuedProcess())
            spview.add2DDataSeries(spCtrl.getTypeNameOfStochasticProcess(),spCtrl.getRealisedValues());
        }
    }
}
