package recursion;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ2447 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[][] board;
    static int[][] test;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        board = new String[n][n];
        test = new int[n][n];
        star(0, 0, n ,false);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(board[i][j]);
            }
            bw.newLine();
        }
        bw.close();
    }


    //2차원배열로 해보까? n/3씩 해가믄스
    private static void star(int x, int y, int n, boolean blank) {
        if (n == 1) {
            if (blank) {
                board[x][y] = " ";
            }
            else board[x][y] = "*";

            test[x][y]++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) star(x + n / 3 * i, y + n / 3 * j, n / 3, true);
                else star(x + n / 3 * i, y + n / 3 * j, n / 3, blank);
            }
        }
    }
}
