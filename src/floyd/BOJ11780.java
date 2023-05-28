package floyd;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11780 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

//        int[][] costs = new int[N + 1][N + 1];
        int[][] minCost = new int[N + 1][N + 1];
        int[][] next = new int[N + 1][N + 1];

        for (int[] a : minCost){
            Arrays.fill(a, 100001);
        }
        for (int i = 0; i < N+1; i++) {
            minCost[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            minCost[a][b] = Math.min(minCost[a][b], c);
            next[a][b] = b;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    if (minCost[j][k] <= minCost[j][i] + minCost[i][k]) continue;
                    minCost[j][k] = minCost[j][i] + minCost[i][k];
                    next[j][k] = next[j][i];
                }
            }
        }

        for (int i = 1; i < N+1; i++) {
            int[] x = minCost[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < N+1; j++){
                int num = x[j];
                sb.append(num >= 100001 ? 0 : num).append(" ");
            }
            bw.write(sb.toString());
            bw.newLine();
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(i).append(" ");
                int tmpStart = i;
                int tmpCnt = 2;

                if (next[tmpStart][j] == 0) {
                    bw.write(0 + "");
                    bw.newLine();
                    continue;
                } else if (next[tmpStart][j] == j) {
                    bw.write("2 " + i + " " + j);
                    bw.newLine();
                    continue;
                }
                while (next[tmpStart][j] != j) {
                    sb.append(next[tmpStart][j]).append(" ");
                    tmpStart = next[tmpStart][j];
                    tmpCnt++;
                }

                if (sb.length() == 2) {
                    bw.write(0);
                }
                else {
                    bw.write(tmpCnt + " " + sb.append(j));
                }
                bw.newLine();

            }
        }

        bw.close();
    }
}
