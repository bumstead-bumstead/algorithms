package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
    static int[] arr;
    static int n;
    static int cnt;
    static int s;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(0, 0, true);
        System.out.println(cnt);
    }

    private static void backTrack(int k, int sum, boolean gong) {

        if (k == n) {
            if (sum == s && !gong) {
                cnt++;
            }
            return;
        }

            backTrack(k+1, sum, gong);
            backTrack(k+1, sum+arr[k], false);

    }
}
