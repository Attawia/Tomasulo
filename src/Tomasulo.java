import java.io.*;
import java.util.Scanner;
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
        Scanner scanner =  new Scanner(System.in);
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
