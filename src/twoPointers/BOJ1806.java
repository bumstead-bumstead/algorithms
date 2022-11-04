package twoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (arr[0] >= s) {
            System.out.println(1);
            return;
        }

        int left = 0;
        int right = 1;
        int tmpSum = arr[0] + arr[1];
        int minLength = Integer.MAX_VALUE;

        while (right < n - 1) {
            System.out.println("left = " + left + ", rigtht = " + right);
            System.out.println("tmpSum = " + tmpSum);
            if (tmpSum >= s) {
                minLength = Math.min(minLength, right - left + 1);
                if (right == left) tmpSum += arr[++right];
                tmpSum -= arr[left++];
            } else {
                tmpSum += arr[++right];
            }
        }

        while (tmpSum >= s) {
            System.out.println("left = " + left + ", rigtht = " + right);
            System.out.println("tmpSum = " + tmpSum);
            minLength = Math.min(minLength, right - left + 1);
            tmpSum -= arr[left++];
        }

        if (minLength == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(minLength);
    }
}
