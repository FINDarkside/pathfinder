package tiralabra.datastructure;

public class MyArrays {

    /**
     * Sorts array.
     * 
     * @param arr Array to sort.
     */
    public static void sort(long[] arr) {
        quickSortRecursive(arr, 0, arr.length);
    }

    private static void quickSortRecursive(long[] array, int a, int b) {
        if (a >= b - 1) {
            return;
        }
        long pivot = array[a];
        int j = a;

        for (int i = a + 1; i < b; i++) {
            if (array[i] < pivot) {
                j++;
                long tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        long tmp = array[a];
        array[a] = array[j];
        array[j] = tmp;

        quickSortRecursive(array, a, j);
        quickSortRecursive(array, j + 1, b);
    }

}
