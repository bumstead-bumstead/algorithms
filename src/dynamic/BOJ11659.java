package dynamic;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * D[i] = arr[1]부터 arr[i]까지의 합
 * D[i] = D[i-1] + arr[i]
 *
 *
 * prefix sum이라는 기술!! 기억!!!!
 * */
public class BOJ11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];
        int[] d = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        d[1] = arr[1];

        for (int i = 2; i < n+1; i++) {
            d[i] = d[i - 1] + arr[i];
        }

//        System.out.println(Arrays.toString(d));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            bw.write((-1)*d[Integer.parseInt(st.nextToken())-1] + d[Integer.parseInt(st.nextToken())] + "");
            bw.newLine();
        }

        bw.close();
    }
}
