package ed.u2.sorting.model;

public final class BubbleSort {

    public static void sort(int[] a, SortStats stats) {
        int n = a.length;
        boolean swapped;
        long start = System.nanoTime(); // contar tiempo

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                stats.comparisons++; // Contador de comparaciones
                if (a[j] > a[j + 1]) {
                    SortingUtils.swap(a, j, j + 1);
                    stats.swaps++; // Contador de intercambios
                    swapped = true;
                }
            }
            if (!swapped) break; // Corte temprano
        }
        stats.timeNano = System.nanoTime() - start;
    }
}