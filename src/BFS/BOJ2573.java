package BFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//녹이기, bfs로 덩어리 갯수 세기 반복
public class BOJ2573 {
    static int n;
    static int m;
    static int[][] board;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int years = 0;
        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(b[j]);
            }
        }

        while (true) {
            melt();
            showBoard();
            int icebergs = bfs();
            years++;
            if (icebergs == 0) {
                System.out.println(0);
                return;
            } else if (icebergs >= 2) {
                System.out.println(years);
                return;
            }
        }
    }

    private static int bfs() {
        int result = 0;
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] <= 0 || visited[i][j]) {
                    continue;
                }
                queue.offer(new Pair(i, j));

                while (!queue.isEmpty()) {
                    Pair tmp = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = tmp.getX() + dx[k];
                        int ny = tmp.getY() + dy[k];

                        if (nx < 1 || ny < 1 || nx > n - 2 || ny > m - 2) continue;
                        if (visited[nx][ny] || board[nx][ny] <= 0) continue;

                        queue.offer(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                result++;
            }
        }

        return result;
    }

    private static void melt() {
        int[][] newThang = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1 || board[nx][ny] > 0) continue;

                        cnt++;
                    }

                    newThang[i][j] = board[i][j] - cnt;
                }
                else newThang[i][j] = 0;
            }
        }

        board = newThang;
    }

    private static void showBoard() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("------------------");
    }

}
