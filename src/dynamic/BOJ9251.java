package dynamic;


/*
* 1. 경로를 지나올 때 문자를 저장, d를 모두 채우고 나서
* 2. d[i][j] = str1의 j까지의 문자와 str2의 i까지의 문자와의 공통 최장부분수열
* 존나어려움 무조건 다시봐야댐 : LCS
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

/*
* LCS : 최장 공통 부분 수열
* d[i][j] = 0~i까지의 문자열과 0~j까지의 문자열 사이의 LCS
* 1. 문자 i, j가 같으면, D[i][j] = d[i-1][j-1] + 1
* 2. 그렇지 않으면 d[i][j] = d[i-1][j]; -> 왜 이전행과 이전열 중에 큰걸가져옴???
* */
public class BOJ9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[][] d = new int[a.length + 1][b.length + 1];

        for (int i = 1; i < a.length+1; i++) {
            for (int j = 1; j < b.length+1; j++) {
                if (a[i-1] == b[j-1]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    //여긴 d[i-1][j]랑 d[i][j-1]중에 큰걸루 넣어야함.
                    //자기 이전의 수열들 사이의 경우의 수는 모두 포함하기 때무넹
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }

        for (int i = 0; i < d.length; i++) {
            System.out.println(Arrays.toString(d[i]));
        }
    }
}
