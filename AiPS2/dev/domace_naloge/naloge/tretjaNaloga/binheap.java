package naloge.tretjaNaloga;

import java.time.temporal.IsoFields;

public class binheap {
    private Integer[] array;
    private int size;

    private static int compareCounter;

    public binheap() {
        array = new Integer[1];
        size = 1;
    }

    public void insert(int key) {

    }

    public void deleteMin() {
        if (array[0] == null) {
            System.out.println("false");
            return;
        }

        int min = array[0];
        array[0] = null;
        int leaf = array[array.length - 1];
        array[array.length - 1] = null;
        insert(leaf);
        System.out.printf("true: %d", min);
    }

    public void printElements() {
        System.out.printf("%d", array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.printf(", %d", array[i]);
        }
    }

    public void printMin() {
        if (array[0] == null) System.out.printf("MIN: none\n");
        else System.out.printf("MIN: %d\n", array[0]);
    }

    public void printComparisons() {
        System.out.printf("COMPARISONS: %d\n", compareCounter);
    }
}
