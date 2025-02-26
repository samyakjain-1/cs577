import java.util.Scanner;

public class hw5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        int k = sc.nextInt();

        for (int t = 0; t < k; t++) {
            
            int j = sc.nextInt();

            
            long[] arr = new long[j];
            for (int i = 0; i < j; i++) {
                arr[i] = sc.nextLong();
            }

          
            long inversions = mergeSortAndCount(arr, 0, j - 1);


            System.out.println(inversions);
        }

        sc.close();
    }

    
    private static long mergeSortAndCount(long[] arr, int left, int right) {
        
        if (left >= right) {
            return 0;
        }

        int mid = (left + right) / 2;
        long countLeft = mergeSortAndCount(arr, left, mid);
        long countRight = mergeSortAndCount(arr, mid + 1, right);
        long countMerge = mergeAndCount(arr, left, mid, right);

        return countLeft + countRight + countMerge;
    }

    
    private static long mergeAndCount(long[] arr, int left, int mid, int right) {
       
        int n1 = mid - left + 1;
        int n2 = right - mid;

        long[] leftArr = new long[n1];
        long[] rightArr = new long[n2];

        
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0; 
        int k = left;
        long crossInversions = 0;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                crossInversions += (n1 - i);
            }
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        while (j < n2) {
            arr[k++] = rightArr[j++];
        }

        return crossInversions;
    }
}
