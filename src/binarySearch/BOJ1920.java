package binarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {
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
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int x : B) {
            if (binarySearch(A, x) == -1) bw.write(0 + "");
            else bw.write(1 + "");
            bw.newLine();
        }

        bw.close();
    }

    private static int binarySearch(int[] arr, int target) {
        int st = 0;
        int en = arr.length - 1;
        int mid;

        while (st <= en) {
            mid = (st + en) / 2;
            if (arr[mid] == target) return 1;
            else if (arr[mid] > target) en = mid - 1;
            else st = mid + 1;
        }
        return -1;
    }
}

