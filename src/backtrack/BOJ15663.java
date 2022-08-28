package backtrack;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


//다시풀기... list에 저장해서 매번싹다확인해서 시간초과
public class BOJ15663 {
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

            if (visited[i] || arr[i] == prev) continue;

            answer[depth] = arr[i];
            visited[i] = true;
            backTrack(depth+1);
            visited[i] = false;
            prev = arr[i];
        }
    }
}
