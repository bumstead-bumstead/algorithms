package recursion;

import java.io.*;
import java.util.Arrays;

public class BOJ2448 {
    static String[][] board;
    //    static boolean[] blankSeq = {true, true, false, true, true, true, false, true, false, true, false, false, false, false, false};
    static int[] dx = {0, 1, 1, 2, 2, 2, 2, 2};
    static int[] dy = {0, -1, 1, -2, -1, 0, 1, 2};


    // n/3 = 8 - 8*8 세 뭉탱이 ... n/3 = 1 : 1*1 세뭉탱이
    //가장작은 한뭉탱이 5*3?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        board = new String[n*2][n*2];
        for (int i = 0; i < n*2; i++) {
            Arrays.fill(board[i], " ");
        }
        triangleStar(0, n-1, n);

//        for (int i = 0; i < n*2; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < board[i].length; j++) {
                bw.write(board[i][j]);
            }
            bw.newLine();
        }

        bw.close();
    }


    //시작 좌표는 x=0, y=23?
    private static void triangleStar(int x, int y, int n) {
        if (n==3) {
            for (int i = 0; i < 8; i++) {
                board[x+ dx[i]][y+ dy[i]] = "*";
            }
            return;
        }

        triangleStar(x, y, n/2);
        triangleStar(x + n/2, y - n/2, n/2);
        triangleStar(x + n/2, y + n/2, n/2);
    }
}
