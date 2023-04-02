/*
import java.util.Scanner;

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Collection {
    String ARRAY_RESIZE = "Array has been resized.";

    void resize();
    boolean isEmpty();
    int size();
    String toString();
}

abstract class SortingAlgorithm implements Collection {
    protected int DEFAULT_CAPACITY = 64;
    protected int size = 0;
    protected boolean trace, order;
    protected int[] count = { 0, 0 };
    protected int counter = 0;
    protected Integer[] array;
    SortingAlgorithm(boolean trace, boolean order, String[] input) {
        this.trace = trace;
        this.order = order;
        array = new Integer[DEFAULT_CAPACITY];
        for (int i = 0; i < input.length; i++) {
            if (i == array.length) {
                resize();
            }
            array[i] = Integer.parseInt(input[i]);
        }
        int n = 0;
        for (int i = 0; i < array.length; i++, n++) {
            if (array[i] == null) {
                break;
            }
        }
        Integer[] fullArray = new Integer[n];
        for (int i = 0; i < n; i++) {
            fullArray[i] = array[i];
        }
        array = fullArray;
    }
    void swap(int i, int j) {
        Integer tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        count[0] += 3;
    }

    int compare(Integer a, Integer b) {
        count[1]++;
        return a.compareTo(b);
    }

    void trace(int i) {
        if (i == 0)
            return;
        System.out.printf("%d", array[0]);
        for (int j = 1; j < i; j++) {
            System.out.printf(" %d", array[j]);
        }
        if (i != array.length) {
            System.out.printf(" %s", "|");
            for (int j = i; j < array.length; j++) {
                System.out.printf(" %d", array[j]);
            }
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void resize() {
        DEFAULT_CAPACITY = DEFAULT_CAPACITY * 2;
        Integer[] resizedArray = new Integer[DEFAULT_CAPACITY];
        for (int i = 0; i < array.length; i++) {
            resizedArray[i] = array[i];
        }
        array = resizedArray;
    }

    public int size() {
        return array.length;
    }
}

// DELA
class InsertionSort extends SortingAlgorithm {
    InsertionSort(boolean trace, boolean order, String[] input) {
        super(trace, order, input);
    }
    public void sort() {
        if (counter == 3) {
            return;
        }
        if (counter == 2) {
            order = !order;
        }
        if (trace) {
            trace(array.length);
        }
        for (int i = 1; i < array.length; i++) {
            count[0]++;
            for (int j = i; j > 0; j--) {
                if (order) {
                    if (compare(array[j], array[j - 1]) < 0) {
                        Integer tmp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = tmp;
                        count[0]++;
                    } else {
                        break;
                    }
                } else {
                    if (compare(array[j], array[j - 1]) > 0) {
                        Integer tmp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = tmp;
                        count[0]++;
                    } else {
                        break;
                    }
                }
            }
            count[0]++;
            if (trace) {
                if (i == array.length - 1) {
                    System.out.printf("%d", array[0]);
                    for (int j = 1; j < array.length; j++) {
                        System.out.printf(" %d", array[j]);
                    }
                    System.out.printf(" |");
                } else {
                    trace(i + 1);
                }
            }
        }
        if (!trace) {
            System.out.printf("%d %d", count[0], count[1]);
            if (counter != 2) {
                System.out.printf(" | ");
            }
            count = new int[]{ 0, 0 };
            counter++;
            sort();
        }
    }
}

//DELA
class SelectionSort extends SortingAlgorithm {
    SelectionSort(boolean trace, boolean order, String[] input) {
        super(trace, order, input);
    }
    public void sort() {
        if (counter == 3) {
            return;
        }
        if (counter == 2) {
            order = !order;
        }
        if (trace) {
            trace(array.length);
        }
        for (int i = 0; i < array.length - 1; i++) {
            Integer current = array[i];
            int index = i;
            for (int j = i; j < array.length; j++) {
                if (order) {
                    if (compare(current, array[j]) > 0) {
                        current = array[j];
                        index = j;
                    }
                } else {
                    if (compare(current, array[j]) < 0) {
                        current = array[j];
                        index = j;
                    }
                }
            }
            swap(i, index);
            if (trace) {
                trace(i + 1);
            }
        }
        if (!trace) {
            count[1] -= array.length - 1;
            System.out.printf("%d %d", count[0], count[1]);
            if (counter != 2) {
                System.out.printf(" | ");
            }
            count = new int[]{ 0, 0 };
            counter++;
            sort();
        }
    }
}

class BubbleSort extends SortingAlgorithm {
    BubbleSort(boolean trace, boolean order, String[] input) {
        super(trace, order, input);
    }
    public void sort() {
        if (counter == 3) {
            return;
        }
        if (counter == 2) {
            order = !order;
        }
        if (trace) {
            trace(array.length);
        }

        int index = 0;
        int swapIndex;
        boolean swapped;
        for (int i = array.length - 1; i >= 0; i--) {
            swapIndex = i;
            swapped = false;
            for (int j = array.length - 1; j > index; j--) {
                if (order) {
                    if (compare(array[j], array[j - 1]) < 0) {
                        swap(j, j - 1);
                        swapIndex = j;
                        swapped = true;
                    }
                } else {
                    if (compare(array[j], array[j - 1]) > 0) {
                        swap(j, j - 1);
                        swapIndex = j;
                        swapped = true;
                    }
                }
            }
            if (trace && (swapIndex != i || swapped)) {
                trace(swapIndex);
            } else if (trace && i == 0 && swapIndex != array.length - 2) {
                trace(array.length - 1);
            }
            index = swapIndex;
        }
        if (!trace) {
            System.out.printf("%d %d", count[0], count[1]);
            if (counter != 2) {
                System.out.printf(" | ");
            }
            count = new int[]{ 0, 0 };
            counter++;
            sort();
        }
    }
}

// DELA
class HeapSort extends SortingAlgorithm {
    HeapSort(boolean trace, boolean order, String[] input) {
        super(trace, order, input);
    }
    public void sort() {
        if (counter == 3) {
            return;
        }
        if (counter == 2) {
            order = !order;
        }
        if (trace) {
            System.out.printf("%d", array[0]);
            for (int i = 1; i < array.length; i++) {
                System.out.printf(" %d", array[i]);
            }
            System.out.println();
        }
        int n = array.length;
        int start = (n / 2) - 1;

        for (int i = start; i >= 0; i--) {
            heap(n, i);
        }

        if (trace) {
            System.out.printf("%d", array[0]);
            for (int i = 1; i < array.length; i++) {
                System.out.printf(" %d", array[i]);
            }
            System.out.println(" |");
        }

        for (int i = n - 1; i > 0; i--) {
            swap(0, i);
            heap(i, 0);
            if (trace) {
                trace(i);
            }
        }
        if (!trace) {
            System.out.printf("%d %d", count[0], count[1]);
            if (counter != 2) {
                System.out.printf(" | ");
            }
            count = new int[]{ 0, 0 };
            counter++;
            sort();
        }
    }
    private void heap(int n, int i) {
        int parent = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (order) {
            if (left < n && compare(array[left], array[parent]) > 0)  {
                parent = left;
            }
            if (right < n && compare(array[right], array[parent]) > 0) {
                parent = right;
            }
        } else {
            if (left < n && compare(array[left], array[parent]) < 0)  {
                parent = left;
            }
            if (right < n && compare(array[right], array[parent]) < 0) {
                parent = right;
            }
        }
        if (parent != i) {
            swap(i, parent);
            heap(n, parent);
        }
    }
}

// DELA
class MergeSort extends SortingAlgorithm {
    MergeSort(boolean trace, boolean order, String[] input) {
        super(trace, order, input);
    }
    public void sort() {
        if (counter == 3) {
            return;
        }
        if (counter == 2) {
            order = !order;
        }
        if (trace) {
            trace(array.length);
        }
        mSort(array);
        if (!trace) {
            System.out.printf("%d %d", count[0], count[1]);
            if (counter != 2) {
                System.out.printf(" | ");
            }
            count = new int[]{ 0, 0 };
            counter++;
            sort();
        }
    }
    private void mSort(Integer[] originalArray) {
        int originalLength = originalArray.length;
        if (originalLength < 2) {
            return;
        }

        Integer[] leftSide = new Integer[(originalLength + 1) / 2];
        Integer[] rightSide = new Integer[originalLength - (originalLength + 1) / 2];

        for (int i = 0; i < (originalLength + 1) / 2; i++) {
            leftSide[i] = originalArray[i];
            count[0]++;
        }
        for (int i = (originalLength + 1) / 2; i < originalLength; i++) {
            rightSide[i - (originalLength + 1) / 2] = originalArray[i];
            count[0]++;
        }

        if (trace) {
            System.out.printf("%d", leftSide[0]);
            for (int i = 1; i < leftSide.length; i++) {
                System.out.printf(" %d", leftSide[i]);
            }
            System.out.printf(" %s %d", "|", rightSide[0]);
            for (int i = 1; i < rightSide.length; i++) {
                System.out.printf(" %d", rightSide[i]);
            }
            System.out.println();
        }

        mSort(leftSide);
        mSort(rightSide);

        merge(originalArray, leftSide, rightSide);
    }
    private void merge(Integer[] originalArray, Integer[] leftSide, Integer[] rightSide) {
        int leftLength = leftSide.length;
        int rightLength = rightSide.length;

        int i = 0, j = 0, k = 0;
        while (i < leftLength && j < rightLength) {
            if (order) {
                if (compare(leftSide[i], rightSide[j]) <= 0) {
                    originalArray[k] = leftSide[i];
                    count[0]++;
                    i++;
                } else {
                    originalArray[k] = rightSide[j];
                    count[0]++;
                    j++;
                }
            } else {
                if (compare(leftSide[i], rightSide[j]) >= 0) {
                    originalArray[k] = leftSide[i];
                    count[0]++;
                    i++;
                } else {
                    originalArray[k] = rightSide[j];
                    count[0]++;
                    j++;
                }
            }
            k++;
        }

        while (i < leftLength) {
            originalArray[k] = leftSide[i];
            count[0]++;
            i++;
            k++;
        }

        while (j < rightLength) {
            originalArray[k] = rightSide[j];
            count[0]++;
            j++;
            k++;
        }

        if (trace) {
            System.out.printf("%d", originalArray[0]);
            for (i = 1; i < originalArray.length; i++) {
                System.out.printf(" %d", originalArray[i]);
            }
            System.out.println();
        }
    }
}

// DELA TRACE, COUNT ŠE NE
class QuickSort extends SortingAlgorithm {
    QuickSort(boolean trace, boolean order, String[] input) {
        super(trace, order, input);
    }
    public void sort() {
        if (counter == 3) {
            return;
        }
        if (counter == 2) {
            order = !order;
        }
        if (trace) {
            trace(array.length);
            qSort(array, 0, array.length - 1);
            trace(array.length);
        }
        if (!trace) {
            qSort(array, 0, array.length - 1);
            System.out.printf("%d %d", count[0], count[1]);
            if (counter != 2) {
                System.out.printf(" | ");
            }
            count = new int[]{ 0, 0 };
            counter++;
            sort();
        }
    }
    private void qSort(Integer[] originalArray, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        Integer pivot = originalArray[lowIndex];

        int leftIndex = lowIndex + 1;
        int rightIndex = highIndex;

        while (leftIndex <= rightIndex) {
            if (order) {
                if (compare(originalArray[leftIndex], pivot) <= 0) {
                    leftIndex++;
                } else if (compare(originalArray[rightIndex], pivot) > 0) {
                    rightIndex--;
                } else {
                    swap(originalArray, leftIndex, rightIndex);
                }
            } else {
                if (compare(originalArray[leftIndex], pivot) > 0) {
                    leftIndex++;
                } else if (compare(originalArray[rightIndex], pivot) <= 0) {
                    rightIndex--;
                } else {
                    swap(originalArray, leftIndex, rightIndex);
                }
            }
        }
        swap(originalArray, lowIndex, rightIndex);

        if (trace) {
            for (int i = lowIndex; i < highIndex; i++) {
                if (i == rightIndex) {
                    System.out.printf("| %d | ", originalArray[i]);
                } else {
                    System.out.printf("%d ", originalArray[i]);
                }
            }
            if (highIndex == rightIndex) {
                System.out.printf("| %d |\n", originalArray[highIndex]);
            } else {
                System.out.printf("%d\n", originalArray[highIndex]);
            }
        }

        qSort(originalArray, lowIndex, rightIndex - 1);
        qSort(originalArray, rightIndex + 1, highIndex);
        return;
    }

    private void swap(Integer[] array, int i, int j) {
        Integer tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        count[0] += 3;
    }
}

class RadixSort extends SortingAlgorithm {
    RadixSort(boolean mode, boolean order, String[] input) {
        super(mode, order, input);
    }
    public void sort() {
        if (counter == 3) {
            return;
        }
        if (counter == 2) {
            order = !order;
        }
        if (trace) {
            trace(array.length);
        }
        Integer orderValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (compare(orderValue, array[i]) < 0) {
                orderValue = array[i];
            }
        }

        for (int e = 1; orderValue/e > 0; e*=10) {
            rSort(array, e);
            if (trace) {
                trace(array.length);
            }
        }
        if (!trace) {
            System.out.printf("%d %d", count[0], count[1]);
            if (counter != 2) {
                System.out.printf(" | ");
            }
            count = new int[]{ 0, 0 };
            counter++;
            sort();
        }
    }

    private void rSort(Integer[] array, int exp) {
        Integer[] newArray = new Integer[array.length];
        Integer[] occurences = new Integer[10];
        for (int i = 0; i < 10; i++) {
            occurences[i] = 0;
        }

        for (int i = 0; i < array.length; i++) {
            occurences[(array[i] / exp) % 10]++;
        }

        if (order) {
            for (int i = 1; i < 10; i++) {
                occurences[i] += occurences[i - 1];
            }
        } else {
            for (int i = 9; i > 0; i--) {
                occurences[i - 1] += occurences[i];
            }
        }

        for (int i = array.length - 1; i >= 0; i--) {
            newArray[occurences[(array[i]/exp)%10] - 1] = array[i];
            count[0]++;
            occurences[(array[i]/exp)%10]--;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
            count[0]++;
        }
    }
}

class BucketSort extends SortingAlgorithm {
    BucketSort(boolean mode, boolean order, String[] input) {
        super(mode, order, input);
    }
    public void sort() {

    }
}

class SortingAlgorithms {
    SortingAlgorithms(boolean trace, String type, boolean order, String[] input) {
        switch (type) {
            case "insert": {
                InsertionSort insertionSort = new InsertionSort(trace, order, input);
                insertionSort.sort();
                break;
            }
            case "select": {
                SelectionSort selectionSort = new SelectionSort(trace, order, input);
                selectionSort.sort();
                break;
            }
            case "bubble": {
                BubbleSort bubbleSort = new BubbleSort(trace, order, input);
                bubbleSort.sort();
                break;
            }
            case "heap": {
                HeapSort heapSort = new HeapSort(trace, order, input);
                heapSort.sort();
                break;
            }
            case "merge": {
                MergeSort mergeSort = new MergeSort(trace, order, input);
                mergeSort.sort();
                break;
            }
            case "quick": {
                QuickSort quickSort = new QuickSort(trace, order, input);
                quickSort.sort();
                break;
            }
            case "radix": {
                RadixSort radixSort = new RadixSort(trace, order, input);
                radixSort.sort();
                break;
            }
            case "bucket": {
                BucketSort bucketSort = new BucketSort(trace, order, input);
                bucketSort.sort();
                break;
            }
            default: break;
        }
    }
}

public class Naloga2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] command = sc.nextLine().split(" ");
        String[] input = sc.nextLine().split(" ");

        boolean trace = command[0].equals("trace") ? true : false;
        String type = command[1];
        boolean order = command[2].equals("up") ? true : false;

        new SortingAlgorithms(trace, type, order, input);

        sc.close();
    }
}*/
