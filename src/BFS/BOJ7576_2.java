package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7576_2 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static int n;
    static int m;
    static int nx;
    static int ny;
    static Queue<Pair> queue;

    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queue = new LinkedList<>();

        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[1]);
        m = Integer.parseInt(a[0]);

        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(b[j]);
                if (tmp == 1) queue.offer(new Pair(i,j));
                board[i][j] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 1) continue;
                BFS(i, j);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                if (board[i][j] > max) max = board[i][j];
            }
        }
        System.out.println(max-1);
    }

    private static void BFS(int x, int y) {

        visited[x][y] = true;
        queue.offer(new Pair(x, y));

        while (!queue.isEmpty()) {
            Pair tmp = queue.poll();

            for (int k = 0; k < 4; k++) {
                nx = tmp.getX() + dx[k];
                ny = tmp.getY() + dy[k];

                if (nx < 0 || ny < 0 || nx > n -1 || ny > m -1) continue;
                if (visited[nx][ny] || board[nx][ny] != 0) continue;

                board[nx][ny] = board[tmp.getX()][tmp.getY()] + 1;
                queue.offer(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}
