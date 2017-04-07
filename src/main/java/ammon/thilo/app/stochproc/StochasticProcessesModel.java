package ammon.thilo.app.stochproc;

import java.util.ArrayList;

/**
 * Created by thilo on 13.09.16.
 */
public class StochasticProcessesModel {
    ArrayList<StochasticProcessModel> spms = null;
    public StochasticProcessesModel(){
        spms = new ArrayList<StochasticProcessModel>();
    }
    public void add(StochasticProcessModel spm){
        spms.add(spm);
    }

    public ArrayList<StochasticProcessModel> getStochProcesses() {
        return spms;
    }
}
