package ammon.thilo.app.HelperFunctions;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by thilo on 09.09.16.
 */
public class ArrayHelperFunctions {

    public static ArrayList<Double> getFirstSumedValues(ArrayList<Double> values){
        ArrayList<Double>  res = new ArrayList<Double>();
        double sum = 0;
        for(int i = 0; i<values.size();i++){
            sum = sum + values.get(i);
            res.add(sum);
        }
        return res;
    }

    public static ArrayList<Double> getKeysFromPairArrayList(ArrayList<Pair<Double,Double>> pairArr){
        ArrayList<Double> res = new ArrayList<Double>();
        for(Pair p : pairArr){
            res.add((Double) p.getKey());
        }
        return res;
    }

    public static ArrayList<Double> getValuesFromPairArrayList(ArrayList<Pair<Double,Double>> pairArr){
        ArrayList<Double> res = new ArrayList<Double>();
        for(Pair p : pairArr){
            res.add((Double) p.getValue());
        }
        return res;
    }

    public static ArrayList<Double> createArrayListFromDouble(double t){
        ArrayList<Double> d = new ArrayList<Double>();
        d.add(t);
        return d;
    }
}
