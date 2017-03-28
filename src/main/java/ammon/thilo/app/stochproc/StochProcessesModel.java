package ammon.thilo.app.stochproc;

import java.util.ArrayList;

/**
 * Created by thilo on 13.09.16.
 */
public class StochProcessesModel {
    ArrayList<StochProcessModel> spms = null;
    public StochProcessesModel(){
        spms = new ArrayList<StochProcessModel>();
    }
    public void add(StochProcessModel spm){
        spms.add(spm);
    }

    public ArrayList<StochProcessModel> getStochProcesses() {
        return spms;
    }
}
