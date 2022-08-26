package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1992 {
    static int[][] image;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        image = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] a = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                image[i][j] = Integer.parseInt(a[j]);
            }
        }

        System.out.println(quadTree(0, 0, n));
    }

    private static String quadTree(int x, int y, int n) {
        if (n == 1) {
            return image[x][y]+"";
        }

        int a = isUnified(x, y, n);
        if (a != -1) {
            return a+"";
        }

        String answer = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                answer += quadTree(x + n / 2 * i, y + n / 2 * j, n / 2);
            }
        }

        return "(" + answer + ")";

    }

    private static int isUnified(int x, int y, int n) {
        int prev = image[x][y];
        for (int i = x; i < x+n; i++) {
            for (int j = y; j < y+n; j++) {
                if (prev != image[i][j]) return -1;
            }
        }
        return prev;
    }
}
