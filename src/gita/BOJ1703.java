package gita;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ1703 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int leaves = 1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int years = Integer.parseInt(st.nextToken());
            if (years == 0) break;

            for (int i = 0; i < years; i++) {
                leaves *= Integer.parseInt(st.nextToken());
                leaves -= Integer.parseInt(st.nextToken());
            }

            bw.write(leaves + "");
            bw.newLine();
        }
        bw.close();
    }
}
