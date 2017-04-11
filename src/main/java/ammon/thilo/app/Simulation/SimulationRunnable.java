package ammon.thilo.app.Simulation;

import ammon.thilo.app.stochproc.StochProcessController;
import ammon.thilo.app.stochproc.StochProcessesController;

import java.util.ArrayList;

/**
 * Created by thilo on 13.09.16.
 */
public class SimulationRunnable implements Runnable {
    double timeBetweenNewSimulationPoint;
    private static final int DELAYBETWEENVIEWUPDATESIMULATION = 500;
    double time = 0;
    private boolean simulationPause = false;
    ArrayList<StochProcessController> spsCtrls = null;
    StochProcessesController stochasticprocessesctrl;

    public SimulationRunnable(StochProcessesController stochasticprocessesctrl, ArrayList<StochProcessController> spsCtrls, double timeBetweenNewSimulationPoint){
        this.spsCtrls = spsCtrls;
        this.timeBetweenNewSimulationPoint = timeBetweenNewSimulationPoint;
        this.stochasticprocessesctrl = stochasticprocessesctrl;
    }

    public void run() {
        while(true) {
            while (!simulationPause) {
                for (StochProcessController spCtrl : spsCtrls) {
                    spCtrl.createNewRealisedValue(time);
                }
                try {
                    Thread.sleep(DELAYBETWEENVIEWUPDATESIMULATION);
                } catch (InterruptedException e) {

                }
                stochasticprocessesctrl.updateProcessesView(time);
                time = time + timeBetweenNewSimulationPoint;
            }
            try {
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void setSimulationActive(boolean active){
        simulationPause = !active;
        notify();
    }
}
