package dynamic;


import java.io.*;
import java.util.StringTokenizer;

// D[i][j] = i~j까지의 문자의 펠린드롬 여부
public class BOJ10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[][] d = new boolean[n + 1][n + 1];
        String[] arr = br.readLine().split(" ");

        for (int i = 1; i < n+1; i++) {
            for (int j = i; j < n+1; j++) {
                d[i][j] = isPalindrome(i, j, arr);
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write((d[a][b] ? 1 : 0) + "");
            bw.newLine();
        }
        bw.close();
    }

    private static boolean isPalindrome(int i, int j, String[] arr) {
        if (i==j) return true;
        int cnt = 0;
        for (int k = i-1; k < j; k++) {
            if (!arr[k].equals(arr[j-1-cnt++])) return false;
        }
        return true;
    }
}
