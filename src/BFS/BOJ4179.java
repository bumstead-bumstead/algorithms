package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



//시작부터 가장자리에있는 경우 생각 안해서 틀렸었ㅇ씀
public class BOJ4179 {
    static char[][] board;
    static boolean[][] visited;
    static Queue<Pair> fireQueue = new LinkedList<>();
    static Queue<Pair> jihoonQueue = new LinkedList<>();
    static int n;
    static int m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int nx;
    static int ny;
    static int cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);
        visited = new boolean[n][m];
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            char[] b = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (b[j] == 'J') {
                    if (i == 0 || i == n-1 || j == 0 || j == m-1) {
                        System.out.println(1);
                        return;
                    }

                    jihoonQueue.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
                else if (b[j] == 'F') fireQueue.offer(new Pair(i, j));

                board[i][j] = b[j];
            }
        }

        Pair tmp;
        int threshold;
        while (true) {
            if (jihoonQueue.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            threshold = fireQueue.size();
            for (int i = 0; i < threshold; i++) {
                tmp = fireQueue.poll();
                //불의 움직임...
                for (int j = 0; j < 4; j++) {
                    nx = tmp.getX() + dx[j];
                    ny = tmp.getY() + dy[j];

                    if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
                    if (board[nx][ny] == 'F' || board[nx][ny] == '#') continue;

                    fireQueue.offer(new Pair(nx, ny));
                    board[nx][ny] = 'F';
                }
            }

            threshold = jihoonQueue.size();
            for (int i = 0; i < threshold; i++) {
                tmp = jihoonQueue.poll();

                //지후니의 움직임... 샤샤샥~ (닌자인듯ㅋㅋ)
                for (int j = 0; j < 4; j++) {
                    nx = tmp.getX() + dx[j];
                    ny = tmp.getY() + dy[j];

                    if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) {
                        continue;
                    }
                    if (board[nx][ny] == 'F' || board[nx][ny] == '#' || visited[nx][ny]) {
                        continue;
                    }
                    if (nx == 0 || nx == n-1 || ny == 0 || ny == m-1) {
                        System.out.println(++cnt);
                        return;
                    }

                    jihoonQueue.offer(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            cnt++;
            showBoard();
        }
    }

    private static void showBoard(){
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("-------------------");
    }
}
