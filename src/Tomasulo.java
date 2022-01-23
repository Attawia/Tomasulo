import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
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


    public Tomasulo() {
        cycleNo = 0;
        CDB = new CommonDataBus();
        registerFile = new RegisterFile();
        memoryUnit = new MemoryUnit();
        loadBuffer = new LoadBuffer(1);
        storeBuffer = new StoreBuffer(1);
        addStation = new ReservationStation(3, "A");
        mulStation = new ReservationStation(2, "M");
        instructionUnit = new InstructionUnit("Program");
        instructionQueue = new InstructionQ(instructionUnit.getProgram());

    }


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
	
	
    public void issue() {
        Instruction next = instructionQueue.getInstruction();
        String operation = next.getOperation();
        String station = "";
        switch (operation) {
            case "ADD.D":
            case "SUB.D":
                station = "A";
                break;
            case "MUL.D":
            case "DIV.D":
                station = "M";
                break;
            case "L.D":
                station = "L";
                break;
            case "S.D":
                station = "S";
                break;
        }
        boolean hasPlace = false;
        switch (station) {
            case "A":
                hasPlace = addStation.freeSpace();
                break;
            case "M":
                hasPlace = mulStation.freeSpace();
                break;
            case "L":
                hasPlace = loadBuffer.isAvailable();
                break;
            case "S":
                hasPlace = storeBuffer.isAvailable();
                break;
        }
        if (!hasPlace)
            return;
        next = instructionQueue.getInstructionAndRemove();
        int regNo = Integer.parseInt(next.getDestReg().substring(1));
        String tag;
        if (station == "A" || station == "M") {
            Reservation res = instructionToReservation(next);
            tag = station == "A" ? addStation.addReservation(res) : mulStation.addReservation(res);
            registerFile.awaitOn(regNo, tag);
        } else if (station == "L") {
            tag = loadBuffer.add(Integer.parseInt(next.getFirstOperand()));
            registerFile.awaitOn(regNo, tag);
        } else {
            if (registerFile.hasValue(regNo)) {
                storeBuffer.add(Integer.parseInt(next.getFirstOperand()), registerFile.getData(regNo), cycleNo);
            } else {
                storeBuffer.add(Integer.parseInt(next.getFirstOperand()), registerFile.getTag(regNo), cycleNo);
            }
        }


    }


}