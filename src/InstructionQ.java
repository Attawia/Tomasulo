import java.util.Vector;

public class InstructionQ {
    private Vector<Instruction> program;

    public InstructionQ(Vector<Instruction> p){
        program = p;
    }

    public Instruction getInstructionAndRemove(){
        return program.remove(0);
    }

public Instruction getInstruction(){
        return program.firstElement();
}
}
