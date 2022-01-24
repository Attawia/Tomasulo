import java.util.Vector;

public class InstructionQ {
    private Vector<Instruction> program;

    public InstructionQ(Vector<Instruction> p){
        program = p;
    }

    public Instruction getNextAndRemove(){

        return program.remove(0);
    }

public Instruction getNext(){
        return program.firstElement();
}

    public Vector<Instruction> getProgram() {
        return program;
    }
}
