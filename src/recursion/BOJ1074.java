package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {
    static int r;
    static int c;
    static int cnt = 0;
    static boolean finished = false;

    static int[] dx = {0, 0, 1, 1}; //행
    static int[] dy = {0, 1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int squareN = 1;
        for (int i = 0; i < n; i++) {
            squareN *= 2;
        }

//        z(1, 1, squareN);
        System.out.println(z2(r, c, squareN));;
    }

    private static void z(int row, int col, int n) { //row, col은 시작 좌표
        if (finished) return;
        if (n == 1) {
            cnt++;
            if (row == r+1 && col == c+1) {
                System.out.println(--cnt);
                finished = true;
                return;
            }
//            System.out.println(row + " " + col);
            return;
        }

        z(row, col, n / 2);
        z(row, col + n / 2, n / 2);
        z(row + n / 2, col, n / 2);
        z(row + n / 2, col + n / 2, n / 2);
    }

    private static int z2(int r, int c, int n) {
        if (n == 1) return 0;
        int half =  n/2;
        if (r >= half && c >= half) return 3 * half * half + z2(r - half, c - half, n / 2);
        else if (r < half && c >= half) return half * half + z2(r, c - half, n / 2);
        else if (r < half && c < half) return z2(r, c, n / 2);
        else return 2 * half * half + z2(r - half, c, n / 2);
    }
}
