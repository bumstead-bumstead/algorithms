package sihum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class probD_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사람 수
        int m = Integer.parseInt(st.nextToken()); // 문제 수
        int k = Integer.parseInt(st.nextToken()); // case 수
        float x = Integer.parseInt(st.nextToken()); // 시상 비율
        int nWin = (int) (((float) n) * (x / 100));

        //각 사람별, 각 문제별 최고 점수 유지 필요
        int[][] scores = new int[n + 1][m + 1]; //i번 사람의 j번 문제 점수 arr

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st.nextToken());
            int problem = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            scores[person][problem] = Math.max(score, scores[person][problem]);
        }

        int[] totalScores = getTotalScores(scores);
        //동점이 존재하는 경우 예외처리

        Arrays.sort(totalScores);
        System.out.println(totalScores[totalScores.length-nWin]);
    }

    private static int[] getTotalScores(int[][] scores) {
        int[] result = new int[scores.length];

        for (int i = 1; i < result.length; i++) {
            result[i] = sum(scores[i]);
        }

        return result;
    }

    private static int sum(int[] arr) {
        int result = 0;
        for (int x : arr) {
            result += x;
        }
        return result;
    }
}

/*
* 1 1 1 100
* 1 1 100
* */
