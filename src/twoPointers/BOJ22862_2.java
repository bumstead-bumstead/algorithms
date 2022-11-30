package twoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22862_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int rp = 0;
        int answer = 0;
        int cntOdd = 0;

        for (int lp = 0; lp + answer < n; lp++) {
            if (lp != 0 && arr[lp - 1] % 2 != 0) cntOdd--;

            while (cntOdd <= k && rp < n) {
                if (arr[rp++] % 2 != 0) {
                    cntOdd++;
                }
            }
            answer = Math.max(answer, rp - lp - cntOdd);
        }

        System.out.println(answer);
    }
}
