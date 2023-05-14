package subjectUtc;

import java.util.LinkedList;
import java.util.Queue;

public class FindMine {
    static int[] dx = new int[]{1,0,-1,0,-1,-1, 1, 1};
    static int[] dy = new int[]{0,1,0,-1,-1,1,-1,1};
    static boolean[][] visited;
    static char[][] charBoard;

    public String[] solution(String[] board, int y, int x) {
        charBoard = new char[board.length][board[0].length()];
        for (int i = 0; i < board.length; i++) {
            charBoard[i] = board[i].toCharArray();
        }

        visited = new boolean[charBoard.length][charBoard[0].length];

        //{row, col}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});
        visited[y][x] = true;

        if (charBoard[y][x] == 'M') {
            charBoard[y][x] = 'X';
            return toStringArray(charBoard);
        }

        while (!queue.isEmpty()) {
            int[] tmpPos = queue.poll();
            int row = tmpPos[0];
            int col = tmpPos[1];

            int nMine = getNumberOfMine(row, col);

            if (nMine > 0) {
                charBoard[row][col] = (char) (nMine + '0');
                continue;
            }

            charBoard[row][col] = 'B';

            for (int i = 0; i < 8; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if (nextRow < 0 || nextRow > charBoard.length-1 || nextCol < 0 || nextCol > charBoard[0].length-1) continue;
                if (visited[nextRow][nextCol]) continue;

                queue.offer(new int[]{nextRow,nextCol});
                visited[nextRow][nextCol] = true;
            }
        }

        hideMine();

        String[] answer = toStringArray(charBoard);
        return answer;
    }

    private void hideMine() {
        for (char[] arr : charBoard) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 'M') arr[i] = 'E';
            }
        }
    }

    private String[] toStringArray(char[][] arr) {
        String[] result = new String[arr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = String.valueOf(arr[i]);
        }

        return result;
    }

    private int getNumberOfMine(int row, int col) {
        int mine = 0;
        for (int i = 0; i < 8; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (nextRow < 0 || nextRow > charBoard.length-1 || nextCol < 0 || nextCol > charBoard[0].length-1) continue;
            if (visited[nextRow][nextCol]) continue;

            if (charBoard[nextRow][nextCol] == 'M') {
                mine++;
            }
        }
        return mine;
    }

}
