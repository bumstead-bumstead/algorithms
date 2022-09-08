package dynamic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 0 1 01 101
public class BOJ1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcases = Integer.parseInt(br.readLine());

        int[] dZero = new int[41];
        int[] dOne = new int[41];
        dZero[0] = 1;
        dOne[1] = 1;
        for (int i = 2; i < 41; i++) {
            dZero[i] = dZero[i - 1] + dZero[i - 2];
            dOne[i] = dOne[i - 1] + dOne[i - 2];
        }


        for (int i = 0; i < testcases; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] zeroandOne = new int[2];
            zeroandOne[0] = dZero[n];
            zeroandOne[1] = dOne[n];

            System.out.println(zeroandOne[0] + " " + zeroandOne[1]);
        }
    }
}
