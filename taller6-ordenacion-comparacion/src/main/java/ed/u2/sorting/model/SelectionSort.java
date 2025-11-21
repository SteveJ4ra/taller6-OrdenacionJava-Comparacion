package ed.u2.sorting.model;

public final class SelectionSort {

    public static void sort(int[] a, SortStats stats) {
        int n = a.length;
        long start = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                stats.comparisons++;
                if (a[j] < a[min_idx]) {
                    min_idx = j;
                }
            }

            if (min_idx != i) {
                SortingUtils.swap(a, i, min_idx);
                stats.swaps++;
            }
        }
        stats.timeNano = System.nanoTime() - start;
    }
}