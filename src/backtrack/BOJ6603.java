package backtrack;

import java.io.*;
import java.util.*;

public class BOJ6603 {
    static int n;
    static boolean[] visited;
    static int[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (st.countTokens() == 1) break;
            n = Integer.parseInt(st.nextToken());
            arr = new int[n];
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            backTrack(0, 0);
            bw.newLine();
        }
        bw.close();
    }

    private static void backTrack(int depth, int threshold) throws IOException{
        if (depth == 6) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) bw.write(arr[i] + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            backTrack(depth+1, i+1);
            visited[i] = false;
        }
    }

}
