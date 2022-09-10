package dynamic;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Scanner;

/*
* D[i] = i번째 줄까지 채우는 경우의 수
* D[i] = D[i-1] + D[i-2]
* */
public class BOJ11726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 1) {
            System.out.println(1);
            return;
        }
        long[] d = new long[n + 1];
        d[1] = 1;
        d[2] = 2;

        for (int i = 3; i < n+1; i++) {
            d[i] = (d[i - 1] + d[i - 2])%10007;
        }

        System.out.println(d[n]%10007);
    }
}
