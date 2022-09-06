package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
* 1. D[i][j]  = i번째 계단에서의 최대 점수, j는 현재 연속으로 뛴 횟수..
* 2.
* */
public class BOJ2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] score = new int[n+1];

        for (int i = 1; i <= n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        int[][] d = new int[301][3];

        d[0][0] = 0;
        d[0][1] = 0;
        d[0][2] = 0;

        d[1][0] = 0;
        d[1][1] = score[1];
        d[1][2] = 0;

        if (n == 1) {
            System.out.println(score[1]);
            return;
        } else if (n == 2) {
            System.out.println(score[1] + score[2]);
            return;
        }

        d[2][0] = 0;
        d[2][1] = score[2]; //불가
        d[2][2] = score[1]+score[2];

        d[3][0] = 0;
        d[3][1] = score[1] + score[3];
        d[3][2] = score[2] + score[3];

        for (int i = 4; i < score.length; i++) {
            for (int j = 0; j < 3; j++) {
                //더블짬뿌 해서 도달하는 경우
                d[i][1] = Math.max(d[i - 2][j] + score[i], d[i][1]);
                // 한단계뛰어서 도달하는 경우
                if (j != 0) d[i][j] = Math.max(d[i-1][j-1] + score[i], d[i][j]);
            }
        }
        System.out.println(Math.max(d[6][1], d[6][2]));
    }


}
