package dynamic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1. D[i] = i일 상담이 마지막일 때 수익의 최댓값
* 2. D[i-1]까지 검사해서 자기자신이 낄 수 있는지, 낄 수 있다면 꼈을 떄 최대인지 확인해서 바까치우깅~
* 이 기술
* */
public class BOJ14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n];
        int[] p = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        if (t[0] <= n) d[0] = p[0];

        for (int i = 1; i < n; i++) {
            if (t[i] + i > n) continue;
            d[i] = p[i];
            for (int j = 0; j < i; j++) {
                //j = 8, i = 9
                if (t[j] + j <= i && d[j] + p[i] > d[i]) d[i] = d[j] + p[i];
            }
        }

//        System.out.println(Arrays.toString(d));
        System.out.println(max(d));
    }

    private static int max(int[] d) {
        int answer = -1;

        for (int x : d) {
            if (answer < x) answer = x;
        }
        return answer;
    }

}
