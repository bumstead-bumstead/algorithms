package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {

    static int[][] confetti;
    static int[] answer = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        confetti = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                confetti[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        confettiRecur(0,0,n);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    private static void confettiRecur(int x, int y, int n) {
        if (n == 1) {
            answer[confetti[x][y]]++;
            return;
        }

        int a = isUnified(x, y, n);
        if (a != -1) {
            answer[a]++;
            return;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                confettiRecur(x + i * n/2, y + j * n/2, n/2);
            }
        }
    }

    private static int isUnified(int x, int y, int n) {
        int prev = confetti[x][y];
        for (int i = x; i < x+n; i++) {
            for (int j = y; j < y+n; j++) {
                if (prev != confetti[i][j]) return -1;
            }
        }
        return prev;
    }
}
