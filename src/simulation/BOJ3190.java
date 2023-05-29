package simulation;

import java.io.*;
import java.util.*;

public class BOJ3190 {

    //시계방향
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static Integer[][][] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] board = new int[N + 1][N + 1];
        pos = new Integer[N+2][N+2][2];
        for (int i = 0; i < N+2; i++) {
            for (int j = 0; j < N+2; j++) {
                pos[i][j] = new Integer[]{i, j};
            }
        }
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            board[row][col] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        Queue<Command> commands = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            commands.offer(new Command(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int curTime = 0;
        int curDir = 1;
        Deque<Integer[]> body = new LinkedList<>();
        body.offer(new Integer[]{1, 1});
        board[1][1] = -1;

        while (true) {
            curTime++;
            //System.out.println("time = " + curTime);
            Integer[] head = body.peek();
            //System.out.println("지금 머리 : " + Arrays.toString(head));
            Integer[] nextHead = pos[head[0] + dx[curDir]][head[1] + dy[curDir]]; // curTime에 있어야할 머리
            //System.out.println("nextHead = " + Arrays.toString(nextHead));

            if (nextHead[0] < 1 || nextHead[0] > N || nextHead[1] < 1 || nextHead[1] > N || board[nextHead[0]][nextHead[1]] == -1) {
                System.out.println(curTime);
                break;
            }
            if (board[nextHead[0]][nextHead[1]] != 1) {
                Integer[] out = body.pollLast();
                //System.out.println("out : " + Arrays.toString(out));
                board[out[0]][out[1]] = 0;
            }

            body.offerFirst(nextHead);
            board[nextHead[0]][nextHead[1]] = -1;
            //System.out.println("다음 머리 : " + Arrays.toString(nextHead));
            if (!commands.isEmpty() && commands.peek().time == curTime) {
                //System.out.println("돌았다. : " + commands.peek().direction);
                curDir = rotate(commands.peek().direction, curDir);
                commands.poll();
            }
            //showBoard(board);
            //System.out.println("---------------------------------");
        }

        bw.close();
    }

    private static int rotate(String command, int direction) {
        if (command.equals("D")) return (direction + 1) % 4;
        else return (direction + 3) % 4;
    }

    private static void showBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}

class Command {
    int time;
    String direction;

    public Command(int time, String direction) {
        this.time = time;
        this.direction = direction;
    }
}
