import java.util.HashMap;

public class tests {

    public static void main(String[] args) {
        RegisterFile registerFile = new RegisterFile();
        registerFile.awaitOn(2,"A3");
        System.out.println(registerFile);

        MemoryUnit memoryUnit = new MemoryUnit();
        System.out.println(memoryUnit);
    }
}
