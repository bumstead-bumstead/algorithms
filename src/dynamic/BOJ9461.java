package dynamic;


import java.io.*;
import java.util.Arrays;

//dp의 핵심은 쪼꼬만놈들의 해를 구하고, 고놈을 또이용해서 최종해에 도달하는것!!!
//D[i] = D[i-1] + d[i-4] (i > 5)?
public class BOJ9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        int max = -1;
        int[] tcs = new int[tc];
        for (int i = 0; i < tc; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp > max) max = tmp;
            tcs[i] = tmp;
        }

        long[] d = new long[max+1];
        d[1] = 1;
        d[3] = 1;
        d[4] = 2;
        d[5] = 2;
        for (int i = 6; i < max + 1; i++) {
            d[i] = d[i - 1] + d[i - 5];
        }

//        System.out.println(Arrays.toString(d));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int x : tcs) {
            bw.write(d[x] + "");
            bw.newLine();
        }
        bw.close();
    }
}
