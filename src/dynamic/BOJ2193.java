package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
* 1. D[i, j] = i-1자리의 수가 j (0, 1)인 i자릿수의 이친수를 만드는 경우의 수,
* 2. D[i, 0] = D[i-1, 1] + D[i-1, 0]
*    D[i, 1] = D[i-1, 0]
*
* DP는 DP인지 파악을 잘해야됨!!!! 경우의 수를 찾아내는 것인데, 백트래킹이나 bfs로는 시간/메모리에서 불가능 할때
* 단계별로 해를 찾아나가는 과정으로 풀기
* */
public class BOJ2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] d = new long[n + 1][2];

        d[1][0] = 0;
        d[1][1] = 1;

        for (int i = 2; i < n + 1; i++) {
            d[i][0] = d[i - 1][1] + d[i - 1][0]; //0, 1 아무거나와도 댐
            d[i][1] = d[i - 1][0];
        }
//        for (int i = 0; i < d.length; i++) {
//            System.out.println(d[i][0] + d[i][1]);
//            System.out.println(Arrays.toString(d[i]));
//        }

        System.out.println(d[n][0]+d[n][1]);
    }

}
