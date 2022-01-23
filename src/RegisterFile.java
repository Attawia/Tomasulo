import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RegisterFile {

    private float[] data;
    private String[] awaiting;
    private boolean[] hasData;
    private boolean[] isAwaiting;


    public RegisterFile(){
        data = new float[32];
        awaiting = new String[32];
        hasData = new boolean[32];
        isAwaiting = new boolean[32];
        Arrays.fill(data,0);
        Arrays.fill(hasData,true);
        Arrays.fill(isAwaiting,false);
        try {
            fetchData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void awaitOn(int regNo,String tag){
        hasData[regNo] = false;
        isAwaiting[regNo] = true;
        awaiting[regNo] = tag;
    }

    public void storeValue(int regNo, float value){
        isAwaiting[regNo] = false;
        hasData[regNo] = true;
        data[regNo] = value;
    }

    public void readBus(CommonDataBus CDB){
        for(int i=0;i<isAwaiting.length;i++){
            if(isAwaiting[i] == true){
                Float value = CDB.search(awaiting[i]);
                if(value!=null){
                    isAwaiting[i] = false;
                    hasData[i] = true;
                    data[i] = value;
                }
            }
        }
    }

    public boolean hasValue(int regNo){
        if(hasData[regNo]){
            return true;
        }
        else return false;
    }

    public String getTag(int regNo){
        if(isAwaiting[regNo]){
            return awaiting[regNo];
        }
        else return "invalid_tag";
    }

    public float getData(int regNo){
        return data[regNo];
    }



    private void fetchData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Registers.txt"));
        String line = reader.readLine();
        while (line != null) {
            int regNo = Integer.parseInt(line.split(":")[0].substring(1).replaceAll("\\s",""));
            float regData;
            try {
                regData = Float.parseFloat(line.split(":")[1].replaceAll("\\s",""));
                data[regNo] = regData;
            }catch(Exception e){};

            line = reader.readLine();
        }
    }

    public String toString(){
        String toReturn = "Register File: \n";
        for(int i = 0 ; i<32 ; i++){
            toReturn += "F" + i + ": ";
            if(hasData[i])
                toReturn += data[i] + "\n";
            else
                toReturn += "\"" + awaiting[i] + "\"" + "\n";
        }
        return toReturn;

    }
}
