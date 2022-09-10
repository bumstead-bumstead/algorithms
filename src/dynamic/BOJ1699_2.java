package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 가장 가까운 제곱수를 쌔려버림
*
* */
public class BOJ1699_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(1);
            return;
        } else if (n == 2) {
            System.out.println(2);
            return;
        }
        int[] d = new int[n + 1];

        d[1] = 1;
        d[2] = 2;

        for (int i = 3; i < n+1; i++) {
            Double tmp = Math.sqrt(i);
            if (Math.sqrt(i) == tmp.intValue()) {
                d[i] = 1;
                continue;
            }

            d[i] = Integer.MAX_VALUE;
//            for (int j = 1; j <= i/2 ; j++) { //j를 뭐하러 다 순회하니?? j^2만 빼가믄스 하믄 되자너~
//                if (d[i-j] + d[j] < min) min = d[i - j] + d[j];
//            }
            for (int j = 1; j*j < i; j++) {
                if (d[i] > d[i - j * j] + 1) d[i] = d[i - j * j] + 1;
            }
        }

        System.out.println(d[n]);

    }
}
