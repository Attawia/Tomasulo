import java.util.HashMap;

public class CommonDataBus {

    private HashMap<String, Float> data;

    public CommonDataBus(){
        data = new HashMap<String,Float>();
    }

    public void write(String tag,Float value){
        data.put(tag,value);
    }

    public Float search(String tag){
        return data.get(tag);
    }

    public void flush(){
        data.clear();
    }


}
