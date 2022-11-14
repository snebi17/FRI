import java.sql.Array;

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}


interface Stack<T> extends Collection {
    T top() throws CollectionException; // Poglej vrh sklada
    void push(T x) throws CollectionException; // Dodaj na vrh sklada
    T pop() throws CollectionException; // Odvzemi vrh sklada
}


interface Deque<T> extends Collection {
    T front() throws CollectionException; // Kateri je prvi element?
    T back() throws CollectionException; // Kateri je zadnji element?
    void enqueue(T x) throws CollectionException; // Dodaj na koncu
    void enqueueFront(T x) throws CollectionException; // Dodaj na začetku
    T dequeue() throws CollectionException; // Odvzemi na začetku
    T dequeueBack() throws CollectionException; // Odvzemi na koncu
}


interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException; // Izpis elementa na indeksu i
    void add(T x) throws CollectionException; // Dodajanje elementa na konec
}

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] a;
    private int front, back, size;

    public ArrayDeque(){
        a = (T[]) (new Object[DEFAULT_CAPACITY]);
        front = 0;
        back = 0;
        size = 0;
    }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() {
        return size == DEFAULT_CAPACITY;
    }
    public int size() { return size; }
    private int next(int i ) {
        return (i + 1) % DEFAULT_CAPACITY;
    }
    private int prev(int i) {
        return (DEFAULT_CAPACITY + i - 1) % DEFAULT_CAPACITY;
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (size > 0) {
            sb.append(a[front].toString());
        }
        for (int i = 0; i < size - 1; i++) {
            sb.append(", " + a[next(front + i)].toString());
        }
        sb.append("]");
        return sb.toString();
    }
    public T front() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return a[front];
    }
    public T back() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return top();
    }
    public void enqueue(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
        return push(x);
    }
    public void enqueueFront(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
    }
    public T dequeue() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return a[prev(front)];
    }
    public T dequeueBack() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return a[prev(back)];
    }
    public T top() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return a[prev(back)];
    }
    public void push(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
        a[back] = x;
        back = next(back);
        size++;
    }
    public T pop() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        back = prev(back);
        T o = a[back];
        a[back] = null;
        size--;
        return o;
    }
    private int index(int i) {
        return (front + i) % DEFAULT_CAPACITY;
    }
    public T get(int i) throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        if (i < 0 || i > this.size() - 1) throw new CollectionException(ERR_MSG_INDEX);
        return a[index(i)];
    }
    public void add(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
        a[next(front)] = x;
        front = next(front);
        size++;
    }
}

public class Izziv1 {
    public static void main(String[] args) {
        ArrayDeque<Integer> arrayD = new ArrayDeque<>();
        arrayD.isEmpty();
        arrayD.isFull();
        arrayD.back();
    }
}
