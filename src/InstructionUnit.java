import java.io.*;
import java.util.Vector;

public class InstructionUnit {


    String textFileProg;
    String filePathProg;
    File fp;


    private Vector<Instruction> program;


    public InstructionUnit(String file){
        program = new Vector<Instruction>();

        textFileProg = file;
        fp = new File("src/Files/"+file);
        filePathProg = fp.getAbsolutePath();

    }

    public Vector<Instruction> getProgram() {
        return program;
    }

    public void setProgram(Vector<Instruction> program) {
        this.program = program;
    }

    public void createProgram() throws IOException {


        BufferedReader br = new BufferedReader(new FileReader(filePathProg));

        String lineP,  op, op1, op2, dest;

        while ((lineP = br.readLine()) != null){
            String[] instructionAsText = lineP.split(" ");
            op = instructionAsText[0];
            dest = instructionAsText[1];
            op1 = instructionAsText[2];
            op2 = instructionAsText[3];

            program.add(new Instruction(op, dest, op1 ,op2));

        }

    }
}
