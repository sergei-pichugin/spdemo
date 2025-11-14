package com.example.spdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс со статическими методами для сортировки по алгоритму Timsort
 */
public class TimSort {

    private static final int MIN_RUN = 64;

    /**
     * Сортирует массив гибридным алгоритмом сортировки Timsort
     * @param arr исходный массив
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        int minRun = calcMinRun(n);
        List<int[]> runs = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int runEnd = findRun(arr, i, n);
            int runLen = runEnd - i;

            if (runLen < minRun) {
                int end = Math.min(i + minRun, n);
                insertionSort(arr, i, end - 1);
                runEnd = end;
            }
            runs.add(new int[]{i, runEnd});
            i = runEnd;

            while (runs.size() > 1) {
                int[] run1 = runs.get(runs.size() - 2);
                int[] run2 = runs.get(runs.size() - 1);

                int l1 = run1[0], r1 = run1[1];
                int l2 = run2[0], r2 = run2[1];
                int len1 = r1 - l1, len2 = r2 - l2;

                if (len1 <= len2) {
                    merge(arr, l1, r1 - 1, r2 - 1);
                    runs.remove(runs.size() - 1);
                    runs.set(runs.size() - 1, new int[]{l1, r2});
                } else break;
            }
        }

        // Слияние оставшихся подмассивов
        while (runs.size() > 1) {
            int[] run1 = runs.get(runs.size() - 2);
            int[] run2 = runs.get(runs.size() - 1);

            int l1 = run1[0], r1 = run1[1];
            int l2 = run2[0], r2 = run2[1];
            merge(arr, l1, r1 - 1, r2 - 1);
            runs.remove(runs.size() - 1);
            runs.set(runs.size() - 1, new int[]{l1, r2});
        }
    }

    // Вычисляет минимальный размер подмассива
    private static int calcMinRun(int n) {
        int r = 0;
        while (n >= MIN_RUN) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    // Сортирует массив небольшого размера методом вставки
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Выполняет слияние двух отсортированных подмассивов [l..m] и [m+1..r]
    private static void merge(int[] arr, int l, int m, int r) {
        int[] left = copyOfRange(arr, l, m + 1);
        int[] right = copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    // Определяет направление сортировки (по возрастанию/по убыванию) с индекса start
    // и в случае по убыванию переворачивает подмассив, возвращает индекс финиша
    private static int findRun(int[] arr, int start, int n) {
        int end = start + 1;
        if (end == n) return end;

        // Определить направление
        if (arr[end] < arr[start]) {
            // по убыванию
            while (end < n && arr[end] < arr[end - 1])
                end++;
            reverse(arr, start, end - 1);
        } else {
            // по возрастанию
            while (end < n && arr[end] >= arr[end - 1])
                end++;
        }
        return end;
    }

    // Переворачивает подмассив от l до r задом наперёд
    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    // Создаёт новый массив как копию подмассива src от from до to
    private static int[] copyOfRange(int[] src, int from, int to) {
        int[] copy = new int[to-from];
        int icopy = 0;
        for (int i = from; i < to; i++) {
            copy[icopy++] = src[i];
        }
        return copy;
    }
}
