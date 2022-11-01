package binarySearch;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1. 이분탐색으로 해당 정수 인덱스 찾기
* 2. 거기서 양쪽으로 다시 이분탐색~
*
* */
public class BOJ10816 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        int n = Integer.parseInt(br.readLine());
//        int[] A = new int[n];
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < n; i++) {
//            A[i] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(A);
//
//        int m = Integer.parseInt(br.readLine());
//        int[] B = new int[m];
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < n; i++) {
//            B[i] = Integer.parseInt(st.nextToken());
//        }
        int[] test = new int[]{2, 4, 6, 10, 10, 16, 16, 16, 30, 32};
        System.out.println(lowerBS(test, 16) + ", " + upperBS(test, 16));
    }

    private static int lowerBS(int[] arr, int target) {
        int st = 0;
        int en = arr.length - 1;
        int mid;

        while (st < en) {
            mid = (st + en) / 2;
            if (arr[mid] >= target) en = mid;
            else st = mid + 1;
        }
        return st;
    }
    private static int upperBS(int[] arr, int target) {
        int st = 0;
        int en = arr.length - 1;
        int mid;

        while (st < en) {
            mid = (st + en) / 2;
            if (arr[mid] <= target) st = mid + 1;
            else en = mid;
        }
        return st;
    }
}
