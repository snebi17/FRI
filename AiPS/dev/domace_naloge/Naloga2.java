import java.util.Arrays;
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

class DynamicArray<T extends Comparable> implements Collection {
    private int DEFAULT_CAPACITY = 6;
    private T[] array;
    private int[] inputData;
    private int size;
    private String mode, sort, order;

    public DynamicArray(/*String[] ukazi, String[] podatki*/) {
        /*array = (T[]) (new Comparable[DEFAULT_CAPACITY]);
        mode = ukazi[0];
        sort = ukazi[1];
        order = ukazi[2];
        for (int i = 0; i < podatki.length; i++) {
            array[i] = (Comparable<T>) ;
        }
        readCommands(sort);*/
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }
    private int rightChild(int i) {
        return 2 * i + 2;
    }
    private int parent(int i) {
        return (i - 1) / 2;
    }

    public void readCommands(String x) {
        switch (x) {
            case "insert": {
                insert();
                break;
            }
            case "select": {
                select();
                break;
            }
            case "bubble": {
                bubble();
                break;
            }
            case "heap": {
                heap();
                break;
            }
            case "merge": {
                merge();
                break;
            }
            case "quick": {
                quick();
                break;
            }
            case "radix": {
                radix();
                break;
            }
            case "bucket": {
                bucket();
                break;
            }
            default: break;
        }
    }

    public void insert() {

    }

    public void select() {

    }

    public void bubble() {

    }

    public void heap() {
        int[] a = { 1, 4, 1, 2, 2, 0, 2, 1, 9, 5, 10, 22, 312, 32, 4, 6, 10, 2302, 20, 139, 29, 40, 9, 0, 192, 3, 12312 };
        int n = a.length;
        int start = (n / 2) - 1;

        for (int i = start; i >= 0; i--) {
            minHeap(a, n, i);
        }
        System.out.printf("%d", a[0]);
        for (int i = 1; i < a.length; i++) {
            System.out.printf(" %d", a[i]);
        }
        System.out.println();

        for (int i = n - 1; i > 0; i--) {
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            minHeap(a, i, 0);
            System.out.printf("%d", a[0]);
            for (int j = 1; j < i; j++) {
                System.out.printf(" %d", a[j]);
            }
            System.out.printf("%s", " |");
            for (int j = i; j < a.length; j++) {
                System.out.printf(" %d", a[j]);
            }
            System.out.println();
        }
    }

    private void maxHeap(int[] a, int n, int i) {
        int root = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < n && a[left] > a[root])  {
            root = left;
        }
        if (right < n && a[right] > a[root]) {
            root = right;
        }
        if (root != i) {
            int tmp = a[i];
            a[i] = a[root];
            a[root] = tmp;
            maxHeap(a, n, root);
        }
    }

    private void minHeap(int[] a, int n, int i) {
        int root = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left > n && a[left] > a[root])  {
            root = left;
        }
        if (right > n && a[right] > a[root]) {
            root = right;
        }
        if (root != i) {
            T tmp = array[i];
            array[i] = array[root];
            array[root] = tmp;
            minHeap(a, n, root);
        }
    }

    private void swap(int , int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public void merge() {

    }

    public void quick() {

    }

    public void radix() {

    }

    public void bucket() {

    }

    @Override public int size() {
        return size;
    }
    @Override public boolean isEmpty() { return size == 0; }
    @Override public void resize() {
        DEFAULT_CAPACITY = 2 * DEFAULT_CAPACITY;
        T[] tmpArray = (T[]) (new Comparable[DEFAULT_CAPACITY]);
        for (int i = 0; i < array.length; i++) {
            tmpArray[i] = array[i];
        }
        array = tmpArray;
    }
    @Override public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(array[0].toString());
        for (int i = 1; i < array.length; i++) {
            s.append(" " + array[i].toString());
        }
        return s.toString();
    }
}

public class Naloga2 {
    public static void main(String[] args) {
        /*DynamicArray polje;
        String[] ukazi, podatki;
        Scanner sc = new Scanner(System.in);
        ukazi = sc.nextLine().split(" ");
        podatki = sc.nextLine().split(" ");
        polje = new DynamicArray(ukazi, podatki);*/
        DynamicArray polje = new DynamicArray();
        polje.heap();
    }
}
