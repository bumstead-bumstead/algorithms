package dynamic;


import java.io.*;
import java.util.Arrays;



//다시보기.     추가적인 정보를 저장해야하는 경우ㅠ
public class BOJ12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] d = new int[n + 1]; // i에서 1까지 가는 데 최소 계산
        int[] trace = new int[n + 1]; // i번째 위치로 이동하는
        d[1] = 0;

        for (int i = 2; i < n + 1; i++) {
            trace[i] = i-1;
            d[i] =d[i - 1] + 1;

            if (i % 2 == 0) {
                if (d[i] > d[i / 2] + 1) {
                    d[i] = d[i / 2] + 1;
                    trace[i] = i / 2;
                }
            }
            if (i % 3 == 0) {
                if (d[i] > d[i / 3] + 1) {
                    d[i] = d[i / 3] + 1;
                    trace[i] = i / 3;
                }
            }
        }

//        System.out.println(Arrays.toString(d));
        System.out.println(Arrays.toString(trace));
        int cur = n;

        while (true) {
            System.out.print(cur + " ");
            if (cur == 1) break;

            cur = trace[cur];
        }
    }
}
