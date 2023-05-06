package programmers;

public class WayToSchool {

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] minMoves = new int[n + 1][m + 1];

        for (int[] puddle : puddles) {
            int row = puddle[1];
            int col = puddle[0];

            minMoves[row][col] = -1;
        }

        minMoves[1][1] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (i == 1 && j == 1) continue;

                if (minMoves[i][j] == -1) {
                    minMoves[i][j] = 0;
                    continue;
                }
                minMoves[i][j] = (minMoves[i - 1][j] + minMoves[i][j - 1]) % 1000000007;
            }
        }

        return minMoves[n][m];
    }
}

