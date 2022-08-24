package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        boolean[][] visited;
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        int ny = 0;
        int nx = 0;

        for (int i = 0; i < testCase; i++) {
            int cnt = 0;
            Queue<Pair> queue = new LinkedList<>();
            int n = Integer.parseInt(br.readLine());
            visited = new boolean[n][n];

            String[] begin = br.readLine().split(" ");
            String[] a = br.readLine().split(" ");
            Pair target = new Pair(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
            queue.offer(new Pair(Integer.parseInt(begin[0]), Integer.parseInt(begin[1])));
            visited[Integer.parseInt(begin[0])][Integer.parseInt(begin[1])] = true;
            a : while (!queue.isEmpty()) {
                int tmpSize = queue.size();
                System.out.println("queue = " + queue);
                System.out.println("cnt = " + cnt);
                System.out.println("queue 갯수 = " + queue.size());
                System.out.println("=============================");
                for (int k = 0; k < tmpSize; k++) {
                    Pair tmp = queue.poll();
                    if (tmp.equals(target)) {
                        System.out.println(cnt);
                        break a;
                    }

                    for (int j = 0; j < 8; j++) {
                        nx = tmp.getX() + dx[j];
                        ny = tmp.getY() + dy[j];

                        if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1) continue;
                        if (visited[nx][ny]) continue;

                        queue.offer(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                cnt++;
            }
        }
    }
}
