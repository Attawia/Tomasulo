import java.io.*;
import java.util.Vector;

public class Tomasulo {

	
  	private int cycleNo;
    private CommonDataBus CDB;
    private RegisterFile registerFile;
    private MemoryUnit memoryUnit;
    private LoadBuffer loadBuffer;
    private StoreBuffer storeBuffer;
    private ReservationStation addStation;
    private ReservationStation mulStation;
    private InstructionQ instructionQueue;
    private InstructionUnit instructionUnit;
	
	public static Reservation instructionToReservation(Instruction i) {
		String op = i.getOperation();
		Float vj = null;
		Float vk = null;
		String qj = null;
		String qk = null;
		
		if(registerFile.hasValue(Integer.parseInt(i.getFirstOperand().substring(1, 2)))) {
			vj = registerFile.getData(Integer.parseInt(i.getFirstOperand().substring(1, 2)));
		}
		else {
			qj = registerFile.getTag(Integer.parseInt(i.getFirstOperand().substring(1, 2)));
		}
		
		if(registerFile.hasValue(Integer.parseInt(i.getSecondOperand().substring(1, 2)))) {
			vk = registerFile.getData(Integer.parseInt(i.getSecondOperand().substring(1, 2)));
		}
		else {
			qk = registerFile.getTag(Integer.parseInt(i.getSecondOperand().substring(1, 2)));
		}
		
		Reservation r = new Reservation(i.getLatency(), op, vj, vk, qj, qk, null);
		return r;
	}
	
    public static void main(String[] args) throws IOException {




    


}
