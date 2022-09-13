package dynamic.review;


/*
* 1. D[i] = i를 포함하는 최대 길이의 증가부분수열의 길이
* 11055랑 똑같게
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(1);
            return;
        }

        int[] arr = new int[n + 1];
        int[] d = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        d[1] = 1;

        int max = -1;

        for (int i = 2; i < n + 1; i++) {
            d[i] = 1;
            for (int j = 1; j < i; j++) {
                if (d[j] + 1 > d[i] && arr[j] < arr[i]) d[i] = d[j] + 1;
            }
            if (d[i] > max) max = d[i];
        }

        System.out.println(max);
    }
}
