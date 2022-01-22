import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RegisterFile {

    private float[] data;
    private String[] awaiting;
    private boolean[] hasData;


    public RegisterFile(){
        data = new float[32];
        awaiting = new String[32];
        hasData = new boolean[32];
        try {
            fetchData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Registers.txt"));
        String line = reader.readLine();
        while (line != null) {
            int regNo = Integer.parseInt(line.split(":")[0].substring(1).replaceAll("\\s",""));
            System.out.println(regNo);
            float regData;
            try {
                regData = Float.parseFloat(line.split(":")[1].replaceAll("\\s",""));
                data[regNo] = regData;
                hasData[regNo] = true;
                System.out.println(regData);
            }catch(Exception e){};

            line = reader.readLine();
        }
    }
}
