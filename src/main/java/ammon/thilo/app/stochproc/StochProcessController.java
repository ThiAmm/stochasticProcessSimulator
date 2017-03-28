package ammon.thilo.app.stochproc;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by thilo on 07.09.16.
 */
public interface StochProcessController {
    public void simulateNextPoint(double time);

    public ArrayList<RealisedValue> getRealisedValues();

    public String getTypeNameOfStochasticProcess();

    boolean IsRealValuedProcess();

    public void setId(int id);

    public int getId();

    ArrayList<Pair<Double, ArrayList<Double>>> getNewRealisedValues();
}
