package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long tmpMax = 0;

        for (int i = n-1; i >= 0 ; i--) {
            if (tmpMax > arr[i]*(n-i)) continue;
            tmpMax = arr[i] * (n - i);
        }
        System.out.println(tmpMax);
    }
}
