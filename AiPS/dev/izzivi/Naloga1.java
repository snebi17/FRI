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
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
}


interface Deque<T> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    void enqueueFront(T x) throws CollectionException;
    T dequeue() throws CollectionException;
    T dequeueBack() throws CollectionException;
}


interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
}

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    public boolean isEmpty() {
        return false;
    }
    public boolean isFull() {
        return true;
    }
    public int size() {
        return 1;
    }
    public String toString() {
        return "";
    }
    public T front() throws CollectionException {
        
    }
    public T back() throws CollectionException {

    };
    public void enqueue(T x) throws CollectionException {

    };
    public void enqueueFront(T x) throws CollectionException {

    };
    public void dequeue() throws CollectionException {

    };
    public T dequeueBack() throws CollectionException {

    };
    public T top() throws CollectionException {

    };
    public void push(T x) throws CollectionException {

    };
    public T pop() throws CollectionException {

    };
    public T get(int i) throws CollectionException {

    };
    public void add(T x) throws CollectionException {

    };
}

public class Naloga1 {
    public static void main(String[] args) {

    }
}

// CollectionException - izjema, ki jo vrnete ob težavah (preliv oz. podliv sklada oz. vrste),
// Collection - osnovni vmesnik za zbirko elementov,
// Stack<T> - generični vmesnik (abstraktni podatkovni tip) za sklad,
// Deque<T> - generični vmesnik za vrsto z dvema koncema in
// Sequence<T> - generični vmesnik za zaporedje.
