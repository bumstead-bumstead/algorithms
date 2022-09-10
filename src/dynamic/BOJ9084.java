package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* d[i] = i원을 주어진 동전으로 나타내는 경우의 수
* */


public class BOJ9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            int money = Integer.parseInt(br.readLine());
            int[] d = new int[money + 1];
            for (int coin : coins) {
                d[coin] = 1;
            }

            for (int j = 1; j < money + 1; j++) {
                if (d[j] == 1) continue;
                for (int coin : coins) {
                    if (j > coin) d[j] += d[j - coin];
                }
            }

            System.out.println(Arrays.toString(d));

        }
    }
}
