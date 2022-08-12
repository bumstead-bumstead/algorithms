package array;

import java.io.*;

public class BOJ10808 {
    public static void main(String[] args) throws IOException {
        //알파벳 아스키 97 to 122
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();
        int[] answer = new int[26];

        for (char x : chars) {
            answer[x-97]++;
        }

        for (int x : answer) {
            bw.write(x + " ");
        }
        bw.close();
    }
}
