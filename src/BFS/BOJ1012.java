package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int[][] board;
        boolean[][] visited;
        for (int i = 0; i < testCase; i++) {
            String[] a = br.readLine().split(" ");
            int n = Integer.parseInt(a[1]); //행
            int m = Integer.parseInt(a[0]); //열
            int k = Integer.parseInt(a[2]);
            board = new int[n][m];
            visited = new boolean[n][m];
            Queue<Pair> queue = new LinkedList<>();
            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};

            for (int j = 0; j < k; j++) {
                String[] b = br.readLine().split(" ");
                board[Integer.parseInt(b[1])][Integer.parseInt(b[0])] = 1;
            }

            int cnt = 0;

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    if (board[j][l] == 0 || visited[j][l]) continue;
                    queue.offer(new Pair(j, l));
                    visited[j][l] = true;

                    while (!queue.isEmpty()) {
                        Pair tmp = queue.poll();

                        for (int o = 0; o < 4; o++) {
                            int nx = tmp.getX() + dx[o];
                            int ny = tmp.getY() + dy[o];

                            if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1) continue;
                            if (visited[nx][ny] || board[nx][ny] == 0) continue;

                            queue.offer(new Pair(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
