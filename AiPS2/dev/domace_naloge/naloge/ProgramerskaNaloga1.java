package naloge;

public class ProgramerskaNaloga1 {
    public static void main(String[] args) {
        ArrArray tbl = new ArrArray();

        tbl.insert(1);
        tbl.insert(2);
        tbl.insert(3);
        tbl.insert(4);
        tbl.insert(5);
        tbl.insert(6);
        tbl.insert(7);
        tbl.insert(8);
        tbl.printOut();
        tbl.delete(1);
        tbl.delete(2);
        tbl.delete(3);
        tbl.delete(4);
        tbl.delete(5);
        tbl.delete(6);
        tbl.delete(7);
        tbl.delete(8);

        tbl.printOut();
    }
}