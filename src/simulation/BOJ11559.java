package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//class Coordinate {
//    int x;
//    int y;
//
//    public Coordinate(int x, int y) {
//        this.x=x;
//        this.y = y;
//
//    }
//}
public class BOJ11559 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] board = new char[12][6];
    static int chainCnt = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            char[] a = br.readLine().toCharArray();
            for (int j = 0; j < 6; j++) {
                board[i][j] = a[j];
            }
        }
        boolean chain = true;
        while (chain) {
            visited = new boolean[12][6];

            chain = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] != '.' || visited[i][j]) {
                        if (puyoBFS(i, j)) chain = true;
                    }
                }
            }
            System.out.println("-------pop-------");
            showBoard();
            System.out.println("-----pulldown-----");
            pulldown();
            showBoard();

            if (chain) chainCnt++;
        }

        System.out.println(chainCnt);
    }

    private static boolean puyoBFS(int x, int y) {
//        System.out.println(x + ", " + y + "---------------------------");
        Queue<Coordinate> queue = new LinkedList<>();
        List<Coordinate> puyos = new ArrayList<>();
        char target = board[x][y];
        int cnt = 0;
        queue.offer(new Coordinate(x, y));
        puyos.add(new Coordinate(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
//            System.out.println("돈다돌아!!!");
            Coordinate tmp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx < 0 || ny < 0 || nx > 11 || ny > 5 || visited[nx][ny]) continue;

                if (board[nx][ny] == target) {
                    visited[nx][ny] = true;
                    queue.offer(new Coordinate(nx, ny));
                    cnt++;
                    puyos.add(new Coordinate(nx, ny));
                }
            }
        }
        if (cnt >= 3) {
            for (Coordinate puyo : puyos) {
                board[puyo.x][puyo.y] = '.';
            }
            return true;
        }
        return false;
    }

    private static void pulldown() {
        for (int j = 0; j < 6; j++) {
            int prevPuyo = -1;
            for (int i = 11; i >= 0; i--) {
                if (board[i][j] != '.') {
                    if (prevPuyo == -1) {
                        char tmp = board[i][j];
                        board[i][j] = '.';
                        board[11][j] = tmp;
                        prevPuyo = 11;
                    } else {
                        char tmp = board[i][j];
                        board[i][j] = '.';
                        board[--prevPuyo][j] = tmp;
                    }
                }
            }
        }
    }

    private static void showBoard() {
        for (int i = 0; i < 12; i++) {
            System.out.println(Arrays.toString(board[i]));

        }
    }
}
