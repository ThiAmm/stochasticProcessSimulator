package ammon.thilo.app.ProbabilityFactory;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

import static ammon.thilo.app.HelperFunctions.ArrayHelperFunctions.getFirstSumedValues;
import static ammon.thilo.app.HelperFunctions.ArrayHelperFunctions.getValuesFromPairArrayList;
/**
 * Created by thilo on 09.09.16.
 */
public class RandomNumberGenerator {

    public static ArrayList<Double> generateUniformUnitRandomVariables(int n){
        ArrayList<Double> res = new ArrayList<Double>();
        Random r = new Random();
        for(int i = 0; i<n; i++){
            res.add(r.nextDouble());
        }
        return res;
    }

    public static double generateUniformUnitRandomVariable(){
        return generateUniformUnitRandomVariables(1).get(0);
    }

    /**
     * This method returns a sample of the random variables X
     * with discrete finite state space.
     * @return
     */
    public static double generateDiscreteFiniteRandomVariable(ArrayList<Pair<Double,Double>> valProbPairs){
        ArrayList<Double> distributionFunction = getFirstSumedValues(getValuesFromPairArrayList(valProbPairs));
        double randUniVar = generateUniformUnitRandomVariable();
        int i = 0;
        while(i<distributionFunction.size()){
            if(randUniVar<distributionFunction.get(i)){
                break;
            }
            i++;
        }
        return valProbPairs.get(i).getKey();
    }
}
