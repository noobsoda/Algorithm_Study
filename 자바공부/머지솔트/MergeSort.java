package 자바공부.머지솔트;

import java.util.Arrays;

public class MergeSort {
    static int[] arr = {1,10,7,6,3,2,8,4,9,5};
    static int[] sorted = new int[arr.length];
    public static void main(String[] args) {
        mergeSort(arr, sorted, 0, arr.length-1);
        System.out.println(Arrays.toString(sorted));
    }
    //start => m
    //stop => n

    private static void mergeSort(int[] arr, int[] sorted, int m, int n){
        if(m < n){
            int middle = (m+n) / 2;
            mergeSort(arr, sorted, m, middle);
            mergeSort(arr, sorted, middle+1, n);
            merge(arr, m, middle, n);

        }
    }

    private static void merge(int[] arr, int start, int middle, int end) {
        int i = start;
        int j = middle+1;
        int k = start;

        while(i <= middle && j < end){
            if(arr[i] < arr[j]){
                sorted[k] = arr[i];
                i++;
            }else{
                sorted[k] = arr[j];
            }
        }
        k++;

        if(i > middle){
            for(int t = j; t <= end; t++){
                sorted[k] = arr[t];
                k++;
            }
        }else{
            for(int t = i; t <= end; t++){
                sorted[k] = arr[t];
                k++;
            }
            
        }
    }
}
