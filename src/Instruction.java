import java.io.*;

public class Instruction {

    private String operation;
    private String firstOperand;
    private String secondOperand;
    private String destReg;
    private int latency;



    public Instruction(String op, String dest, String first, String second) throws IOException {
        operation = op;
        firstOperand = first;
        secondOperand = second;
        destReg = dest;

        File f = new File("src/Files/Latencies.txt");
        String path = f.getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while ((line = br.readLine()) != null){
            String [] l = line.split(" ");

            if(l[0].equals(op)){
                latency = Integer.parseInt(l[1]);
            }
        }

    }
    public String getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }

    public String getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getDestReg() {
        return destReg;
    }

    public void setDestReg(String destReg) {
        this.destReg = destReg;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }


    @Override
    public String toString() {
        boolean mem = (this.operation.equals("LD.D") || this.operation.equals("ST.D"))?
                true:false;

        if(!mem)
        return "Instruction{"+"\n" +
                "Operation: " + this.operation + "\n" +
                "Operand1 : " + this.firstOperand + "\n" +
                "Operand2 : " + this.secondOperand + "\n" +
                "Destination Register : " + this.destReg + "\n" +
                "Latency : " + this.latency +"}";

        return "Instruction{"+"\n" +
                "Operation: " + this.operation + "\n" +
                "Target Address : " + this.firstOperand + "\n" +
                "Destination Register : " + this.destReg + "\n" +
                "Latency : " + this.latency +"}";
    }
}
