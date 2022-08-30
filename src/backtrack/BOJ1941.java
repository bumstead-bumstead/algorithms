package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;


/*
* 모든 노드중에 7개뽑는 조합 다확인 --> 연결 됐는지, 4개이상인지
* 좌표를 1차원으로 표현한 뒤, 검사할때 coordination으로 바꾸기.. 기억!!
* */
class Coordination{
    int x;
    int y;

    public Coordination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{ x = " + x + ", y = " + y + "}";
    }
}

public class BOJ1941 {
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Coordination[] arr = new Coordination[7];

    static int cnt = 0;
    static int cc = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];
        visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                board[i][j] = tmp[j];
            }
        }

        princess(0, 0, 0);
        System.out.println(cnt);
    }

    private static void princess(int depth, int tmpC, int cntS) {
        if (depth == 7) {
            if (cntS >= 4) bfs();
            return;
        }

        for (int i = tmpC; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;

            if(visited[x][y]) continue;

            if (board[x][y] == 'S') {
                arr[depth] = new Coordination(x, y);
                visited[x][y] = true;
                princess(depth + 1, i + 1, cntS + 1);
            }
            else {
                arr[depth] = new Coordination(x, y);
                visited[x][y] = true;
                princess(depth + 1, i + 1, cntS);
            }
            visited[x][y] = false;
        }
    }

    private static void bfs() {
        Queue<Coordination> queue = new LinkedList<>();
        boolean[][] BFSvisited = new boolean[5][5];
        int connectedN = 0;

        queue.offer(arr[0]);
        BFSvisited[queue.peek().x][queue.peek().y] = true;

        while (!queue.isEmpty()) {
            Coordination tmp = queue.poll();
            for (int j = 0; j < 4; j++) {
                int tmpx = tmp.x + dx[j];
                int tmpy = tmp.y + dy[j];

                if (tmpx < 0 || tmpy < 0 || tmpx > 4 || tmpy > 4 || BFSvisited[tmpx][tmpy]) continue;
                if (!visited[tmpx][tmpy]) continue;

                queue.offer(new Coordination(tmpx, tmpy));
                BFSvisited[tmpx][tmpy] = true;
                connectedN++;
            }
        }

        if (connectedN == 6) {
            cnt++;
        }
    }

}
