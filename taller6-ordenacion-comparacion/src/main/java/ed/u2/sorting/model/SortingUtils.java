package ed.u2.sorting.model;

public final class SortingUtils {
    private SortingUtils() {}

    public static void swap(int[] array, int indexA, int indexB) {
        int swapHolder = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = swapHolder;
    }
}