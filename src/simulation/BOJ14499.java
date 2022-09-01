package simulation;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 가는 곳이 0이면 전염시키고, 0이 아니면 주사위가 전염되고 칸은 0됨
*  3*3 배열과, 정수 변수 하나로 주사위 표현???
* */

public class BOJ14499 {
    static int[][] board;
    static int[][] dice = new int[3][3];
    static int bottom = 0;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dice[i], 0);
        }


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Coordinate tmpPos = new Coordinate(x, y);

        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());

            //현재 위치 옮겨주기
            tmpPos.x += dx[dir];
            tmpPos.y += dy[dir];

            if (tmpPos.x < 0 || tmpPos.y < 0 || tmpPos.x >= n || tmpPos.y >= m)  {
                tmpPos.x -= dx[dir];
                tmpPos.y -= dy[dir];
                continue;
            }

            //이번 이동에 밑으로 갈 위치 저장
            int nextBottom = dice[1 + dx[dir]][1 + dy[dir]];

            //굴러가고 나서의 주사위 모양으로 바꿔줌
            roll(dir);
            bw.write(dice[1][1] + "");
            bw.newLine();
            if (board[tmpPos.x][tmpPos.y] == 0) board[tmpPos.x][tmpPos.y] = nextBottom;
            else {
                nextBottom = board[tmpPos.x][tmpPos.y];
                board[tmpPos.x][tmpPos.y] = 0;
            }

            bottom = nextBottom;

        }
        bw.close();
    }

    private static void roll(int dir) {
        dice[1 + dx[dir]][1 + dy[dir]] = dice[1][1];
        dice[1][1] = dice[1 - dx[dir]][1 - dy[dir]];
        dice[1 - dx[dir]][1 - dy[dir]] = bottom;
    }
}
