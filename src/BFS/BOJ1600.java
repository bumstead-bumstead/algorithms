package BFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 벽부수기 문제랑 비슷한 풀이
* visited를 int[][]로, 값은 말짬뿌를 이용한 횟수
* -> 검사하는 위치의 말짬뿌횟수가 자신의 말짬뿌횟수보다 크면 갈 수 있음. 더 효율적인 방법이라는거니께
* */

class Monkey {
    int[] position;
    int jumpNum;

    public Monkey(int x, int y, int jumpNum) {
        this.position = new int[]{x, y};
        this.jumpNum = jumpNum;
    }

    @Override
    public String toString() {
        return "Monkey : [position : " + Arrays.toString(position) + ", jumpNum : " + jumpNum + "]";
    }
}

public class BOJ1600 {
    static int[][] board;
    static int[][] visited;
    static Queue<Monkey> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        if (n == 1 && m == 1) {
            System.out.println(0);
            return;
        }
        board = new int[n][m];
        visited = new int[n][m];
        queue = new LinkedList<>();
        int cnt = 0;

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int[] dx2 = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy2 = {-2, -1, 1, 2, 2, 1, -1, -2};

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], k+1);
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue.offer(new Monkey(0, 0, 0));
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            int tmpSize = queue.size();
            System.out.println(queue);
            cnt++;
            for (int i = 0; i < tmpSize; i++) {
                Monkey tmpMonkey = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = tmpMonkey.position[0] + dx[j];
                    int ny = tmpMonkey.position[1] + dy[j];

                    if (nx == n-1 && ny == m-1) {
                        System.out.println(cnt);
                        return;
                    }
                    if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
                    if (visited[nx][ny] <= tmpMonkey.jumpNum || board[nx][ny] == 1) continue;

                    queue.offer(new Monkey(nx, ny, tmpMonkey.jumpNum));
                    visited[nx][ny] = tmpMonkey.jumpNum;
                }

                if (tmpMonkey.jumpNum < k) {
                    for (int j = 0; j < 8; j++) {
                        int nx = tmpMonkey.position[0] + dx2[j];
                        int ny = tmpMonkey.position[1] + dy2[j];

                        if (nx == n-1 && ny == m-1) {
                            System.out.println(cnt);
                            return;
                        }
                        if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
                        if (visited[nx][ny] <= tmpMonkey.jumpNum+1 || board[nx][ny] == 1) continue;

                        queue.offer(new Monkey(nx, ny, tmpMonkey.jumpNum+1));
                        visited[nx][ny] = tmpMonkey.jumpNum+1;
                    }
                }
            }
        }
        if (queue.isEmpty()) System.out.println(-1);
    }
}
