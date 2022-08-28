package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//이전문제랑 똑같지만, 비내림차순이니까 자기보다 작은 숫자는 그뒤에 나오면 안됨. -> threshold
public class BOJ15657 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
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

    private static void backTrack(int depth, int threshold) throws IOException {
        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n; i++) {
            answer[depth] = arr[i];
            backTrack(depth+1, i);
        }
    }
}
