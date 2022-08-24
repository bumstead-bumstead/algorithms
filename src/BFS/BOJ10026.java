package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ10026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[][] board = new char[n][n];
        boolean[][] visited = new boolean[n][n];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int nx = 0;
        int ny = 0;

        for (int i = 0; i < n; i++) {
            char[] a = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                board[i][j] = a[j];
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        int cnt = 0;
        //정상인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;

                queue.offer(new Pair(i, j));
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    Pair tmp = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        nx = tmp.getX() + dx[k];
                        ny = tmp.getY() + dy[k];

                        if (nx < 0 || nx > n-1 || ny < 0 || ny > n-1) continue;
                        if (visited[nx][ny] || board[i][j] != board[nx][ny]) continue;

                        queue.offer(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                cnt++;
            }
        }
        System.out.println(cnt);

        cnt=0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'G') board[i][j] = 'R';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;

                queue.offer(new Pair(i, j));
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    Pair tmp = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        nx = tmp.getX() + dx[k];
                        ny = tmp.getY() + dy[k];

                        if (nx < 0 || nx > n-1 || ny < 0 || ny > n-1) continue;
                        if (visited[nx][ny] || board[i][j] != board[nx][ny]) continue;

                        queue.offer(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void showVisited(boolean[][] visited) {
        System.out.println("---------------------------------");
        for (int i = 0; i < visited.length; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
    }
}
