package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* bottom-up 방식
* d[i] = i일에 벌 수 있는 최대 수익
* d[i+T] = max(d[i+T], d[i] + P)
* -> 모든 d에 해당 날짜에 벌수 있는 최댓값이 저장댄당 ㅋㅋ
* d[1] = 0;
* */
public class BOJ15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] T = new int[n + 1];
        int[] P = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] d = new int[n + 2];

        int max = -1; //현재 검사한 날짜 중 최대 수익. 중간에 일을 하지 않는 날이 있으면 0인 원소가 생김 -> update
        for (int i = 1; i < n + 1; i++) {
            if (d[i] < max) d[i] = max;
            else max = d[i];

            if (i + T[i] > n + 1) continue;
            d[i + T[i]] = Math.max(d[i + T[i]], d[i] + P[i]);
        }

        max = Math.max(max, d[n + 1]);

        System.out.println(max);
    }
}
