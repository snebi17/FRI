package naloge;

import java.util.HashMap;

public class ArrArray {
    // null ... prazen element, -1 ... leno brisan element

    private static Integer[][] array;
    private static int[] duplicates;
    private static int size;
    private static int[] bloomFilter;

    public static void main(String[] args) {
        array = new Integer[1][1];
        size = 0;
        bloomFilter = new int[10];
    }

    public void insert(int e) {
        if (!elementInArray(e)) {
            hash(e);
        }

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 0; i < k; i++) {
            int levelSize = (int) Math.pow(2, i);
            if ((size & levelSize) == levelSize) {

            }
        }
    }

    // Buggy fix
    public boolean find(int e) {
        if (!elementInArray(e)) return false;

        if (array[0][0] == e) return true;
        if (array[1][0] == e || array[1][1] == e) return true;

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 2; i < k; i++) {
            int low = 0;
            int high = (int) Math.pow(2, i) - 1;

            if (array[i][low] > e || array[i][high] < e) continue;

            while (low <= high) {
                int j = low + ((high - low) / 2);
                if (array[i][j] == e) return true;
                if (array[i][j] < e) {
                    low = j + 1;
                } else if (array[i][j] > e) {
                    high = j - 1;
                }
            }
        }
        return false;
    }

    public boolean delete(int e) {
        if (!elementInArray(e)) return false;

        if (array[0][0] == e) {
            array[0][0] = -1;
            return true;
        }
        if (array[1][0] == e || array[1][1] == e) {
            if (array[1][0] == e )
            return true;
        }

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 2; i < k; i++) {
            int low = 0;
            int high = (int) Math.pow(2, i) - 1;

            if (array[i][low] > e || array[i][high] < e) continue;

            while (low <= high) {
                int j = low + ((high - low) / 2);
                if (array[i][j] == e) return true;
                if (array[i][j] < e) {
                    low = j + 1;
                } else if (array[i][j] > e) {
                    high = j - 1;
                }
            }
        }
        return false;
    }

    public void printOut() {
        if (size == 0) {
            System.out.println("empty");
            return;
        }

        int k = (int) Math.floor(Math.log(size) / Math.log(2) + 1);
        for (int i = 0; i < k; i++) {
            System.out.printf("%s%d:", "A_", i);
            int levelSize = (int) Math.pow(2, i);
            if ((size & levelSize) == levelSize) {
                if (array[i][0] != null) {
                    System.out.printf("%d/%d", array[i][0], duplicates[array[i][0]]);
                } else if (array[i][0] == -1) {
                    System.out.printf("x");
                }
                for (int j = 1; j < levelSize; j++) {
                    if (array[i][j] != null) {
                        System.out.printf(", %d/%d", array[i][j], duplicates[array[i][j]]);
                    } else if (array[i][j] == -1) {
                        System.out.printf(", x");
                    }
                }
                System.out.println();
            } else {
                System.out.println(" ...");
            }
        }
    }


    private void hash(int e) {
        while (e > 1) {
            if (bloomFilter[e % 10] == 1) continue;
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


    private static void resize() {

    }

    private static void merge() {

    }

}
