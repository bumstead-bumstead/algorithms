package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


//이전 문제에서 visited없애고, threshold 없앰
public class BOJ15665 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0);
        bw.close();
    }

    private static void backTrack(int depth) throws IOException {

        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }
        int prev = -1;
        for (int i = 0; i < n; i++) {

            if (arr[i] == prev) continue;

            answer[depth] = arr[i];

            backTrack(depth+1);

            prev = arr[i];
        }
    }
}
