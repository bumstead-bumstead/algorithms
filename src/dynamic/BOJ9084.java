package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
* d[i] = i원을 주어진 동전으로 나타내는 경우의 수
* 다시 풀기
*
* coin부터 n까지의 i, 모든 동전에 대해서 D[i] += D[i-coin];
*  -> 모든 단계에서 해당 동전을 사용하는 경우의 수를 모두 포함한다.
*  -> 동전 별로 경우의수를 채워넣기 때문에 '조합'만을 계산
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
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j < money + 1; j++) {
                if (d[j] == 1) continue;
                a: for (int coin : coins) {

                    //순열이 아닌 조합이니까
                    for (int x : list) {
                        if (x == coin) continue a;
                    }

                    if (j > coin) {
                        d[j] += d[j - coin];
                        list.add(j - coin);
                    }
                }
                list.clear();
            }

            System.out.println(Arrays.toString(d));

        }
    }
}
