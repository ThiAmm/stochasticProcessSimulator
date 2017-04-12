package ammon.thilo.app.stochproc;

import ammon.thilo.app.HelperFunctions.ArrayHelperFunctions;

import java.util.ArrayList;

/**
 * Created by thilo on 13.09.16.
 */
public class RealisedValue extends State{
    double time;
    public RealisedValue(double time, ArrayList<Double> val){
        super(val);
        this.time = time;
    }

    public RealisedValue(double time, double val){
        super(ArrayHelperFunctions.createArrayListFromDouble(val));
        this.time = time;
    }


    public double getTime(){
        return time;
    }
}
