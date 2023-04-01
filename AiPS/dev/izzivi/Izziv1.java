/*
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
    @Override
    public boolean isEmpty() { return size == 0; }
    @Override
    public boolean isFull() {
        return size == DEFAULT_CAPACITY;
    }
    @Override
    public int size() { return size; }
    private int next(int i ) { return (i + 1) % DEFAULT_CAPACITY; }
    private int prev(int i) { return (DEFAULT_CAPACITY + i - 1) % DEFAULT_CAPACITY; }
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
    @Override
    public T front() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return a[next(front)];
    }
    @Override
    public T back() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return top();
    }
    @Override
    public void enqueue(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
        push(x);
    }
    @Override
    public void enqueueFront(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
        front = prev(front);
        a[front] = x;
        size++;
    }
    @Override
    public T dequeue() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        front = next(front);
        T o = a[front];
        a[front] = null;
        size--;
        return o;
    }
    @Override
    public T dequeueBack() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return pop();
    }
    @Override
    public T top() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        return a[prev(back)];
    }
    @Override
    public void push(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
        a[back] = x;
        back = next(back);
        size++;
    }
    @Override
    public T pop() throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        back = prev(back);
        T o = a[back];
        a[back] = null;
        size--;
        return o;
    }
    private int index(int i) { return (front + i) % DEFAULT_CAPACITY; }
    @Override
    public T get(int i) throws CollectionException {
        if (isEmpty()) throw new CollectionException(ERR_MSG_EMPTY);
        if (i < 0 || i > this.size() - 1) throw new CollectionException(ERR_MSG_INDEX);
        return a[index(i)];
    }
    @Override
    public void add(T x) throws CollectionException {
        if (isFull()) throw new CollectionException(ERR_MSG_FULL);
        push(x);
    }
}

public class Izziv1 {
    public static void main(String[] args) {
        try {
            Stack<String> s = new ArrayDeque<>();
            Deque<String> d = new ArrayDeque<>();
            Sequence<String> z = new ArrayDeque<>();

            s.push("ABC");
            s.push("DEF");
            s.push("GHI");
            System.out.println(s.toString());

            System.out.println("Stack: ");
            while (!s.isEmpty()) {
                System.out.println(s.top() + " ");
                d.enqueueFront(s.pop());
            }

            System.out.println("Deque: ");
            System.out.println("Back of d: " + d.back());
            System.out.println("Front of d: " + d.front());
            while (!d.isEmpty()) {
                System.out.println(d.back() + " ");
                z.add(d.dequeueBack());
            }
            System.out.println("Enqueuing 'JKL' to d...");
            d.enqueue("JKL");
            System.out.println(d.toString());
            System.out.println("Dequeuing from d...");
            d.dequeue();
            System.out.println(d.toString());

            System.out.println("Sequence: ");
            for (int i = 0; i < z.size(); i++) {
                System.out.println((i + 1) + ". " + z.get(i) + " ");
            }
            System.out.println("Adding 'JKL' to sequence z...");
            z.add("JKL");
            System.out.println(z.toString());
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }
}
*/
