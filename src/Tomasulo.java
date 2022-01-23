import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Tomasulo {
    Scanner scanner =  new Scanner(System.in);

    boolean running = true;

    public void resolveBus(){
        //registerFile.readBus(CDB);
        //addStation.resolveBus(CDB);
        //mulStation.resolveBus(CDB);
        //storeBuffer.cycle(CDB);
        //loadBuffer.cycle(CDB);
    }

    public void print(){
        //System.out.println(
        // "Clock Number : " + cycleNo + "\n" +
        // "Instruction Queue : "+instructionQueue.getProgram() + "\n" +
        // "Register File : "+registerFile.toString() + "\n" +
        //"Add/Sub Reservation Station : "+addStation.toString() + "\n" +
        //"Mul/Div Reservation Station : "+mulStation.toString() + "\n" +
        //"Store Buffer : "+storeBuffer.toString() + "\n" +
        //"Load Buffer : "+loadBuffer.toString()
        //              );

    }
    public void programRunner(){

        while(running){



           print();
//            running = (
//                    addStation.isEmpty()&&
//                    mulStation.isEmpty()&&
//                    storeBuffer.isEmpty()&&
//                    loadBuffer.isEmpty())?false :true ;

            scanner.nextLine();

        }
    }



    public static void main(String[] args) throws IOException {






    }
}
