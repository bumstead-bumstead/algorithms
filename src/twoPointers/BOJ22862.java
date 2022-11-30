package twoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22862 {
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

        int lp = 0;
        int rp = 0;
        int answer = -1;
        int cntOdd = 0;

        while (rp < n) {
            //홀수 개수가 k보다 작으면, 계속 rp++
            while (cntOdd < k) {
                if (rp == n) break;
                answer = Math.max(answer, rp - lp + 1 - cntOdd);
//                System.out.println(lp + " to " + rp + ", answer = " + answer);
                if (arr[rp++] % 2 != 0) cntOdd++;
            }
            //홀수 개수가 k가 되면, answer 갱신하고 그렇지 않게 될때까지  lp++
            while (cntOdd == k) {
                if (arr[lp++] % 2 != 0) {
                    cntOdd--;
                }
            }
        }

        System.out.println(answer);
    }
}
