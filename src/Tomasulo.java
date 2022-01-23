import java.io.*;
import java.util.Vector;

public class Tomasulo {
 boolean running = true;

    public void resolveBus(){
        //registerFile.readBus(CDB);
        //addStation.resolveBus(CDB);
        //mulStation.resolveBus(CDB);
        //storeBuffer.cycle(CDB);
        //loadBuffer.cycle(CDB);
    }

    public void print(){
        //System.out.println(instructionQueue.getProgram() + "\n" +
        //            registerFile.toString() + "\n" +
        //             addStation.toString() + "\n" +
        //              mulStation.toString() + "\n" +
        //              storeBuffer.toString() + "\n" +
        //              loadBuffer.toString()
        //              );

    }
    public void programRunner(){
        while(running){



           print();
//            running = (registerFile.isEmpty()&&
//                    addStation.isEmpty()&&
//                    mulStation.isEmpty()&&
//                    storeBuffer.isEmpty()&&
//                    loadBuffer.isEmpty())?false :true ;
//
        }
    }



    public static void main(String[] args) throws IOException {


        InstructionUnit IU = new InstructionUnit("Program.txt");


        // creating instructionQ and passing the program to it
        InstructionQ IQ = new InstructionQ(IU.getProgram());

        System.out.println(IQ.getProgram());





    }
}
