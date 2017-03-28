package ammon.thilo.app.HelperFunctions;

import java.util.ArrayList;

/**
 * Created by thilo on 08.09.16.
 */
public class StringHelperFunctions {

    /**
     * Generates an array with double values from a string with double
     * values seperated by semicolons
     * @param values
     * @return
     */
    public static ArrayList<Double> crtDblArrayFrmStrg(String values) {
        ArrayList<Double> dblList = new ArrayList<Double>();
        for(int i = 0; i<values.length();i++){
            String valueAsString = null;
            int j = 0;
            while(!(values.substring(i+j,i+j+1).equals(";") || i+j == values.length())) {
                j++;
                if(i+j==values.length()){
                    break;
                }
            }
            valueAsString = values.substring(i, i + j);
            double value = Double.parseDouble(valueAsString);
            dblList.add(value);
            i = i + j;
        }
        return dblList;

    }
}
