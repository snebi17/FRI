class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";

    boolean isEmpty();
    int size();
    String toString();
}

interface Queue<T> extends Collection {
    T front() throws CollectionException;
    void enqueue(T x);
    T dequeue() throws CollectionException;
}

interface PriorityQueue<T extends Comparable> extends Queue<T> {
    void enqueue(T x);
    T dequeue();
    T front();
}

abstract class AbstractArrayPQ<T extends Comparable> implements PriorityQueue<T> {
    protected T[] array;
    @Override public boolean isEmpty() { return size() == 0; }
    @Override public int size() {
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                n++;
            }
        }
        return n;
    }
    @Override public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(array[0]);
        for (int i = 1; i < size(); i++) {
            s.append(" " + array[i]);
        }
        return s.toString();
    }
}

class ArrayPQ<T extends Comparable> extends AbstractArrayPQ<T> {
    private int front;
    private int DEFAULT_CAPACITY = 64;
    public int m, c;

    public ArrayPQ() {
        array = (T[]) (new Comparable[DEFAULT_CAPACITY]);
        front = 0;
    }

    @Override public void enqueue(T x) {
        if (size() == DEFAULT_CAPACITY) {
            resize();
        }
        array[front++] = x;
    }
    @Override public T dequeue() {
        T max = array[0];
        int index = 0;
        for (int i = 1; i < size() - 1; i++) {
            c++;
            if (array[i].compareTo(max) > 0) {
                m++;
                max = array[i];
                index = i;
            }
        }
        m++;
        T last = array[--front];
        array[index] = last;
        array[front] = null;
        return max;
    }
    @Override public T front() {
        return array[0];
    }
    private void resize() {
        DEFAULT_CAPACITY = 2 * DEFAULT_CAPACITY;
        T[] resizedArray = (T[]) (new Comparable[DEFAULT_CAPACITY]);
        for (int i = 0; i < size(); i++) {
            resizedArray[i] = array[i];
            m++;
        }
        array = resizedArray;
    }
}

class ArrayHeapPQ<T extends Comparable> extends AbstractArrayPQ<T> {
    private int front;
    private int DEFAULT_CAPACITY = 64;
    public int m, c;

    public ArrayHeapPQ() {
        array = (T[]) (new Comparable[DEFAULT_CAPACITY]);
        front = 0;
    }

    @Override public void enqueue(T x) {
        if (size() == DEFAULT_CAPACITY) {
            resize();
        }
        array[front++] = x;
        for (int i = size() - 1; i > 0; i = (int) Math.floor((i - 1) / 2)) {
            // če je starš manjši, ga zamenjaj, če ni, lahko ustavimo sift-up
            int p = (int) Math.floor((i - 1) / 2);
            c++;
            if (array[p].compareTo(array[i]) < 0) {
                T tmp = array[p];
                array[p] = array[i];
                array[i] = tmp;
                m += 3;
            } else {
                break;
            }
        }
    }
    @Override public T dequeue() {
        T root = array[0];
        array[0] = array[--front];
        // če je starš manjši od levega ali desnega, ga zamenjaj, če ne lahko ustavimo sift-down
        for (int i = 0; i < size(); i = i * 2) {
            // če je levi večji od starša in levega
            c += 2;
            if (array[i + 1].compareTo(array[i]) > 0 && array[i + 1].compareTo(array[i + 2]) > 0) {
                T tmp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = tmp;
                m += 3;
            }
            // če je desni večji od starša in desnega
            else if (array[i + 2].compareTo(array[i]) > 0 && array[i + 2].compareTo(array[i + 1]) > 0) {
                T tmp = array[i + 2];
                array[i + 2] = array[i];
                array[i] = tmp;
                m += 3;
            } else {
                break;
            }
        }
        return root;
    }
    @Override public T front() {
        return array[0];
    }
    private void resize() {
        DEFAULT_CAPACITY = 2 * DEFAULT_CAPACITY;
        T[] resizedArray = (T[]) (new Comparable[DEFAULT_CAPACITY]);
        for (int i = 0; i < size(); i++) {
            resizedArray[i] = array[i];
            m++;
        }
        array = resizedArray;
    }
}

class Node<T extends Comparable> {
    T item;
    Node parent, left, right;
    Node() {
        parent = null;
        left = null;
        right = null;
    }
    Node(T x) {
        item = x;
        parent = null;
        left = null;
        right = null;
    }
}

class LinkedHeapPQ<T extends Comparable> implements PriorityQueue<T>{
    private int size;
    private Node root;
    public int m, c;
    private boolean isLeft = true;
    @Override public boolean isEmpty() { return size == 0; }
    @Override public int size() { return size; }
    @Override public String toString() { return ""; }

    public LinkedHeapPQ() {
        root = new Node();
        size = 0;
    }
    @Override public void enqueue(T x) {
        if (isEmpty()) {
            root.item = x;
            size++;
            return;
        }

        String bin = Integer.toBinaryString(size + 1).substring(1);
        Node node = root;
        Node parent = null;
        for (int i = 0; i < bin.length(); i++) {
            parent = node;
            if (bin.charAt(i) == '0') {
                node = node.left;
                isLeft = true;
            } else {
                node = node.right;
                isLeft = false;
            }
        }

        node = new Node(x);
        if (parent == null) {
            parent = root;
        }
        if (isLeft) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        node.parent = parent;
        siftUp(node);
        size++;
    }
    @Override public T dequeue() {
        T item = (T) root.item;

        // poiščemo zadnji node in nastavimo root.item = node.item ter zbrisemo ta node
        Node node = root;
        String bin = Integer.toBinaryString(size).substring(1);
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '0') {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        root.item = node.item;
        node = null;

        Node n = root;
        // izvedemo sift down
        siftDown(n);

        return item;
    }
    @Override public T front() {
        return (T) root.item;
    }

    private void siftUp(Node n) {
        while (true) {
            c += 2;
            if (n.parent == null || n.item.compareTo(n.parent.item) <= 0)
                break;
            T tmp = (T) n.item;
            n.item = n.parent.item;
            n.parent.item = tmp;
            n = n.parent;
            m += 3;
        }
    }

    private void siftDown(Node n) {
        while (true) {
            c += 2;
            T tmp = (T) n.item;
            if (n.left == null) {
                if (n.right == null) {
                    break;
                }
                n.item = n.right.item;
                n.right.item = tmp;
                n = n.right;
            } else {
                if (n.right == null) {
                    n.item = n.left.item;
                    n.left.item = tmp;
                    n = n.left;
                } else {
                    if (n.item.compareTo(n.left.item) > 0 && n.item.compareTo(n.right.item) > 0)
                        break;
                    if (n.left.item.compareTo(n.right.item) >= 0) {
                        n.item = n.left.item;
                        n.left.item = tmp;
                        n = n.left;
                    } else {
                        n.item = n.right.item;
                        n.right.item = tmp;
                        n = n.right;
                    }
                }
            }
            m += 3;
            c += 1;
        }
    }
}

public class Izziv3 {
    public static void main(String[] args) {
        ArrayPQ APQ = new ArrayPQ<Integer>();
        ArrayHeapPQ AHPQ = new ArrayHeapPQ<Integer>();
        LinkedHeapPQ LHPQ = new LinkedHeapPQ<Integer>();
        int r;

        long APQms = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            r = (int) (Math.random() * 999) + 1;
            APQ.enqueue(r);
        }
        for (int i = 0; i < 999; i++) {
            r = (int) (Math.random() * 999) + 1;
            APQ.dequeue();
            APQ.enqueue(r);
            APQ.front();
        }
        APQms = System.currentTimeMillis() - APQms;

        long AHPQms = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            r = (int) (Math.random() * 999) + 1;
            AHPQ.enqueue(r);
        }
        for (int i = 0; i < 1000; i++) {
            r = (int) (Math.random() * 999) + 1;
            AHPQ.dequeue();
            AHPQ.enqueue(r);
            AHPQ.front();
        }
        AHPQms = System.currentTimeMillis() - AHPQms;

        long LHPQms = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            r = (int) (Math.random() * 999) + 1;
            LHPQ.enqueue(r);
        }
        for (int i = 0; i < 1000; i++) {
            r = (int) (Math.random() * 999) + 1;
            LHPQ.enqueue(r);
            LHPQ.dequeue();
            LHPQ.front();
        }
        LHPQms = System.currentTimeMillis() - LHPQms;

        System.out.println("Implementacija                     Čas [ms]           Premikov             Primerjav");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-35s%-19d%-21d%-11d\n", "Neurejeno polje (64,2x)", APQms, APQ.m, APQ.c);
        System.out.printf("%-35s%-19d%-21d%-11d\n", "Implicitna kopica (64,2x)", AHPQms, AHPQ.m, AHPQ.c);
        System.out.printf("%-35s%-19d%-21d%-11d", "Eksplicitna kopica", LHPQms, LHPQ.m, LHPQ.c);
    }
}