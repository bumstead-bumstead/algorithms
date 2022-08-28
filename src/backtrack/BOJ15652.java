package backtrack;


import java.io.*;
import java.util.StringTokenizer;

/*
* threshold 정해서, 중복 허용해서 하깅
* arr 필요없을것같은뎅??
* */
public class BOJ15652 {
    static int[] arr;
    static int n;
    static int m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        backTrack(0, 1);
        bw.close();
    }

    private static void backTrack(int k, int threshold) throws IOException{
        if (k == m) {
            for (int x : arr) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n+1; i++) {
            arr[k] = i;
            backTrack(k+1, i);
        }
    }
}
