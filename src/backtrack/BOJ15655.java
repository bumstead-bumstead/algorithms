package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 이전 문제랑 똑같이 하되, 오름차순으로 출력, 중복 안되니께 threshold 정하깅*/
public class BOJ15655 {
    static int n;
    static int m;
    static boolean[] visited;
    static int[] arr;
    static int[] answer;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        arr = new int[n];
        answer = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        backTrack(0, 0);
        bw.close();
    }

    private static void backTrack(int depth, int threshold) throws IOException{
        if (depth == m) {
//            for (int i = 0; i < n; i++) if (visited[i]) bw.write(arr[i] + " ");
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n; i++) {
            answer[depth] = arr[i];
            backTrack(depth+1, i+1);
        }
    }
}
