package dynamic.review;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* d[i] = i번째 수를 포함하는 최대 증가 부분 수열
* d[i] -> d[i-1] > arr[i] && arr[i] > arr[i-1]
*
* */
public class BOJ11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        int[] d = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        d[1] = arr[1];

        for (int i = 2; i < n + 1; i++) {
            d[i] = arr[i];
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j] && d[i] < d[j] + arr[i]) d[i] = d[j] + arr[i];
            }
        }

        System.out.println(Arrays.toString(d));
    }
}
