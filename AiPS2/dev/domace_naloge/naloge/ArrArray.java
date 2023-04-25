package naloge;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrArray {
    private static List<ArrayElement[]> array;
    private static int size;
    private static int[] bloomFilter;

    private static List<ArrayElement> duplicatedInstances;

    public ArrArray() {
        array = new ArrayList<>();
        size = 0;
        bloomFilter = new int[10];
        duplicatedInstances = new ArrayList<>();
    }

    public void insert(int e) {
        /***
         * Če duplikat obstaja v tabeli, inkrementiraj števec. O(log^2 n)
         */
        if (find(e)) {
            duplicatedInstances.stream()
                    .filter(element -> element.value == e)
                    .findFirst()
                    .ifPresent(element -> {
                        element.incrementDuplicateCounter();
                        size++;
                    });
            return;
        }

        /***
         * Če je element unikaten, ga sheširaj, potem ga dodaj v prvo nealocirano (leno zbrisano) mesto v tabeli.
         */
        if (!elementInArray(e)) {
            hash(e);
        }

        if (size == 0) {
            array.add(new ArrayElement[] { new ArrayElement(e) });
            size++;
            return;
        }

        /**
         * Časovna kompleksnost: O(n log n)
         */
        int k = array.size();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < (int) Math.pow(2, i); j++) {
                if ((int) Math.floor(Math.log(size + 1) / Math.log(2) + 1) > k) {
                    merge(e);
                    return;
                }
                if (array.get(i)[j] == null || array.get(i)[j].isDeleted) {
                    array.get(i)[j] = new ArrayElement(e);
                    size++;
                    if ((int) Math.floor(Math.log(size) / Math.log(2)) > array.size()) {
                        merge(e);
                    }
                    return;
                }
            }
        }

    }

    /***
     * Časovna kompleksnost: O(log^2 n)
     */
    public boolean find(int e) {
        if (!elementInArray(e)) return false;

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        /***
         * Časovna kompleksnost: O(log n)
         */
        for (int i = 0; i < k; i++) {
            if (isEmpty(i)) continue;

            int low = 0;
            int high = (int) Math.pow(2, i) - 1;

            if (array.get(i)[low].compareTo(e) == 1 || array.get(i)[high].compareTo(e) == -1) continue;

            /***
             * Časovna kompleksnost: O(log n)
             */
            while (low <= high) {
                int j = low + ((high - low) / 2);
                if (array.get(i)[j].compareTo(e) == 0) return !array.get(i)[j].isDeleted;
                if (array.get(i)[j].compareTo(e) == -1) {
                    low = j + 1;
                } else if (array.get(i)[j].compareTo(e) == 1) {
                    high = j - 1;
                }
            }
        }
        return false;
    }

    public boolean delete(int e) {
        if (!elementInArray(e)) return false;

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);

        for (int i = 0; i < k; i++) {
            if (isEmpty(i)) continue;

            int low = 0;
            int high = (int) Math.pow(2, i) - 1;

            if (array.get(i)[low].compareTo(e) == 1 || array.get(i)[low].compareTo(e) == -1) continue;

            while (low <= high) {
                int j = low + ((high - low) / 2);
                if (array.get(i)[j].compareTo(e) == 0) {
                    array.get(i)[j].lazyDelete();
                    size--;
                    removeFromFilter(e);
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
            System.out.printf("%s%d: ", "A_", i);
            if (!isEmpty(i)) {
                if (array.get(i)[0] != null) {
                    System.out.printf("%d/%d", array.get(i)[0].getValue(), array.get(i)[0].getDuplicateCounter());
                } else if (array.get(i)[0].isDeleted) {
                    System.out.printf("x");
                }
                for (int j = 1; j < (int) Math.pow(2, i); j++) {
                    if (array.get(i)[j] != null) {
                        System.out.printf(", %d/%d", array.get(i)[j].getValue(), array.get(i)[j].getDuplicateCounter());
                    } else if (array.get(i)[j].isDeleted) {
                        System.out.printf(", x");
                    }
                }
                System.out.println();
            } else {
                System.out.println("...");
            }
        }
    }

    private boolean isEmpty(int level) {
        int levelSize = (int) Math.pow(2, level);
        return (size & levelSize) == 0;
    }

    private void hash(int e) {
        while (e > 1) {
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

    private void removeFromFilter(int e) {
        while (e > 1) {
            bloomFilter[e % 10]--;
            e = e / 10;
        }
    }

    private void resize() {

    }

    private void merge(int e) {
        int k = array.size();
        int n = (int) Math.pow(2, k);
        ArrayElement[] mergedArray = new ArrayElement[n];

        int l = 0;
        for (int i = 0; i < k; i++) {
            int j = 0;
            while (j < Math.pow(2, i)) {
                mergedArray[l] = array.get(i)[j];
                j++;
                l++;
            }
            array.set(i, new ArrayElement[j]);
        }

        mergedArray[n - 1] = new ArrayElement(e);
        array.add(mergedArray);
    }

    class ArrayElement implements Comparable<Integer> {
        private int value;
        int duplicateCounter;
        boolean isDeleted;

        public ArrayElement(int value) {
            this.value = value;
            duplicateCounter = 0;
            isDeleted = false;
        }

        @Override
        public int compareTo(Integer x) {
            return value > x ? 1 : value == x ? 0 : -1;
        }

        public int getValue() {
            return value;
        }

        public void incrementDuplicateCounter() {
            duplicateCounter++;
        }

        public int getDuplicateCounter() {
            return duplicateCounter;
        }

        public void lazyDelete() {
            if (--duplicateCounter == 0) {
                isDeleted = true;
                duplicatedInstances.remove(this);
            }
        }
    }
}
