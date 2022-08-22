package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair3 {
    private int x;
    private int y;

    private int reps;

    public Pair3(int x, int y, int reps) {
        this.x = x;
        this.y = y;
        this.reps = reps;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public int getReps() {
        return reps;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

//pair 클래스에 reps 추가할 필요 없이, boolean[][]였던 배열을 거리를 나타내는 배열로 만들면 댐. 어차피 한 지점은 한번씩만 갈꺼니까 거기 갈 때의 거리 저장
public class BOJ2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] board = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<Pair3> queue = new LinkedList<>();
        int cnt = 1;

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(b[j]);
            }
        }

        queue.offer(new Pair3(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pair3 tmp = queue.poll();
            if (tmp.getX() == n-1 && tmp.getY() == m-1) {
                System.out.println(tmp.getReps());
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tmp.getX() + dx[i];
                int ny = tmp.getY() + dy[i];

                if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
                if (visited[nx][ny] || board[nx][ny] == 0) continue;

                queue.offer(new Pair3(nx, ny, tmp.getReps()+1));
                visited[nx][ny] = true;
            }
        }
    }
}
