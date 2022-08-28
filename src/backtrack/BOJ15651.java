package backtrack;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15651 {
    static int n;
    static int m;
    static int[] arr;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];


        backTrack(0);
        bw.close();
    }

    private static void backTrack(int k) throws IOException{
        if (k == m) {
            for (int x : arr) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i < n+1; i++) {
            arr[k] = i;
            backTrack(k+1);
        }
    }
}
