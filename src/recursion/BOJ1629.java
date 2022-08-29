package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    static long C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());

        C = c;
        System.out.println(recursive(a,b,c));
    }

    private static long recursive(long a, long b, long c) {
        if (b == 1) return a % c;
        long val = recursive(a, b/2, c);

        if (b%2 == 1) return (val * val % c) * a % c;
        return val * val % c;
    }
}
