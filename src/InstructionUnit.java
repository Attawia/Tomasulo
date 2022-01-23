import java.io.*;
import java.util.Vector;

public class InstructionUnit {


    String textFileProg;
    String filePathProg;
    File fp;


    private Vector<Instruction> program;


    public InstructionUnit(String file) {
        program = new Vector<Instruction>();

        textFileProg = file;
        fp = new File("src/Files/"+file);
        filePathProg = fp.getAbsolutePath();
        try {
            this.createProgram();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            if(!(op.equals("LD.D") || op.equals("ST.D"))) op2 = instructionAsText[3];
            else op2 = "";

            program.add(new Instruction(op, dest, op1 ,op2));

        }

    }
}
