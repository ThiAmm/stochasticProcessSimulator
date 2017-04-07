package ammon.thilo.app.stochproc;

import java.util.ArrayList;

/**
 * Created by thilo on 13.09.16.
 */
public class StochProcessesModel {
    ArrayList<StochasticProcessModel> spms = null;
    public StochProcessesModel(){
        spms = new ArrayList<StochasticProcessModel>();
    }
    public void add(StochasticProcessModel spm){
        spms.add(spm);
    }

    public ArrayList<StochasticProcessModel> getStochProcesses() {
        return spms;
    }
}
