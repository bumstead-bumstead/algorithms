package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PickUpAItem {
    static boolean[][] board;
    static int[] dx = new int[] {1,-1,0,0, 1, 1, -1, -1};
    static int[] dy = new int[] {0,0,1,-1, 1, -1, 1, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new boolean[102][102];

        for (int[] r : rectangle) {
            overwrap(r);
        }

        // showBoard();
        int answer = bfs(characterY, characterX, itemY, itemX);

        return answer;
    }

    private int bfs(int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[102][102];

        queue.offer(new Integer[]{2*characterX, 2*characterY});
        visited[2*characterX][2*characterY] = true;

        while (!queue.isEmpty()) {
            answer++;
            // System.out.println(answer);
            int reps = queue.size();
            // for (Integer[] next : queue) {
            // System.out.print(next[0] + ", " + next[1] + " | ");
            // }

            // System.out.println();
            for (int i =0; i < reps; i++) {
                Integer[] next = queue.poll();

                for (int j=0; j<4;j++) {
                    int tmpX = next[0] + dx[j];
                    int tmpY = next[1] + dy[j];

                    if (tmpX == itemX*2 && tmpY == itemY*2) {
                        return answer / 2;
                    }
                    if (tmpX < 0 || tmpX > 102 || tmpY < 0 || tmpY > 102
                            || visited[tmpX][tmpY]
                            || !board[tmpX][tmpY]
                            || isNotOnEdge(tmpX, tmpY)) continue;

                    queue.offer(new Integer[] {tmpX, tmpY});
                    visited[tmpX][tmpY] = true;
                }
            }
        }
        return -1;
    }

    private void overwrap(int[] r) {
        r = Arrays.stream(r).map(n -> 2*n).toArray();

        for (int i = r[1]; i <= r[3]; i++) {
            for (int j = r[0]; j <= r[2]; j++) {
                board[i][j] = true;
            }
        }
    }

    private boolean isNotOnEdge(int x, int y) {
        for (int i=0; i<8;i++){
            int tmpX = x + dx[i];
            int tmpY = y + dy[i];

            if (!board[tmpX][tmpY]) return false;
        }

        return true;
    }

    private void showBoard() {
        for (int i =0; i <20; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == 2 & j == 6) {
                    System.out.print("S ");
                    continue;
                }
                if (i == 14 & j == 16) {
                    System.out.print("Q ");
                    continue;
                }
                if (board[i][j]) System.out.print(1 + " ");
                else System.out.print(0 + " ");
            }
            System.out.println();
        }
    }

}
