package stack;

import java.io.*;

public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repetations = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] tops = new int[repetations];
        String[] strings = br.readLine().split(" ");

        for (int i = 0; i < repetations; i++) {
            tops[i] = Integer.parseInt(strings[i]);
        }

        int tmpMax = tops[0];
        sb.append(0).append(" ");
        for (int i = 1; i < repetations; i++) {
            if (tmpMax < tops[i]) {
                tmpMax = tops[i];
                sb.append(0).append(" ");
                continue;
            }
            for (int j = i-1; j >= 0; j--) {
                if (tops[i] < tops[j]) {
                    sb.append(j+1).append(" ");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}
