package naloge.drugaNaloga;

public class HTB {
    private int noOfCollisions;
    private Integer[] array;
    private Integer[] tmpArray;

    private final int MULTIPLIER;
    private int CAPACITY;
    private final int CONST_1;
    private final int CONST_2;

    /***
     *
     * @param p - množitelj
     * @param m - velikost polja
     * @param c1
     * @param c2
     */
    HTB(int p, int m, int c1, int c2) {
        array = new Integer[m];

        MULTIPLIER = p;
        CAPACITY = m;
        CONST_1 = c1;
        CONST_2 = c2;
    }

    /***
     * h(k) = (k * p) mod m
     * h'(k, i) = (h(k) + c1 * i + c2 * i2) mod m
     * @param key
     */
    public void insert(int key) {
        if (find(key)) return;

        int index, i = 0;
        while (i < CAPACITY) {
            index = hash(key, i);
            if (array[index] == null) {
                array[index] = key;
                break;
            }
            i++;
            if (i == CAPACITY) {
                resize();
                noOfCollisions += i;
                i = 0;
            }
        }

        noOfCollisions += i;
    }

    private void reHash(int key, int c) {
        int index, i = 0;
        while (i < c) {
            index = hash(key, i);
            if (tmpArray[index] == null) {
                tmpArray[index] = key;
                break;
            }
            i++;
        }
        noOfCollisions += i;
    }

    private int hash(int key, int i) {
        if (i == 0) return (key * MULTIPLIER) % CAPACITY;
        return (hash(key, 0) + CONST_1 * i + CONST_2 * i * i) % CAPACITY;
    }

    boolean find(int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) continue;
            if (array[i] == key) return true;
        }
        return false;
    }

    void delete(int key) {
        if (!find(key)) return;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) continue;
            if (array[i] == key) array[i] = null;
        }
    }

    void printKeys() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) System.out.printf("%d: %d\n", i, array[i]);
        }
    }

    void printCollisions() {
        System.out.println(noOfCollisions);
    }

    void resize() {
        CAPACITY = 2 * CAPACITY + 1;
        tmpArray = new Integer[CAPACITY];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) reHash(array[i], CAPACITY);
        }
        array = tmpArray;
    }
}
