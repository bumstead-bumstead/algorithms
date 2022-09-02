package simulation;


/*
* 1. 판떼기 돌리기조합 만드르서 (4진수)
* 2. bfs로 최단거리
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Coordinate3{
    int x;
    int y;
    int z;

    public Coordinate3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Coordinate3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
public class BOJ16985 {
    static boolean[][][] board = new boolean[5][5][5];
    static boolean[][][] visited;
    static boolean[][][] tmpBoard;
    static int[] dx = {1, 0, 0, -1, 0, 0};
    static int[] dy = {0, 1, 0, 0, -1, 0};
    static int[] dz = {0, 0, 1, 0, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        backTrack(0);
//        for (int i = 0; i < 120; i++) {
//            System.out.println(Arrays.toString(cases2[i]));
//        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    board[i][j][k] = st.nextToken().equals("1") ? true : false; //true가 갈 수 있는 곳
                }
            }
        }
        int[] cases = new int[5];

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < 1024; i++) {
            for (int l = 0; l < 120; l++) {
                int[] tmpStack = cases2[l];
                //board 가져오기
                tmpBoard = new boolean[5][5][5];
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        tmpBoard[tmpStack[j]][k] = Arrays.copyOf(board[j][k], 5);
                    }
                }

//                if (l == 3 && i ==0) {
//                    System.out.println(Arrays.toString(tmpStack));
//                    showboard();
//                }

                fourJinsu(cases, i);

                //돌리기~
                for (int j = 0; j < 5; j++) { //모든 층에대해서
                    for (int k = 0; k < cases[j]; k++) { //돌리기 반복 횟수
                        bingbing(j);
                    }
                }
                if (!tmpBoard[0][0][0] || !tmpBoard[4][4][4]) continue;
                int tmpDist = bfs(tmpBoard);
                if (tmpDist == 12) {
                    showboard();
                }
//                if (tmpDist != -1 ) System.out.println(tmpDist);

                if (tmpDist == -1) continue;
                answer = answer <= tmpDist ? answer : tmpDist;
            }

        }
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static int[] arr = new int[5];
    static int[][] cases2 = new int[120][5];
    static int backCnt = 0;
    static boolean[] visited2 = new boolean[5];
    private static void backTrack(int depth) {
        if (depth == 5) {
            cases2[backCnt++] = Arrays.copyOf(arr, 5);
        }

        for (int i = 0; i < 5; i++) {
            if (visited2[i]) continue;

            visited2[i] = true;
            arr[depth] = i;
            backTrack(depth+1);
            visited2[i] = false;
        }
    }

    private static void showboard() {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                System.out.println(Arrays.toString(tmpBoard[j][k]));
            }
            System.out.println("--------------------------------");
        }
    }

    private static void fourJinsu(int[] cases, int n) {
        for (int i = 0; i < 5; i++) {
            cases[i] = n % 4;
            n = n / 4;
        }
    }

    private static void bingbing(int n) {
        boolean[][] newThang = new boolean[5][5];
        int cnt = 0;

        for (int j = 0; j < 5; j++) {
            for (int i = 4; i >= 0; i--) {
                newThang[cnt / 5][cnt % 5] = tmpBoard[n][i][j];
                cnt++;
            }
        }
        tmpBoard[n] = newThang;
    }

    private static int bfs(boolean[][][] board) {
        visited = new boolean[5][5][5];
        Queue<Coordinate3> queue = new LinkedList<>();
        queue.offer(new Coordinate3(0, 0, 0));
        visited[0][0][0] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Coordinate3 tmp = queue.poll();

                for (int j = 0; j < 6; j++) {
                    int tmpx = tmp.x + dx[j];
                    int tmpy = tmp.y + dy[j];
                    int tmpz = tmp.z + dz[j];

                    if(tmpx < 0 || tmpy < 0 || tmpz <0||tmpx>4||tmpy>4||tmpz>4) continue;
                    if (visited[tmpz][tmpx][tmpy] || !board[tmpz][tmpx][tmpy]) continue;

                    if (tmpx == 4 && tmpy == 4 && tmpz == 4) return ++cnt;

                    queue.offer(new Coordinate3(tmpx, tmpy, tmpz));
                    visited[tmpz][tmpx][tmpy] = true;
                }
            }
            cnt++;
        }
        return -1;
    }
}
