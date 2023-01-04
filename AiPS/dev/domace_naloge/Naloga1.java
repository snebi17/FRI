import java.util.Scanner;

@SuppressWarnings("unchecked")
class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Collection {
    String ERR_MSG_EMPTY = "Collection is empty.";
    String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}

@SuppressWarnings("unchecked")
interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
}

@SuppressWarnings("unchecked")
interface Sequence<T> extends Collection {
    String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
}

@SuppressWarnings("unchecked")
class ArrayDeque<T> implements Stack<T>, Sequence<T> {
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
        if (size > 0) {
            sb.append(a[front].toString());
        }
        for (int i = 0; i < size - 1; i++) {
            sb.append(" " + a[next(front + i)].toString());
        }
        return sb.toString();
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

@SuppressWarnings("unchecked")
class Calculator {
    private Sequence<Stack<String>> zaporedje = new ArrayDeque<>();
    private Stack<String> glavniSklad = new ArrayDeque<>();

    private boolean condFlag = false;
    private boolean isFunction = false;
    private int commandStack;
    private int funCounter = 0;

    public Calculator() {
        try {
            for (int i = 0; i < 42; i++) {
                Stack<String> s = new ArrayDeque<>();
                zaporedje.add(s);
            }
            glavniSklad = zaporedje.get(0);
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    public void readCommands(String x) {
        if (isFunction) {
            fun(x);
            return;
        }
        if (x.startsWith("?")) {
            x = x.substring(1);
            if (condFlag) {
                executeCommand(x);
                return;
            }
        } else {
            executeCommand(x);
        }
    }

    private void executeCommand(String x) {
        try {
            switch (x) {
                case "echo": {
                    echo();
                    break;
                }
                case "pop": {
                    pop();
                    break;
                }
                case "dup": {
                    dup();
                    break;
                }
                case "dup2": {
                    dup2();
                    break;
                }
                case "swap": {
                    swap();
                    break;
                }
                case "print": {
                    int i = Integer.parseInt(glavniSklad.pop());
                    Stack<String> s = zaporedje.get(i);
                    print(s);
                    break;
                }
                case "char": {
                    character();
                    break;
                }
                case "even": {
                    even();
                    break;
                }
                case "odd": {
                    odd();
                    break;
                }
                case "!": {
                    int n = Integer.parseInt(glavniSklad.pop());
                    factorial(n);
                    break;
                }
                case "len": {
                    len();
                    break;
                }
                case "<>": {
                    notEqual();
                    break;
                }
                case "<": {
                    lessThan();
                    break;
                }
                case ">": {
                    greaterThan();
                    break;
                }
                case "<=": {
                    lessOrEqual();
                    break;
                }
                case "==": {
                    equal();
                    break;
                }
                case ">=": {
                    greaterOrEqual();
                    break;
                }
                case "+": {
                    add();
                    break;
                }
                case "-": {
                    sub();
                    break;
                }
                case "*": {
                    mul();
                    break;
                }
                case "/": {
                    div();
                    break;
                }
                case "%": {
                    mod();
                    break;
                }
                case ".": {
                    concat();
                    break;
                }
                case "rnd": {
                    random();
                    break;
                }
                case "then": {
                    then();
                    break;
                }
                case "else": {
                    thenElse();
                    break;
                }
                case "clear": {
                    int i = Integer.parseInt(glavniSklad.pop());
                    Stack<String> s = zaporedje.get(i);
                    clear(s);
                    break;
                }
                case "run": {
                    int i = Integer.parseInt(glavniSklad.pop());
                    Stack<String> s = zaporedje.get(i);
                    run(s);
                    break;
                }
                case "fun": {
                    commandStack = Integer.parseInt(glavniSklad.pop());
                    funCounter = Integer.parseInt(glavniSklad.pop());
                    isFunction = true;
                    break;
                }
                case "loop": {
                    int i = Integer.parseInt(glavniSklad.pop());
                    int n = Integer.parseInt(glavniSklad.pop());
                    Stack<String> s = zaporedje.get(i);
                    loop(s, n);
                    break;
                }
                case "move": {
                    int i = Integer.parseInt(glavniSklad.pop());
                    int n = Integer.parseInt(glavniSklad.pop());
                    Stack<String> s = zaporedje.get(i);
                    move(s, n);
                    break;
                }
                case "reverse": {
                    int index = Integer.parseInt(glavniSklad.pop());
                    Stack<String> s = zaporedje.get(index);
                    reverse(s);
                    break;
                }
                default:
                    glavniSklad.push(x);
                    break;
            }
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void echo() {
        try {
            System.out.println(glavniSklad.top());
        } catch (CollectionException e) {
            System.out.println();
        }
    }

    private void pop() {
        try {
            glavniSklad.pop();
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void dup() {
        try {
            glavniSklad.push(glavniSklad.top());
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void dup2() {
        try {
            String x, y;
            x = glavniSklad.pop();
            y = glavniSklad.pop();
            glavniSklad.push(y);
            glavniSklad.push(x);
            glavniSklad.push(y);
            glavniSklad.push(x);
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void swap() {
        try {
            String x, y;
            x = glavniSklad.pop();
            y = glavniSklad.pop();
            glavniSklad.push(x);
            glavniSklad.push(y);
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void print(Stack<String> s) {
        try {
            if (s.isEmpty()) {
                System.out.println();
                return;
            }
            String x = s.top();
            s.pop();
            System.out.printf("%s ", x);
            print(s);
            s.push(x);

        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void character() {
        try {
            char c = (char)Integer.parseInt(glavniSklad.pop());
            glavniSklad.push(String.valueOf(c));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void even() {
        try {
            int x = Integer.parseInt(glavniSklad.pop());
            if (x % 2 == 0) {
                glavniSklad.push("1");
            } else {
                glavniSklad.push("0");
            }
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void odd() {
        try {
            int x = Integer.parseInt(glavniSklad.pop());
            if (x % 2 != 0)
                glavniSklad.push("1");
            else
                glavniSklad.push("0");
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void factorial(int x) {
        int fac = 1;
        while (x > 0) {
            fac = fac * x;
            x--;
        }
        try {
            glavniSklad.push(String.valueOf(fac));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void len() {
        try {
            String s = glavniSklad.pop();
            glavniSklad.push(String.valueOf(s.length()));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void notEqual() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            if (x != y)
                glavniSklad.push("1");
            else
                glavniSklad.push("0");
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void equal() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            if (x == y)
                glavniSklad.push("1");
            else
                glavniSklad.push("0");
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void lessThan() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            if (x < y)
                glavniSklad.push("1");
            else
                glavniSklad.push("0");
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void greaterThan() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            if (x > y)
                glavniSklad.push("1");
            else
                glavniSklad.push("0");
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void lessOrEqual() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            if (x <= y)
                glavniSklad.push("1");
            else
                glavniSklad.push("0");
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void greaterOrEqual() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            if (x >= y)
                glavniSklad.push("1");
            else
                glavniSklad.push("0");
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void add() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            glavniSklad.push(String.valueOf(x + y));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void sub() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            glavniSklad.push(String.valueOf(x - y));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void mul() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            glavniSklad.push(String.valueOf(x * y));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void div() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            glavniSklad.push(String.valueOf(x / y));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void mod() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            glavniSklad.push(String.valueOf(x % y));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void concat() {
        try {
            String y = glavniSklad.pop();
            String x = glavniSklad.pop();
            glavniSklad.push(x.concat(y));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void random() {
        try {
            int y = Integer.parseInt(glavniSklad.pop());
            int x = Integer.parseInt(glavniSklad.pop());
            int min, max;
            if (x > y) {
                min = y;
                max = x;
            } else {
                min = x;
                max = y;
            }
            int rnd = min + (int)(Math.random() * (max - min + 1));
            glavniSklad.push(String.valueOf(rnd));
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void then() {
        try {
            int x = Integer.parseInt(glavniSklad.pop());
            if (x != 0) {
                condFlag = true;
                return;
            }
            condFlag = false;
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void thenElse() {
        condFlag = !condFlag;
    }

    private void clear(Stack<String> s) {
        try {
            while (!s.isEmpty()) {
                s.pop();
            }
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void run(Stack<String> s){
        if (s.isEmpty()) {
            return;
        }
        String[] str = s.toString().split(" ");
        for (int i = 0; i < str.length; i++) {
            if (str[i].startsWith("?")) {
                String x = str[i].substring(1);
                if (condFlag) {
                    executeCommand(x);
                }
            } else {
                executeCommand(str[i]);
            }
        }
    }

    private void loop(Stack<String> s, int n) {
        while (n > 0) {
            run(s);
            n--;
        }
    }

    private void fun(String x) {
        if (funCounter == 0) {
            isFunction = false;
            executeCommand(x);
            return;
        }
        try {
            Stack<String> s = zaporedje.get(commandStack);
            s.push(x);
            funCounter--;
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void move(Stack<String> s, int n) {
        try {
            while (n > 0) {
                s.push(glavniSklad.pop());
                n--;
            }
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }

    private void reverse(Stack<String> s) {
        if (s.isEmpty()) {
            return;
        }
        try {
            Stack<String> firstS = new ArrayDeque<>();
            Stack<String> secondS = new ArrayDeque<>();
            while (!s.isEmpty()) {
                firstS.push(s.pop());
            }
            while (!firstS.isEmpty()) {
                secondS.push(firstS.pop());
            }
            while (!secondS.isEmpty()) {
                s.push(secondS.pop());
            }
        } catch (CollectionException e) {
            System.out.println(e);
        }
    }
}

public class Naloga1 {
    public static void main(String[] args) {
        String vrstica, niz;
        Scanner sc_v, sc_n;
        sc_v = new Scanner(System.in);
        while (sc_v.hasNextLine()) {
            Calculator kalkulator = new Calculator();
            vrstica = sc_v.nextLine();
            sc_n = new Scanner(vrstica);
            while (sc_n.hasNext()) {
                niz = sc_n.next();
                kalkulator.readCommands(niz);
            }
            kalkulator = null;
        }
    }



    static void sort(int[] a) {
        int min;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[i]) {
                    min = 
                }
            }
        }
    }
}
