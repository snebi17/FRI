package naloge;
<<<<<<< HEAD
import java.util.*;
=======
<<<<<<< HEAD
đ
=======

import java.lang.reflect.Array;
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94

public class ArrArray {
<<<<<<< HEAD
    private static List<Element[]> array;
=======
    private static List<ArrayElement[]> array;
<<<<<<< HEAD
    private int size;
    private static int[] bloomFilter;

    public ArrArray() {
        array = new ArrayList<>();
=======
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
    private static int size;

    private static List<Integer> duplicateElements;
    private static int[] bloomFilter;

<<<<<<< HEAD
    ArrArray() {
        array = new ArrayList<>(1);
=======
    private static List<ArrayElement> duplicatedInstances;

    public ArrArray() {
        array = new ArrayList<>();
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
        size = 0;
        duplicateElements = new ArrayList<>();
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94
        bloomFilter = new int[10];
    }

    /**
     * Časovna kompleksnost O(n^2 log^2 n)
     * @param e
     */
    public void insert(int e) {
        hash(e);

        ArrayElement element = findElement(e);
        if (element != null) {
            element.duplicateCounter++;
            return;
        }

<<<<<<< HEAD

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 0; i < k; i++) {
            if (isEmpty(i)) {

            }
        }

        duplicateElements.add(new Element(e));

        size++;
=======
        if (size == 0) {
            array.add(new ArrayElement[] { new ArrayElement(e) });
            size++;
            return;
        }

        int k = array.size();
        int s = (int) Math.floor(Math.log(size + 1) / Math.log(2) + 1);

        if (s > k) {
            merge(e);
            return;
        }

        ArrayElement[] tmp = new ArrayElement[] { new ArrayElement(e) };

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < (int) Math.pow(2, i); j++) {
                if (array.get(i)[j] != null && array.get(i)[j].isDeleted) {
                    array.get(i)[j] = new ArrayElement(e);
                    quickSort(array.get(i), 0, array.get(i).length - 1);
                    return;
                }
            }
        }
<<<<<<< HEAD
        if (((size + 1) & 1) == 0) {
            int level = 0;
            int l = 1;
            while (((size + 1) & l) == 0) {
                l  = l * 2;
                level++;
            }
            merge(0, level, e);
        } else {
            array.set(0, tmp);
            size++;
        }
=======
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94
    }

    /**
     * Find wrapper
     * @param e
     */
    public void find(int e) {
        System.out.println((findElement(e) != null) ? "true" : "false");
    }

    /**
     * Časovna kompleksnost: O(log^2 n)
     * @param e
     * @return element if element was found, null otherwise
     */
    private ArrayElement findElement(int e) {
        if (!elementInArray(e)) {
            return null;
        }

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        /***
         * Časovna kompleksnost: O(log n)
         */
        for (int i = 0; i < k; i++) {
<<<<<<< HEAD
            // if A_i is empty continue
=======
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
            if (isEmpty(i)) continue;

            int low = 0;
            int high = (int) Math.pow(2, i) - 1;

            if (array.get(i)[low].compareTo(e) == 1 || array.get(i)[high].compareTo(e) == -1) continue;

            /***
             * Časovna kompleksnost: O(log n)
             */
            while (low <= high) {
                int j = low + ((high - low) / 2);
                if (array.get(i)[j].compareTo(e) == 0) {
                    return array.get(i)[j];
                };
                if (array.get(i)[j].compareTo(e) == -1) {
                    low = j + 1;
                } else if (array.get(i)[j].compareTo(e) == 1) {
                    high = j - 1;
                }
            }
        }
        return null;
    }

    /**
     * Delete wrapper
     * @param e
     */
    public void delete(int e) {
        System.out.println((deleteElement(e) ? "true" : "false"));
    }

    /**
     * Časovna kompleksnost: O(log^2 n)
     * @param e
     * @return true if element was deleted, false otherwise
     */
    private boolean deleteElement(int e) {
        if (!elementInArray(e)) {
            return false;
        }

        ArrayElement element = findElement(e);
        if (element != null) {
            element.lazyDelete();
            removeFromFilter(element.value);
            checkNthArray();
            return true;
        }

        return false;
    }

    public void printOut() {
        if (size == 0) {
            System.out.println("empty");
            return;
        }

        int k = array.size();
        for (int i = 0; i < k; i++) {
            System.out.printf("%s%d: ", "A_", i);
            if (!isEmpty(i)) {
<<<<<<< HEAD
                if (array.get(i)[0].isDeleted) {
=======
<<<<<<< HEAD
                if (array.get(i)[0].getValue() != null) {
//                    System.out.printf("%d/%d", array.get(i)[0].getValue(), array.get(i)[0].getDuplicateCounter());
=======
                if (array.get(i)[0] != null) {
                    System.out.printf("%d/%d", array.get(i)[0].getValue(), array.get(i)[0].getDuplicateCounter());
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
                } else if (array.get(i)[0].isDeleted) {
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94
                    System.out.printf("x");
                } else {
                    System.out.printf("%d/%d", array.get(i)[0].getValue(), array.get(i)[0].getDuplicateCounter());
                }
                for (int j = 1; j < (int) Math.pow(2, i); j++) {
<<<<<<< HEAD
                    if (array.get(i)[j].isDeleted) {
=======
<<<<<<< HEAD
                    if (array.get(i)[j].getValue() != null) {
//                        System.out.printf(", %d/%d", array.get(i)[j].getValue(), array.get(i)[j].getDuplicateCounter());
                    } else if (array.get(i)[j].isDeleted) {
=======
                    if (array.get(i)[j] != null) {
                        System.out.printf(", %d/%d", array.get(i)[j].getValue(), array.get(i)[j].getDuplicateCounter());
                    } else if (array.get(i)[j] != null && array.get(i)[j].isDeleted) {
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94
                        System.out.printf(", x");
                    } else {
                        System.out.printf(", %d/%d", array.get(i)[j].getValue(), array.get(i)[j].getDuplicateCounter());
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
        while (e >= 1) {
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

<<<<<<< HEAD
    private void getDuplicateCount(Element e) {
        duplicateElements.stream()
                .collect(Collectors.groupingBy(Element::getValue, Collectors.counting()))
                .values()
                .stream()
                .filter(value -> value.intValue() == e.getValue())
                .count();
    }

    private static void refactor() {

=======
    private void removeFromFilter(int e) {
        while (e > 1) {
            bloomFilter[e % 10]--;
            e = e / 10;
        }
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
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
        quickSort(mergedArray, 0, n - 1);
        size++;
        array.add(mergedArray);
    }

<<<<<<< HEAD
    private void merge(int start, int end, int e) {
        int n = (int) Math.pow(2, end);
        ArrayElement[] mergedArray = new ArrayElement[n];

        int l = 0;
        for (int i = start; i < end; i++) {
            int j = 0;
            while (j < Math.pow(2, i)) {
                mergedArray[l] = array.get(i)[j];
                j++;
                l++;
            }
            array.set(i, new ArrayElement[j]);
        }
        mergedArray[n - 1] = new ArrayElement(e);
        quickSort(mergedArray, 0, n - 1);
        size++;
        array.set(end, mergedArray);
    }

    private void quickSort(ArrayElement[] arr, int low, int high) {
        if (arr.length == 1) return;
        if (low < high) {
            int mid = partition(arr, low, high);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid + 1, high);
        }
    }

    private int partition(ArrayElement[] arr, int low, int high) {
        ArrayElement pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            int e = pivot.getValue();
            if (arr[j].compareTo(e) == -1) {
                i++;
                ArrayElement tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        ArrayElement tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    private void checkNthArray() {
        boolean isSet = false;
        int n = array.size() - 1;
        for (int i = 0; i < array.get(n).length; i++) {
            if (!array.get(n)[i].isDeleted) {
                isSet = true;
                break;
            }
        }

        if (!isSet) {
            size -= array.get(n).length;
            array.remove(n);
        }
    }

=======
<<<<<<< HEAD
    class Element implements Comparable<Integer> {
        private Integer value;
//        int duplicateCounter;
        boolean isDeleted;

        Element(Integer value) {
            this.value = value;
//            duplicateCounter = 0;
=======
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94
    class ArrayElement implements Comparable<Integer> {
        private int value;
        int duplicateCounter;
        boolean isDeleted;

        public ArrayElement(int value) {
            this.value = value;
<<<<<<< HEAD
            duplicateCounter = 1;
=======
            duplicateCounter = 0;
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94
            isDeleted = false;
        }

        @Override
        public int compareTo(Integer x) {
            return value > x ? 1 : value == x ? 0 : -1;
        }

        public int getValue() {
            return value;
        }

<<<<<<< HEAD
        public int getDuplicateCounter() {
            return duplicateCounter;
        }
=======
<<<<<<< HEAD
        public void setDuplicateCounter(int x) {
//            duplicateCounter += x;
=======
        public void incrementDuplicateCounter() {
            duplicateCounter++;
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
        }

//        public int getDuplicateCounter() {
//            return duplicateCounter;
//        }
>>>>>>> 5e5d9d65b464d360cb8541fb1768999cc37baf94

        public void lazyDelete() {
<<<<<<< HEAD
            isDeleted = true;
//            duplicateCounter--;
        }
    }

    class TypeCastException extends Exception {
        TypeCastException(String errorMessage) {
            super(errorMessage);
=======
            if (--duplicateCounter == 0) {
                isDeleted = true;
            }
>>>>>>> 09808f6d364c43a6c9520b0011c73bc63d4b15a0
        }
    }
}
