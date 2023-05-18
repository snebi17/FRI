package naloge.drugaNaloga;

public class ProgramerskaNaloga2 {
    public static void main(String[] args) {
        HTB ht = new HTB(7, 3, 5, 7);

        ht.insert(19);
        ht.insert(11);
        ht.insert(23);
        ht.insert(19);
        ht.insert(29);
        ht.insert(17);
        ht.insert(2);
        ht.insert(33);
        ht.insert(99);
        ht.insert(129);

        ht.printKeys();
        System.out.println("--");
        ht.printCollisions();
    }
}
