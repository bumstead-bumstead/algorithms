package backtrack;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//오름차순 정렬해놓고 시작??
public class BOJ15654 {
    static int n;
    static int m;
    static int[] arr; //
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n];
        answer = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        backTrack(0);
        bw.close();
    }

    private static void backTrack(int k) throws IOException{
        if (k == m) {
//            for (int i = 0; i < n; i++) {
//                if (visited[i]) bw.write(arr[i] + " ");
//            }
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            answer[k] = arr[i];
            backTrack(k+1);
            visited[i] = false;
        }
    }
}
