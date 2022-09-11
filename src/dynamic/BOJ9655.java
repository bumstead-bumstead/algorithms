package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        int n = Integer.parseInt(br.readLine());
        int n = 10;

        int answer = 0;
        answer += n/3 + n%3;

        System.out.println(answer % 2 == 0 ? "CY" : "SK");
    }
}
