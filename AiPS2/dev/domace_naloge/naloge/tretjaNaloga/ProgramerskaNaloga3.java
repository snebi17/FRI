package naloge.tretjaNaloga;

public class ProgramerskaNaloga3 {
    public static void main(String[] args) {
        binheap bh = new binheap();

        bh.insert(12); bh.insert(22); bh.insert(33);
        bh.insert(4); bh.insert(14); bh.insert(6); bh.insert(24);
        bh.printElements();
        bh.printMin();
        bh.printComparisons();
        bh.deleteMin();
        bh.printElements();
        bh.printComparisons();
    }
}
