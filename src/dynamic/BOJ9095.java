package dynamic;

/*
* 다이나믹 프로그래밍은 자잘한 단계가 모여 최종 단계가 되는 느낌이군!!!!
* 1. 테이블 정의 - D[i] = i를 1,2,3의 합으로 저장하는 경우의 수
* 2. 점화식 찾기 - D[i] = D[i-1] + D[i-2] + D[i-3]
* 3. 초기값 - D[1] = 1, D[2] = 2, D[3] = 3
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testN = Integer.parseInt(br.readLine());

        int[] d = new int[12];

           for (int i = 0; i < testN; i++) {
            Arrays.fill(d, 0);
            d[0] = 0; d[1] = 1; d[2] = 2; d[3] = 4; //(1 2) (2 1) (1 1 1)
            int n = Integer.parseInt(br.readLine());
            for (int j = 4; j <= n; j++) {
                d[j] += d[j-1] + d[j-2] + d[j-3];
            }
            System.out.println(d[n]);
        }
    }
}
