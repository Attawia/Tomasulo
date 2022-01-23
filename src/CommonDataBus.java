import java.util.HashMap;
import java.util.Map;

public class CommonDataBus {

    private HashMap<String, Float> data;

    public CommonDataBus(){
        data = new HashMap<String,Float>();
    }

    public void write(String tag,Float value){
        data.put(tag,value);
    }




    public void writeMultiple(HashMap<String ,Float> values){
        data.putAll(values);
    }


    public Float search(String tag){
        return data.get(tag);
    }

    public void flush(){
        data.clear();
    }

    public String toString(){
        String toReturn =  "CDB Contents: \n";
        for (Map.Entry<String, Float> set : data.entrySet()) {
            toReturn +=  set.getKey() + " = " + set.getValue() + "\n";
        }
        return toReturn;
    }

}
