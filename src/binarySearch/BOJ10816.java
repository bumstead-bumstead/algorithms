package binarySearch;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 핵심은 st, mid, en의 미묘한 인덱스 잘 설정하기~~ 무한루프 안빠지게 등등
* */
public class BOJ10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder result = new StringBuilder();
//        System.out.println(lowerBS(A, 10)+", " + upperBS(A, 10));
        for (int x : B) {
            result.append(upperBS(A, x) - lowerBS(A, x)).append(" ");
        }

        System.out.println(result);
    }

    private static int lowerBS(int[] arr, int target) {
        int st = 0;
        int en = arr.length;
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
        int en = arr.length;
        int mid;

        while (st < en) {
            mid = (st + en) / 2;
            if (arr[mid] <= target) st = mid + 1;
            else en = mid;
        }
        return st;
    }
}
