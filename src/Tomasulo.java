import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class Tomasulo {


    private void execute(){
        HashMap<String,Float> h = addStation.check();
        CDB.writeMultiple(h);
        h= mulStation.check();
        CDB.writeMultiple(h);
        h= loadBuffer.execute(memoryUnit);
        CDB.writeMultiple(h);
        storeBuffer.execute(memoryUnit);
    }

}
