package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7576 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int[][] dist;
    static int n;
    static int m;
    static int nx;
    static int ny;

    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[1]);
        m = Integer.parseInt(a[0]);

        board = new int[n][m];
        dist = new int[n][m];

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 1002);
        }
        

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(b[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 1) continue;
                BFS(i, j);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[i][j] == 1002 && board[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                if (dist[i][j] > max && dist[i][j] != 1002) max = dist[i][j];
            }
        }
        System.out.println(max);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void BFS(int x, int y) {
        Queue<Pair> queue = new LinkedList<>();
        dist[x][y] = 0;

        queue.offer(new Pair(x, y));

        while (!queue.isEmpty()) {
            Pair tmp = queue.poll();

            for (int k = 0; k < 4; k++) {
                nx = tmp.getX() + dx[k];
                ny = tmp.getY() + dy[k];

                if (nx < 0 || ny < 0 || nx > n -1 || ny > m -1) continue;
                if (dist[nx][ny] <= dist[tmp.getX()][tmp.getY()]+1 || board[nx][ny] != 0) continue;

                queue.offer(new Pair(nx, ny));
                dist[nx][ny] = dist[tmp.getX()][tmp.getY()]+1;
            }
        }
    }
}
