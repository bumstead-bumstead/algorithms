package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1. D[i][j] = i번째 집을 j색으로 칠할 때 여기까지의 최소 비용
* 2.
* */
public class BOJ1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n + 1][3];
        int[][] d = new int[n + 1][3];
        for (int i = 1; i < n+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            d[1][i] = cost[1][i];
        }

        for (int i = 2; i < n+1; i++) {
            for (int j = 0; j < 3; j++) {
                d[i][j] = Math.min(d[i-1][(j + 1) % 3] + cost[i][j], d[i-1][(j + 2) % 3] + cost[i][j]);
            }
        }

        System.out.println(Math.min(Math.min(d[n][0], d[n][1]), d[n][2]));
    }
}
