import java.util.HashMap;

public class tests {

    public static void main(String[] args) {
        CommonDataBus CDB = new CommonDataBus();
        CDB.write("A1",1.234f);
        CDB.write("M2",3.424f);
        HashMap<String, Float> values = new HashMap<String,Float>();
        values.put("A2",1.2234f);
        values.put("A3",4.32f);
        values.put("M1",5.23421f);
        CDB.writeMultiple(values);
        RegisterFile registerFile = new RegisterFile();
        registerFile.awaitOn(2,"A3");
        System.out.println(CDB);
        System.out.println(registerFile);
    }
}
