package backtrack;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ15650 {
    static int[] arr;
    static boolean[] visited;
    static int n;
    static int m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());



        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n+1];
        backTrack(0, 0);
        bw.close();

    }

    private static void backTrack(int k, int threshold) throws IOException{
        if (k == m) {
            for (int i = 1; i < n+1; i++) {
                if (visited[i]) bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold+1; i < n+1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTrack(k+1, i);
                visited[i] = false;
            }
        }
    }
}
