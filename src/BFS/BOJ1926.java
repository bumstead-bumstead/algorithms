package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }
}

public class BOJ1926 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);

        int[][] picture = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        Queue<Pair> pairsOngoing = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                picture[i][j] = Integer.parseInt(b[j]);
            }
        }
        int pictureCnt = 0; //그림 갯수
        int maxArea = 0; //제일 큰 그림의 넓이

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || picture[i][j] == 0) continue;

                pictureCnt++;

                pairsOngoing.offer(new Pair(i, j));
                visited[i][j] = true;

                int areaCnt = 0;

                while (!pairsOngoing.isEmpty()) {
                    Pair tmp = pairsOngoing.poll();
                    areaCnt++; //여기서?
                    int x = tmp.getX();
                    int y = tmp.getY();
                    if (x > 0 && !visited[x-1][y] && picture[x-1][y] == 1) {
                        pairsOngoing.offer(new Pair(x - 1, y));
                        visited[x-1][y] = true;
                    }
                    if (x < n-1 && !visited[x+1][y] && picture[x+1][y] == 1) {
                        pairsOngoing.offer(new Pair(x + 1, y));
                        visited[x + 1][y] = true;
                    }
                    if (y > 0 && !visited[x][y-1] && picture[x][y-1] == 1) {
                        pairsOngoing.offer(new Pair(x, y-1));
                        visited[x][y-1] = true;
                    }
                    if (y < m-1 && !visited[x][y+1] && picture[x][y+1] == 1) {
                        pairsOngoing.offer(new Pair(x, y+1));
                        visited[x][y+1] = true;
                    }
                }
                if (maxArea < areaCnt) maxArea = areaCnt;
            }
        }
        System.out.println(pictureCnt);
        System.out.println(maxArea);

    }
}
