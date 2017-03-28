package ammon.thilo.app.stochproc;

import java.util.ArrayList;

/**
 * Created by thilo on 24.09.16.
 */
public class State implements Comparable<State>{
    ArrayList<Double> value = null;
    int dimension;
    State(ArrayList<Double> val){
        this.value = val;
        dimension = val.size();
    }

    ArrayList<Double> getValue(){
        return value;
    }

    public int compareTo(State o) {
        if(value.size()==1){
            if(getValue().get(0)>o.getValue().get(0)){
                return 1;
            }else{
                if(getValue()==o.getValue()){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object o){
        if (!(o instanceof Object)) {
            return false;
        }

        State s = (State) o;
        if(value.size()==1){
            return s.getValue().equals(this.getValue().get(0));
        }
        return false;
    }

    public boolean isGreater(State i) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
