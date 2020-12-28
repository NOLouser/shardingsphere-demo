package com.nolouser.demo.note;

import org.junit.jupiter.api.Test;

/**
 * 排序算法笔记
 */
public class MySort {

    /**
     * 归并排序 -- 递归
     *
     * @param arr
     * @param low 起始位置索引
     * @param high 终止位置索引
     */
    public void mergeSort(int arr[], int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            // 排序左边
            mergeSort(arr, low, mid);
            // 排序右边
            mergeSort(arr, mid + 1, high);
            // 合并左右两边的序列
            merge(arr, low, mid, high);
        }

    }

    /**
     * 合并左右两个数组
     * @param arr
     * @param low
     * @param mid
     * @param high
     */
    public void merge(int arr[], int low, int mid, int high) {
        int sortArr[] = new int[high - low + 1];
        // 左边序列指针
        int right = low;
        // 右边序列指针
        int left = mid + 1;
        int k = 0;
        while (right <= mid && left <= high) {
            if (arr[right] < arr[left]) {
                sortArr[k++] = arr[right++];
            } else {
                sortArr[k++] = arr[left++];
            }
        }

        // 左边数组剩余元素合并
        while (right <= mid) {
            sortArr[k++] = arr[right++];
        }

        // 右边数组剩余元素合并
        while (left <= high) {
            sortArr[k++] = arr[left++];
        }

        for (int i : sortArr) {
            arr[low++] = i;
        }

    }

    @Test
    public void mergeSortTest() {
        int arr[] = {1, 10, 11, 20, 19, 7, 9, 8};
        mergeSort(arr, 0, 7);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
