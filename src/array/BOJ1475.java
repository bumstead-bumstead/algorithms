package array;

import java.io.*;
import java.util.Arrays;

public class BOJ1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String number = br.readLine();
        int[] answer = new int[10];
        for (char x : number.toCharArray()) {
            if (x == '6' || x == '9') {
                answer[9]++;
            }
            else answer[x-'0']++;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            if (answer[i] > max) max = answer[i];
        }

        int sixnine = answer[9]%2==0? answer[9]/2 : (answer[9]+1)/2;
        bw.write(max > sixnine ? max+"" : sixnine+"");

        bw.close();
    }
}
