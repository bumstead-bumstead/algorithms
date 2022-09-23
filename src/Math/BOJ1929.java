package Math;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] eratos = new boolean[m + 1]; //false면 소수
        eratos[0] = true;
        eratos[1] = true;

        for (int i = 2; i <= m; i++) {
            if (!eratos[i]) {
                for (int j = i * 2; j < m + 1; j += i) {
                    eratos[j] = true;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = n; i < m + 1; i++) {
            if (!eratos[i]){
                bw.write(i + "");
                bw.newLine();
            }
        }
        bw.close();
    }
}

