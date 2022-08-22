package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
class Pair2 {
    private int x;
    private int y;

    public Pair2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class BOJ1926withDxDy {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);

        int[][] picture = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        Queue<Pair2> pairsOngoing = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                picture[i][j] = Integer.parseInt(b[j]);
            }
        }
        int pictureCnt = 0; //그림 갯수
        int maxArea = 0; //제일 큰 그림의 넓이
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || picture[i][j] == 0) continue;

                pictureCnt++;

                pairsOngoing.offer(new Pair2(i, j));
                visited[i][j] = true;

                int areaCnt = 0;

                while (!pairsOngoing.isEmpty()) {
                    Pair2 tmp = pairsOngoing.poll();
                    areaCnt++;

                    for (int k = 0; k < 4; k++) {
                        int nx = tmp.getX() + dx[k];
                        int ny = tmp.getY() + dy[k];

                        if (nx < 0 || nx > n-1 || ny < 0 || ny > m-1) continue;
                        if (visited[nx][ny] || picture[nx][ny] == 0) continue;

                        pairsOngoing.offer(new Pair2(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                if (maxArea < areaCnt) maxArea = areaCnt;
            }
        }
        System.out.println(pictureCnt);
        System.out.println(maxArea);

    }
}
