package simulation;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14503 {
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] room;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];
        room = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
//            System.out.println(r + ", " + c);
//            System.out.println("d = " + d);
            if (!visited[r][c]) {
                answer++;
                visited[r][c] = true;
            }
            if (!isCleanable(r, c)) {
                int backD = reverse(d);
                if (room[r + dx[backD]][c + dy[backD]] == 1) break;
                r += dx[backD];
                c += dy[backD];
            } else {
                for (int i = 0; i < 4; i++) {
                    if (--d == -1) d = 3;
                    int tmpR = r + dx[d];
                    int tmpC = c + dy[d];

                    if (room[tmpR][tmpC] == 0 && !visited[tmpR][tmpC]) {
                        r = tmpR;
                        c = tmpC;
                        break;
                    }
                }
            }

        }
        System.out.println(answer);
    }

    private static int reverse(int d) {
        return (d + 2) % 4;
    }

    private static boolean isCleanable(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int tmpR = r + dx[i];
            int tmpC = c + dy[i];

//            if (tmpR < 0 || tmpC < 0 || tmpR > n - 1 || tmpC > m - 1) continue;
            if (visited[tmpR][tmpC] || room[tmpR][tmpC] == 1) continue;
            return true;
        }
        return false;
    }
}
