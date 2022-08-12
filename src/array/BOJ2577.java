package array;

import java.io.*;

public class BOJ2577 {
    public static void main(String[] args) throws IOException {
        int[] answer = new int[10];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        char[] chars = Integer.toString(a * b * c).toCharArray();


        for (char x : chars) {
            answer[x - '0']++;
        }
        for (int x : answer) {
            bw.write(x + " ");
        }

        bw.close();
    }
}
