package dynamic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
* 1. D[i] = i번째 단계까지 칸을 채우는 경우의 수
* 2. D[i] = d[i-1] + 2*d[i-2]
* */
public class BOJ11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n==1) {
            System.out.println(1);
            return;
        }

        int[] d = new int[n + 1];

        d[1] = 1;
        d[2] = 3;

        for (int i = 3; i < n+1; i++) {
            d[i] = (d[i-1] + d[i-2]*2) % 10007;
        }

        System.out.println(d[n]);
    }
}
