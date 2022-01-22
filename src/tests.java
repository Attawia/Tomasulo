public class tests {

    public static void main(String[] args) {
        CommonDataBus CDB = new CommonDataBus();
        CDB.write("A1",1.234f);
        CDB.write("M2",3.424f);
        RegisterFile reg = new RegisterFile();
        reg.awaitOn(0,"A1");
        reg.awaitOn(9,"M2");
        reg.awaitOn(15,"A3");
        reg.storeValue(12,1.111f);
        reg.storeValue(31,2.22f);
        reg.readBus(CDB);
        System.out.println(reg.getData(12));
        System.out.println(reg.getTag(15));
        System.out.println(reg.getTag(9));
    }
}
