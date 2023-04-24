package naloge;

import java.util.ArrayList;
import java.util.List;

public class ArrArray {
    // null ... prazen element, -1 ... leno brisan element
    private static List<ArrayElement[]> array;
    private static int size;
    private static int[] bloomFilter;

    ArrArray() {
        array = new ArrayList<>();
        size = 0;
        bloomFilter = new int[10];
    }

    public void insert(int e) {
        if (!elementInArray(e)) {
            hash(e);
        }

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 0; i < k; i++) {
            int levelSize = (int) Math.pow(2, i);
            if ((size & levelSize) == levelSize) {

            }
        }

        size++;
    }

    // Buggy fix
    public boolean find(int e) {
        if (!elementInArray(e)) return false;

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 0; i < k; i++) {
            // if A_i is empty continue


            int low = 0;
            int high = (int) Math.pow(2, i) - 1;

            if (array.get(i)[low].compareTo(e) == 1 || array.get(i)[high].compareTo(e) == -1) continue;

            while (low <= high) {
                int j = low + ((high - low) / 2);
                if (array.get(i)[j].compareTo(e) == 0) return true;
                if (array.get(i)[j].compareTo(e) == -1) {
                    low = j + 1;
                } else if (array.get(i)[j].value > e) {
                    high = j - 1;
                }
            }
        }
        return false;
    }

    public boolean delete(int e) {
        if (!elementInArray(e)) return false;

        if (array.get(0)[0].compareTo(e) == 0) {
            array.get(0)[0].setValue(-1);
            return true;
        }

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);

        for (int i = 2; i < k; i++) {
            int low = 0;
            int high = (int) Math.pow(2, i) - 1;

            if (array.get(i)[low].compareTo(e) == 1 || array.get(i)[low].compareTo(e) == -1) continue;

            while (low <= high) {
                int j = low + ((high - low) / 2);
                if (array.get(i)[j].compareTo(e) == 0) {
                    /*if (duplicates[e] > 0) {
                        duplicates[e]--;
                    } else {
                        array.get(i)[j].value = -1;
                    }*/
                    size--;
                    return true;
                }
                if (array.get(i)[j].compareTo(e) == -1) {
                    low = j + 1;
                } else if (array.get(i)[j].compareTo(e) == 1) {
                    high = j - 1;
                }
            }
        }
        return false;
    }

    public void printOut() {
        if (size == 0) {
            System.out.println("empty");
            return;
        }

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 0; i < k; i++) {
            System.out.printf("%s%d:", "A_", i);
            if (!isEmpty(i)) {
                if (array.get(i)[0].getValue() != null) {
                    System.out.printf("%d/%d", array.get(i)[0].getValue(), array.get(i)[0].getCounter());
                } else if (array.get(i)[0].isDeleted) {
                    System.out.printf("x");
                }
                for (int j = 1; j < (int) Math.pow(2, i); j++) {
                    if (array.get(i)[j].getValue() != null) {
                        System.out.printf(", %d/%d", array.get(i)[j].getValue(), array.get(i).getCounter());
                    } else if (array.get(i)[j].compareTo(-1) == 0) {
                        System.out.printf(", x");
                    }
                }
                System.out.println();
            } else {
                System.out.println(" ...");
            }
        }
    }

    private boolean isEmpty(int level) {
        int levelSize = (int) Math.pow(2, level);
        return (size & levelSize) == 0;
    }

    private void hash(int e) {
        while (e > 1) {
            if (bloomFilter[e % 10] == 1) continue;
            bloomFilter[e % 10]++;
            e = e / 10;
        }
    }


    private boolean elementInArray(int e) {
        while (e > 1) {
            if (bloomFilter[e % 10] == 0) return false;
            e = e / 10;
        }
        return true;
    }

    private static void resize() {

    }

    private static void merge() {

    }

    class ArrayElement implements Comparable<Integer> {
        private Integer value;
        int duplicateCounter;
        boolean isDeleted;

        ArrayElement() {
            duplicateCounter = 0;
            isDeleted = false;
        }

        @Override
        public int compareTo(Integer x) {
            return this.value > x ? 1 : this.value == x ? 0 : -1;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setDuplicateCounter(int x) {
            duplicateCounter += x;
        }

        public int getDuplicateCounter() {
            return duplicateCounter;
        }

        public void lazyDelete() {
            isDeleted = true;
            duplicateCounter--;
        }
    }
}