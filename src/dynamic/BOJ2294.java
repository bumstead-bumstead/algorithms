package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
* d[i] = i원을 만드는 데 사용하는 최소의 동전 수
* d[i] = min(d[i-k] + d[k])
* d[c] = 1;
* */

public class BOJ2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int result = 0;
        HashSet<Integer> set = new HashSet<>();
        int[] d = new int[k + 1];
        Arrays.fill(d, Integer.MAX_VALUE); //d가 max_value로 남아있으면 불가

        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp <= k) set.add(tmp);
        }
        int[] coins = new int[n];

        int cnt = 0;
        for (int x : set) {
            coins[cnt++] = x;
            d[x] = 1;
        }

        for (int i = coins[0] + 1; i < k + 1; i++) {
            for (int j = 1; j <= i/2; j++) {
                if (d[j] == Integer.MAX_VALUE || d[i-j] == Integer.MAX_VALUE) continue;
                d[i] = Math.min(d[i], d[i-j] + d[j]);
            }
        }

        if (d[k] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(d[k]);
    }
}
