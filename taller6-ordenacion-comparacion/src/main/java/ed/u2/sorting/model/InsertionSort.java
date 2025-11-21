package ed.u2.sorting.model;

public final class InsertionSort {

    public static void sort(int[] a, SortStats stats) {
        int n = a.length;
        long start = System.nanoTime(); // Contar tiempo

        for (int i = 1; i < n; ++i) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0) {
                stats.comparisons++; // Contador de comparaciones
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    stats.swaps++;  // Contador de intercambios
                    j = j - 1;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
        stats.timeNano = System.nanoTime() - start;
    }
}