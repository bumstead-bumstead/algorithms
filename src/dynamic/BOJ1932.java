package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


/*
* 1. D[i][j] = i번째 단계의 j 위치에 도달하는 최대 합
* 2.
* */

public class BOJ1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }
        int[][] intTriangle = new int[n+1][n+1];
        int[][] d = new int[n + 1][n + 1];

        for (int i = 1; i < n+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i+1; j++) {
                intTriangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for (int i = 1; i < n+1; i++) {
//            System.out.println(Arrays.toString(intTriangle[i]));
//        }

        d[1][1] = intTriangle[1][1];
        d[2][1] = intTriangle[2][1] + d[1][1];
        d[2][2] = intTriangle[2][2] + d[1][1];
        for (int i = 3; i < n+1; i++) {
            for (int j = 1; j < i+1; j++) {
                if (j == 1) {
                    d[i][j] = d[i - 1][j] + intTriangle[i][j];
                    continue;
                }
                if (j == i) {
                    d[i][j] = d[i - 1][j - 1] + intTriangle[i][j];
                    continue;
                }
                d[i][j] = Math.max(d[i - 1][j-1], d[i - 1][j]) + intTriangle[i][j];
            }
        }

//        for (int i = 1; i < n+1; i++) {
//            System.out.println(Arrays.toString(d[i]));
//        }

        int max = -1;
        for (int x : d[n]) {
            max = x > max ? x : max;
        }

        System.out.println(max);
    }
}
