package sihum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class probD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사람 수
        int m = Integer.parseInt(st.nextToken()); // 문제 수
        int k = Integer.parseInt(st.nextToken()); // case 수
        float x = Integer.parseInt(st.nextToken()); // 시상 비율
        int nWin = (int) (((float) n) * (x/100));

        int[][] scores = new int[n + 1][m + 1];

        int person;
        int problem;
        int score;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            person = Integer.parseInt(st.nextToken());
            problem = Integer.parseInt(st.nextToken());
            score = Integer.parseInt(st.nextToken());

            if (scores[person][problem] < score) {
                scores[person][problem] = score;
            }
        }

        int[] totalScores = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            totalScores[i] = sum(scores[i]);
        }

        Arrays.sort(totalScores);

        System.out.println(totalScores[totalScores.length-nWin]);
    }

    private static int sum(int[] arr) {
        int result = 0;
        for ( int x : arr) {
            result += x;
        }
        return result;
    }
}

/*
* 1 1 1 100
* 1 1 100
* */
