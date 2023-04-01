import java.util.Stack;
public class Naloga2 {
    static void obrni(Stack<Integer> s, int n, int m) {
        Stack<Integer> sklad1 = new Stack<>();
        Stack<Integer> sklad2 = new Stack<>();
        Stack<Integer> sklad3 = new Stack<>();
        for (int i = 0; i < n; i++) {
            sklad1.push(s.pop());
        }
        for (int i = 0; i < m; i++) {
            sklad2.push(s.pop());
        }
        for (int i = 0; i < m; i++) {
            sklad3.push(sklad2.pop());
        }
        for (int i = 0; i < m; i++) {
            s.push(sklad3.pop());
        }
        for (int i = 0; i < n; i++) {
            s.push(sklad1.pop());
        }
    }
    public static void main(String[] args) {
        Stack<Integer> sklad = new Stack<>();
        for (int i = 9; i > 0; i--) {
            sklad.push(i);
        }
        obrni(sklad, 1, 7);
    }
}
