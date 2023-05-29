package recursion;

import java.io.*;

public class BOJ1992_2 {
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        for (int i = 1; i < N+1; i++) {
            String row = br.readLine();
            for (int j = 1; j < N+1; j++) {
                board[i][j] = row.charAt(j - 1) - '0';
            }
        }


        bw.write(quadTree(1, 1, N).toString());

        bw.close();
    }

    private static StringBuilder quadTree(int row, int col, int length) {
        if (length == 1) {
            return new StringBuilder().append(board[row][col]);
        }
        int nextLength = length / 2;
        StringBuilder first = quadTree(row, col, nextLength);
        StringBuilder second = quadTree(row , col+ nextLength, nextLength);
        StringBuilder third = quadTree(row + nextLength, col , nextLength);
        StringBuilder forth = quadTree(row + nextLength, col + nextLength, nextLength);
        if (first.toString().equals(second.toString())
                && second.toString().equals(third.toString())
                && third.toString().equals(forth.toString())
                && !first.toString().startsWith("(")) {
            return first;
        }
        return sheaveStrings(first, second, third, forth);
    }

    private static StringBuilder sheaveStrings(StringBuilder a, StringBuilder b, StringBuilder c, StringBuilder d) {
        StringBuilder sb = new StringBuilder();
        return sb.append("(").append(a).append(b).append(c).append(d).append(")");
    }
}
