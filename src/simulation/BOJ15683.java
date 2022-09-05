package simulation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
* 1. cctv 좌표 모두 저장한다음, 모든 경우 (모든 가능한 방향의 조합)에 대해서 사각지대 구해서(bfs?) 비교 -> 최대 420만번 검사
* 2. 5~1 순서로 현재 자신이 가장 만이 커버하는 방향 골라버리기
*
* */

//진법 활용해서 독립적인 변수들의 경우의수 구하는 방법 기억하기!!!

class Cctv {
    int x;
    int y;
    public Cctv(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

public class BOJ15683 {
    static int[][] board;
    static List<Cctv> cctvs = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] monitored;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int wallsN = 0;

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 6) wallsN++;
                if (tmp != 0 && tmp != 6) cctvs.add(new Cctv(i, j));
                board[i][j] = tmp;
            }
        }

        int[] directions = new int[cctvs.size()];
        int caseNum = (int) Math.pow(4, cctvs.size());

        int max = 0;

        for (int i = 0; i < caseNum; i++) {
            fourJinsu(directions, i);

            int tmpSum = 0;
            monitored = new boolean[n][m];
            for (int j = 0; j < directions.length; j++) {
                Cctv tmp = cctvs.get(j);
                int tmpDir = directions[j];
                switch (board[tmp.x][tmp.y]) {
                    case 1:
                        tmpSum += monitor(tmp.x, tmp.y, tmpDir);
                        continue;
                    case 2:
                        if (tmpDir % 2 == 0) {
                            tmpSum += monitor(tmp.x, tmp.y, 0);
                            tmpSum += monitor(tmp.x, tmp.y, 2);
                        } else {
                            tmpSum += monitor(tmp.x, tmp.y, 1);
                            tmpSum += monitor(tmp.x, tmp.y, 3);
                        }
                        continue;
                    case 3:
                        tmpSum += monitor(tmp.x, tmp.y, tmpDir);
                        tmpSum += monitor(tmp.x, tmp.y, (tmpDir+1)%4);
                        continue;
                    case 4:
                        tmpSum += monitor(tmp.x, tmp.y, (tmpDir+1)%4);
                        tmpSum += monitor(tmp.x, tmp.y, (tmpDir+2)%4);
                        tmpSum += monitor(tmp.x, tmp.y, (tmpDir+3)%4);
                        continue;
                    case 5:
                        tmpSum += monitor(tmp.x, tmp.y, 0);
                        tmpSum += monitor(tmp.x, tmp.y, 1);
                        tmpSum += monitor(tmp.x, tmp.y, 2);
                        tmpSum += monitor(tmp.x, tmp.y, 3);
                        continue;
                }
            }
            if (max < tmpSum) max = tmpSum;
        }

        System.out.println(n*m - max - cctvs.size() - wallsN);
    }

    private static int monitor(int x, int y, int direction) {
        int cnt = 0;
        while (true) {
            x = x + dx[direction];
            y = y + dy[direction];

            if (y < 0 || x < 0 || x > n-1 || y > m-1 || board[x][y] == 6) break;
            if (board[x][y] != 0 || monitored[x][y]) continue;

            monitored[x][y] = true;
            cnt++;
        }
        return cnt;
    }

    private static void fourJinsu(int[] directions, int caseNum) {
        for (int i = 0; i < directions.length; i++) {
            directions[i] =  caseNum % 4;
            caseNum = caseNum / 4;
        }
    }

    //상하좌우 감시범위 반환하는 함수 만들기 (숫자만 셈)
//    private static int left(int x, int y) {
//        int cnt = 0;
//        int[][] tmpBoard = board;
//        while (true) {
//            y = y-1;
//            if (y < 0 || board[x][y] == 6) break;
//
//            if (board[x][y] != 0) continue;
//
//            cnt++;
//        }
//
//        return cnt;
//    }
//
//    private static int right(int x, int y) {
//        int cnt = 0;
//        int[][] tmpBoard = board;
//        while (true) {
//            y = y+1;
//            if (y < 0 || board[x][y] == 6) break;
//
//            if (board[x][y] != 0) continue;
//
//            cnt++;
//        }
//
//        return cnt;
//    }
//
//    private static int up(int x, int y) {
//        int cnt = 0;
//        int[][] tmpBoard = board;
//        while (true) {
//            x = x-1;
//            if (y < 0 || board[x][y] == 6) break;
//
//            if (board[x][y] != 0) continue;
//
//            cnt++;
//        }
//
//        return cnt;
//    }
//
//    private static int down(int x, int y) {
//        int cnt = 0;
//        int[][] tmpBoard = board;
//        while (true) {
//            x = x + 1;
//            if (y < 0 || board[x][y] == 6) break;
//
//            if (board[x][y] != 0) continue;
//
//            cnt++;
//        }
//        return cnt;
//    }


}
