package naloge;

import java.util.HashMap;

public class ArrArray {
    // null ... prazen element, -1 ... leno brisan element

    private static Integer[][] array;
    private static int size;
    private static HashMap<Integer, Integer> duplicateMap;

    public static void main(String[] args) {
        array = new Integer[1][1];
    }

    private void insert(int x) {

    }

    private boolean find(int e) {
        for (int i = 0; i < (int) Math.floor(Math.log(size) / Math.log(2) + 1); i++) {
            if (array[i][0] > e || array[i][0] < e) continue;
            int j = (int) (Math.pow(2, i - 1) - 1);
            int k = 1;
            while (k < i) {
                if (array[i][j] == e) return true;
                if (array[i][j] < e) {
                    j = (int) (Math.pow(2, j - 1) - 1);;
                } else if (array[i][j] > e) {
                    j = (int) (Math.pow(2, j + 1) - 1);;
                }
                k++;
            }
        }
        return false;
    }

    private boolean delete(int e) {

    }

    private void printOut() {
        if (size == 0) {
            System.out.println("empty");
            return;
        }

        for (int i = 0; i < (int) Math.floor(Math.log(size) / Math.log(2)); i++) {
            System.out.printf("%s%d:", "A_", i);
            System.out.printf("%d/%d,", array[i][0], duplicateMap.get(array[i][0]));
            for (int j = 1; j < (int) Math.pow(2, i); j++) {
                if (array[i][j] != null) {
                    System.out.printf(" %d/%d", array[i][j], duplicateMap.get(array[i][j]));
                }
            }
            System.out.println();
        }
    }

    private void

    private void resize() {

    }

}
