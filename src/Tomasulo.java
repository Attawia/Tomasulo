import java.io.*;
import java.util.Vector;

public class Tomasulo {
    public static void main(String[] args) throws IOException {

    InstructionUnit IU = new InstructionUnit("Program.txt");
    IU.createProgram();
    InstructionQ IQ = new InstructionQ(IU.getProgram());

        System.out.println(IQ.getInstruction());





    }
}
