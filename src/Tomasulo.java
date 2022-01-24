import java.util.*;

public class Tomasulo {
    private int cycleNo;
    private final CommonDataBus CDB;
    private final RegisterFile registerFile;
    private final MemoryUnit memoryUnit;
    private final LoadBuffer loadBuffer;
    private final StoreBuffer storeBuffer;
    private final ReservationStation addStation;
    private final ReservationStation mulStation;
    private final InstructionQ instructionQueue;
    Scanner scanner =  new Scanner(System.in);
    boolean running = true;

    public Tomasulo() {
        cycleNo = 0;
        CDB = new CommonDataBus();
        registerFile = new RegisterFile();
        memoryUnit = new MemoryUnit();
        loadBuffer = new LoadBuffer();
        storeBuffer = new StoreBuffer();
        addStation = new ReservationStation(3, "A");
        mulStation = new ReservationStation(2, "M");
        InstructionUnit instructionUnit = new InstructionUnit("Program.txt");
        instructionQueue = new InstructionQ(instructionUnit.getProgram());

    }

    public void resolveBus(){
        registerFile.readBus(CDB);
        addStation.resolveBus(CDB);
        mulStation.resolveBus(CDB);
    }

    public void printInit(){
        System.out.println(
                "At start : \n" +
                        "Instruction Queue : \n "+instructionQueue.getProgram() + "\n" +
                        registerFile.toString() + "\n" +
                        "Memory Unit: \n" +memoryUnit.toString()  + "\n"      +
                        "Add/Sub Reservation Station : \n"+addStation.toString() + "\n" +
                        "Mul/Div Reservation Station : \n"+mulStation.toString() + "\n" +
                        storeBuffer.toString() + "\n" +
                        loadBuffer.toString()
        );

    }

    public void print(){
        System.out.println(
         "Clock Number : " + cycleNo + "\n" +
         "Instruction Queue : \n "+instructionQueue.getProgram() + "\n" +
         registerFile.toString() + "\n" +
         "Memory Unit: \n" +memoryUnit.toString()  + "\n"      +
        "Add/Sub Reservation Station : \n"+addStation.toString() + "\n" +
        "Mul/Div Reservation Station : \n"+mulStation.toString() + "\n" +
        storeBuffer.toString() + "\n" +
        loadBuffer.toString()
                      );

    }
    public void programRunner(){
        printInit();
        while(running){
            execute();
            resolveBus();
            issue();
            print();
            storeBuffer.cycle(CDB);
            loadBuffer.cycle();
            running = !addStation.isEmpty() || !mulStation.isEmpty() ||
                    !storeBuffer.isEmpty() || !loadBuffer.isEmpty();
            CDB.flush();
            cycleNo++;
            scanner.nextLine();

        }
    }




    private void execute(){
        HashMap<String,Float> h = addStation.check();
        CDB.writeMultiple(h);
        h= mulStation.check();
        CDB.writeMultiple(h);
        h= loadBuffer.execute(memoryUnit);
        CDB.writeMultiple(h);
        storeBuffer.execute(memoryUnit);
    }


	public Reservation instructionToReservation(Instruction i) {
		String op = i.getOperation();
		Float vj = null;
		Float vk = null;
		String qj = null;
		String qk = null;
		
		if(registerFile.hasValue(Integer.parseInt(i.getFirstOperand().substring(1)))) {
			vj = registerFile.getData(Integer.parseInt(i.getFirstOperand().substring(1)));
		}
		else {
			qj = registerFile.getTag(Integer.parseInt(i.getFirstOperand().substring(1)));
		}
		
		if(registerFile.hasValue(Integer.parseInt(i.getSecondOperand().substring(1)))) {
			vk = registerFile.getData(Integer.parseInt(i.getSecondOperand().substring(1)));
		}
		else {
			qk = registerFile.getTag(Integer.parseInt(i.getSecondOperand().substring(1)));
		}
		
		return new Reservation(i.getLatency(), op, vj, vk, qj, qk, null);

	}
	

    public void issue() {
        Instruction next;
        try{
            next = instructionQueue.getNext();
        }catch(NoSuchElementException e ){
            return;
        }

        String operation = next.getOperation();
        String station = switch (operation) {
            case "ADD.D", "SUB.D" -> "A";
            case "MUL.D", "DIV.D" -> "M";
            case "L.D" -> "L";
            case "S.D" -> "S";
            default -> "";
        };
        boolean hasPlace = switch (station) {
            case "A" -> addStation.freeSpace();
            case "M" -> mulStation.freeSpace();
            case "L" -> loadBuffer.isAvailable();
            case "S" -> storeBuffer.isAvailable();
            default -> false;
        };
        if (!hasPlace)
            return;
        next = instructionQueue.getNextAndRemove();
        int regNo = Integer.parseInt(next.getDestReg().substring(1));
        String tag;
        if (station.equals("A") || station.equals("M")) {
            Reservation res = instructionToReservation(next);
            tag = station.equals("A") ? addStation.addReservation(res) : mulStation.addReservation(res);
            registerFile.awaitOn(regNo, tag);
        } else if (station.equals("L")) {
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

    public static void main(String[] args) {
        Tomasulo tom = new Tomasulo();
        tom.programRunner();
    }


}