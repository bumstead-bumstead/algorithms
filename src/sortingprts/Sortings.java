package sortingprts;

import java.util.*;

public class Sortings {

    public static void insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int prev = i - 1;
            int tmp = arr[i];
            while (prev >= 0 && tmp < arr[prev]) {
                arr[prev + 1] = arr[prev--];
            }
            arr[prev+1] = tmp;
        }
    }

    public static void radixSort(int[] arr) {

        int maxDigits = getMaxDigits(arr);
        List<Queue<Integer>> queues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            queues.add(new LinkedList());
        }

        for (int i = 0; i <= maxDigits; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.println("tmp = " + arr[j]);
                int tmpDigitNumber = getNumberOfDigit(arr[j], i);
                System.out.println("tmpDigitNumber = " + tmpDigitNumber);
                queues.get(tmpDigitNumber).offer(arr[j]);
            }

            int cnt = 0;

            for (Queue<Integer> queue : queues) {
                while (!queue.isEmpty()) arr[cnt++] = queue.poll();
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void countingSort(int[] arr) {
        int[] countings = new int[100]; //최댓값이 100이라고 가정

        for (int x : arr) {
            countings[x]++;
        }
        int tmpIdx = 0;
        for (int i = 0; i < 100; i++) {
            while (countings[i] != 0) {
                arr[tmpIdx++] = i;
                countings[i]--;
            }
        }
    }

    private static void mergeSort(int[] arr, int left, int right) {
        /*
        * Todo
        *  1. 주어진 배열을 반으로 나누고, 각각을 정렬한다.
        *  2. 양쪽의 배열을 앞쪽에서부터 비교하며 정렬한다.
        * */
        System.out.println(left + " to " + right);
        int mid = (left + right) / 2;
        if (right - left > 1) {
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
        }
        merge(arr, left, right, mid);
        System.out.println("result : " + Arrays.toString(arr));
    }

    private static int getMaxDigits(int[] arr) {
        int result = 0;
        for (int x : arr) {
            result = Math.max(result, String.valueOf(x).length());
        }
        return result;
    }

    private static int getNumberOfDigit(int x, int digit) {
        int a = (int) Math.pow(10, digit);
        return (x % (a * 10)) / a;
    }

    private static void merge(int[] arr, int left, int right, int mid) {
        int l = left;
        int r = mid + 1;
        int tmpIdx = 0;

        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, left, right+1)));
        int[] sortedArray = new int[right - left + 1];

        while (l <= mid && r <= right) {
            System.out.println("l = " + l + ", r = " + r);
            if (arr[l] >= arr[r]) {
                sortedArray[tmpIdx++] = arr[r++];
            } else {
                sortedArray[tmpIdx++] = arr[l++];
            }
        }

        while (l <= mid) {
            System.out.println("l = " + l + ", r = " + r);

            sortedArray[tmpIdx++] = arr[l++];
        }
        while (r <= right) {
            System.out.println("l = " + l + ", r = " + r);

            sortedArray[tmpIdx++] = arr[r++];
        }
        tmpIdx = left;
        for (int x : sortedArray) {
            arr[tmpIdx++]= x;
        }

//        System.out.println(Arrays.toString(sortedArray));
    }

    public static void quick(int[] arr, int left, int right) {
        /*
        * Todo
        *  1. pivot 설정
        *  2. 현재 배열의 양쪽 끝에서부터 검사 -> 왼쪽인덱스는 pivot보다 크면 멈추고, 오른쪽 인덱스는 pivot보다 작으면 멈춘다.
        *  3. 두 idx가 모두 멈추면, 두 값의 위치 변경
        *  4. 두 값이 같아지면, 그 위치와 pivot 위치 변경
        *  5. pivot을 기준으로 배열을 둘로 쪼개서 각각의 배열에 대해서 다시 반복
        * */

        if (left >= right) return;

        int pivotIdx = divide(arr, left, right);
        System.out.println("pivotIdx = " + pivotIdx);
        System.out.println("result = " + Arrays.toString(arr));

        quick(arr, left, pivotIdx-1);
        quick(arr, pivotIdx + 1, right);
    }

    private static int divide(int[] arr, int left, int right) {
        int pivotValue = arr[left];
        int i = left;
        int j = right;
        System.out.println("left = " + left);
        System.out.println("right = " + right);

        while (i < j) {
            while (i < j && arr[j] > pivotValue) j--;
            while (i < j && arr[i] <= pivotValue) i++;
            swap(arr, i, j);
        }
        swap(arr, left, i);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid = (left + right) / 2;
        System.out.println(mid);

        while (left <= right) {


            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) {
                left = mid + 1;
            } else right = mid - 1;
            mid = (left + right) / 2;


            System.out.println(mid);
            System.out.println("l = " + left + ", r = " + right);
        }

        if (arr[mid] == target) return mid;

        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{6, 5, 3, 67, 23, 7, 7, 4, 2};
//        insertion(a);
//        mergeSort(a, 0, a.length - 1);
//        radixSort(a);
        countingSort(a);
        System.out.println(Arrays.toString(a));
        System.out.println(binarySearch(a, 5));
    }
}
