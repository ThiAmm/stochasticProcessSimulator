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

    public static double generateExponentialRandomVariable(double theta){
        return -theta*Math.log(generateUniformUnitRandomVariable());
    }

    public static double[] generateNormalRandomNumbersBoxMuller(){
        double u1 = generateUniformUnitRandomVariable();
        double u2 = generateUniformUnitRandomVariable();
        double[] ret = new double[2];
        ret[0] = Math.sqrt(-2*Math.log(u1))*Math.cos(2*Math.PI*u2);
        ret[1] = Math.sqrt(-2*Math.log(u1))*Math.sin(2*Math.PI*u2);
        return ret;
    }

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

    public static double generatePoissonDistributedRandomVariable(double theta){
        double p = Math.exp(-theta);
        double F = p;
        int N = 0;
        double u = generateUniformUnitRandomVariable();
        while(u>F){
            N++;
            p = (p*theta)/N;
            F = F + p;
        }
        return N;
    }
}
