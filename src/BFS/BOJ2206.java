package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair4 {
    private int x;
    private int y;
    private boolean broken;



    public Pair4(int x, int y, boolean broken) {
        this.x = x;
        this.y = y;
        this.broken = broken;
    }

    public Pair4(int x, int y) {
        this(x, y, false);
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean getBroken(){
        return broken;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

//부수고 나서의 visited 여부와, 부시기 전 visitied 여부를 나눠서 생각해야된다는 것을 몰라서 틀렸음

public class BOJ2206 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);
        if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] board = new int[n][m];
        int[][][] visited = new int[2][n][m];//visited[0]이 안깼을때, [1]이 이미 깼을 때
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], -1);
            }

        }
        Queue<Pair4> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(b[j]);
            }
        }

        queue.offer(new Pair4(0, 0));
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Pair4 tmp = queue.poll();
            int brokenVar = tmp.getBroken() ? 1 : 0;
            for (int i = 0; i < 4; i++) {
                int nx = tmp.getX() + dx[i];
                int ny = tmp.getY() + dy[i];

                if (nx == n-1 && ny == m-1) {
                    System.out.println(visited[brokenVar][tmp.getX()][tmp.getY()] + 1);
                    return;
                }
                if (nx < 0 || nx > n-1 || ny < 0 || ny > m-1 || visited[brokenVar][nx][ny] != -1) continue;
                if (board[nx][ny] == 1) {
                    if (tmp.getBroken()) continue;

                    queue.offer(new Pair4(nx, ny, true));
                    visited[1][nx][ny] = visited[brokenVar][tmp.getX()][tmp.getY()] + 1;
                    continue;
                }

                queue.offer(new Pair4(nx, ny, tmp.getBroken()));
                visited[brokenVar][nx][ny] = visited[brokenVar][tmp.getX()][tmp.getY()] + 1;
            }
//            showvisited(visited);
        }
        System.out.println(-1);
    }

    private static void showvisited(int[][] visited) {
        System.out.println("--------visited-------");
        for (int i = 0; i < visited.length; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
    }
}
