import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Katherine Cabezas
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail shaker sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * When writing your sort, don't recheck already sorted items. The amount of
     * items you are comparing should decrease by 1 for each pass of the array
     * (in either direction). See the PDF for more info.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailShakerSort(T[] arr,
                                              Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array and/or comparator is null."
            );
        }

        T temp = null;
        boolean swapped = true;

        while (swapped) {
            for (int index = 0; index < arr.length / 2; index++) {
                swapped = false;
                for (int i =  index; i < arr.length - index - 1; i++) {
                    if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                        temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        swapped = true;
                    }
                }
                for (int j = arr.length - 2 - index; j > index; j--) {
                    if (comparator.compare(arr[j], arr[j - 1]) < 0) {
                        temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                        swapped = true;
                    }
                }
            }
        }

    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array and/or comparator is null."
            );
        }

        for (int i = 1; i < arr.length; i++) {
            int index = i;
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr[i], arr[j]) < 0) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i = j;
                j--;
            }
            i = index;
        }

    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array and/or comparator is null."
            );
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException(
                    "Array, comparator, and/or rand is null."
            );
        }

        quickSort(arr, 0, arr.length - 1, rand, comparator);
    }

    /**
     * Private helper method to help with quick sort.
     * @param arr Array to sort
     * @param left Pointer for left side of pivot
     * @param right Pointer for right side of pivot
     * @param rand Random object used to choose pivot
     * @param comparator Comparator used to compare data in array
     * @param <T> data type to be sorted
     */
    private static <T> void quickSort(T[] arr, int left, int right,
            Random rand, Comparator<T> comparator) {
        if (left < right) {
            int randomPivot = rand.nextInt(right - left) + left;
            int pivot = partition(arr, left, right, randomPivot,
                    rand, comparator);
            quickSort(arr, left, pivot - 1, rand, comparator);
            quickSort(arr, pivot + 1, right, rand, comparator);
        }
    }

    /**
     * Private helper method to help with partitioning array in place.
     * @param arr Array to sort
     * @param left Pointer for left side of pivot
     * @param right Pointer for right side of pivot
     * @param pivot Index of pivot
     * @param rand Random object used to choose pivot
     * @param comparator Comparator used to compare data in array
     * @param <T> data type to be sorted
     * @return pivot in right pointer's index
     */
    private static <T> int partition(T[] arr, int left, int right, int pivot,
            Random rand, Comparator<T> comparator) {
        T pivotValue = arr[pivot];
        arr[pivot] = arr[right];
        arr[right] = pivotValue;

        int index = left;
        for (int i = left; i < right; i++) {
            if (comparator.compare(arr[i], pivotValue) < 0) {
                T temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                index++;
            }
        }
        T newOne = arr[index];
        arr[index] = arr[right];
        arr[right] = newOne;
        return index;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Array and/or comparator is null."
            );
        }

        mergeSort(0, arr.length - 1, comparator, arr);

    }

    /**
     * Private helper method to help merge sort data recursively.
     * @param left Left index
     * @param right Right index
     * @param comparator Comparator used to compare data in array
     * @param arr Array to be sorted
     * @param <T> data type to be sorted
     */
    private static <T> void mergeSort(int left, int right,
            Comparator<T> comparator, T[] arr) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(left, middle, comparator, arr);
            mergeSort(middle + 1, right, comparator, arr);
            merge(left, middle, right, arr, comparator);
        }
    }

    /**
     * Private helper method that combines arrays into one array.
     * @param left Left index
     * @param middle Middle index
     * @param right Right index
     * @param arr Array to be sorted
     * @param comparator Comparator used to compare data in array
     * @param <T> data type to be sorted
     */
    private static <T> void merge(int left, int middle, int right,
            T[] arr, Comparator<T> comparator) {

        T[] temp = (T[]) new Object[arr.length];
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            if (comparator.compare(temp[i], temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            arr[k] = temp[i];
            k++;
            i++;
        }
    }

    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * DO NOT USE {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use an ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null.");
        }

        LinkedList<Integer>[] buckets = new LinkedList[11];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<Integer>();
        }

        int max = 0;

        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }

        int i = 0;
        while (max > 0) {
            for (int num : arr) {
                buckets[(num / pow(10, i)) % 10].add(num);
            }
            int arrIndex = 0;
            for (int index = 0; index < buckets.length - 1; index++) {
                while (buckets[index].peekFirst() != null) {
                    arr[arrIndex] = buckets[index].remove().intValue();
                    arrIndex++;
                }
            }
            max = max / 10;
            i++;
        }

        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sort instead of {@code Math.pow()}. DO NOT MODIFY THIS METHOD.
     *
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power.
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Invalid exponent.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
