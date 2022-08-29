package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
* 모든 노드중에 7개뽑는 조합 다확인 --> 연결 됐는지, 4개이상인지
* */
class Coordination{
    int x;
    int y;

    public Coordination(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ1941 {
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Coordination[] arr;

    int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];
        visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                board[i][j] = tmp[j];
            }
        }
    }

}
