package Math;

import java.io.*;

public class BOJ11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        for (int i = 2; i < n + 1; i++) {

            while (n % i == 0) {
                bw.write(i + "");
                bw.newLine();
                n /= i;
            }
            if (n == 1) break;
        }
        bw.close();
    }
}
