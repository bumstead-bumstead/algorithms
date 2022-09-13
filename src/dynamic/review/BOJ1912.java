package dynamic.review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1. d[i] = i가 포함된 연속합 중 최댓값
* 2. d[i] = Math.max(arr[i], d[i-1] + arr[i]);
*
* */
public class BOJ1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] d = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        d[1] = arr[1];

        for (int i = 2; i < n + 1; i++) {
            d[i] = Math.max(arr[i], d[i - 1] + arr[i]);
        }

        System.out.println(Arrays.toString(d));
    }
}
