package twoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int lp = 0;
        int rp = 0;
        int tmpSum = arr[0];
        int answer = 0;

        while (rp < arr.length - 1) {
            if (tmpSum == m) {
                answer++;
                tmpSum += arr[++rp];
                tmpSum -= arr[lp++];
            }
            else if (tmpSum < m) {
                tmpSum += arr[++rp];
            }
            else {
                tmpSum -= arr[lp++];
            }
        }

        while (lp < arr.length) {
            if (tmpSum == m) {
                answer++;
            }
            tmpSum -= arr[lp++];
        }
        System.out.println(answer);

    }
}
