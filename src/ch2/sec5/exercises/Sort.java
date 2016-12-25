package ch2.sec5.exercises;

import java.util.Locale;
import java.util.Random;

/**
 * @author Alexander Tsupko (tsupko.alexander@yandex.ru)
 *         Copyright (c) 2016. All rights reserved.
 */
public class Sort {
    private static final int SIZE = 1 << 27;//1 << 18;
    private static final int TRIAL = 1 << 3;
    private static final int SHELL = 20; // for 1 << 27, optimal is 17, which is 48%

    private static int[] a = new int[SIZE];
    private static Random random = new Random();
    private static int[] shell = new int[SHELL];

    static {
        shell[0]++;
        for (int i = 1; i < shell.length; i++) {
            shell[i] = 3 * shell[i-1] + 1;
        }
    }

    private enum Algorithm {
        BUBBLE, SELECTION, INSERTION, SHELL, MERGE, QUICK, QUICK_THREE_WAY, HEAP, RADIX, SYSTEM;

        Algorithm() {
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < TRIAL; i++) {
            shuffle(a);
            long start = System.nanoTime();
//            bubbleSort(a);
//            selectionSort(a);
//            insertionSort(a);
            shellSort(a);
//            mergeSort(a);
//            quickSort(a);
//            threeWayQuickSort(a);
//            heapSort(a);
//            radixSort(a);
//            Arrays.sort(a);
            System.out.printf(Locale.US, "%f%n", (System.nanoTime() - start) / 1E9);
            assert isSorted(a);
        }
    }

    private static void radixSort(int[] a) {
    }

    private static void heapSort(int[] a) {
        int n = a.length;
        for (int i = n >> 1; i >= 1; i--) sink(a, i, n);
        while (n > 1) {
            swapShiftedByOne(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void threeWayQuickSort() {
    }

    private static void quickSort(int[] a) {
    }

    private static void mergeSort(int[] a) {
    }

    private static void shellSort(int[] a) {
        // Shell: 1, 3, 7, 15, 31, 63, ... (powers of two minus one: 2 ^ i - 1 for i = 1, 2, ...)
        // Knuth: 1, 4, 13, 40, 121, 364... (x = 1; x = 3 * x + 1;)
        // Sedgewick: 1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905...
        // (9 * 4 ^ i - 9 * 2 ^ i + 1 and 4 ^ i - 3 * 2 ^ i + 1)
        for (int h = shell.length-1; h >= 0; h--) {
            int k = shell[h];
            for (int i = k; i < a.length; i++) {
                for (int j = i; j >= k && a[j] < a[j-k]; j -= k) {
                    swap(a, j, j-k);
                }
            }
        }
    }

    private static void insertionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                } else break;
            }
        }
    }

    private static void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    private static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            if (a[i] > a[i+1]) {
                swap(a, i, i+1);
                i = -1;
            }
        }
    }

    private static void shuffle(int[] a) {
        for (int i = 0; i < SIZE; i++) {
            a[i] = random.nextInt();
        }
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) {
                return false;
            }
        }
        return true;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void swapShiftedByOne(int[] a, int i, int j) {
        int t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }

    private static void sink(int[] a, int i, int n) {
        while (i << 1 <= n) {
            int j = i << 1;
            if (j < n && a[j-1] < a[j]) j++;
            if (!(a[i-1] < a[j-1])) break;
            swapShiftedByOne(a, i, j);
            i = j;
        }
    }
}
