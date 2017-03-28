package ammon.thilo.app.TestHelperFunctions;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import static ammon.thilo.app.HelperFunctions.StringHelperFunctions.crtDblArrayFrmStrg;

import static ammon.thilo.app.HelperFunctions.StringHelperFunctions.crtDblArrayFrmStrg;
/**
 * Created by thilo on 08.09.16.
 */
public class StringHelperFunctionsTest {
    @Test
    public void ConvertStringSepSemToArray(){
        ArrayList<Double> db1 = new ArrayList<Double>();
        db1.add(3.0);
        db1.add(4.3);
        db1.add(2.1);
        assertEquals(db1, crtDblArrayFrmStrg("3;4.3;2.1"));

        ArrayList<Double> db2 = new ArrayList<Double>();
        db2.add(4.1);
        assertEquals(db2, crtDblArrayFrmStrg("4.1"));

        ArrayList<Double> db3 = new ArrayList<Double>();
        assertEquals(db3, crtDblArrayFrmStrg(""));

        //ArrayList<Double> db4 = new ArrayList<Double>();
        //assertEquals(db3, crtDblArrayFrmStrg(";"));

        ArrayList<Double> db5 = new ArrayList<Double>();
        db5.add(3.33333);
        db5.add(2.1);
        db5.add(5.2);
        assertEquals(db5, crtDblArrayFrmStrg("3.33333;2.1;5.2;"));

    }
}