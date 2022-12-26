package sihum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* yon sei
* korea
* */
public class prbB {
    //y o n s e i -> i = 1일 때 n = 6으로 쪼개짐
    //k o r e a -> i = 1일 때 n = 5로 쪼개짐
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String kucc = br.readLine();
        String ycc = br.readLine();
        int len = kucc.length() < ycc.length() ? kucc.length() : ycc.length();
        //kucc / n == 0 && ycc / (n+1) == 0을 만족시키는 최소의 n구하깅

        for (int i = len; i >= 1; i--) { //i가 n이 되어ㅑ..
//            System.out.println("n = " + i);
            if (kucc.length() % i == 0 && ycc.length() % (i+1) == 0) {
                System.out.println(assemble(kucc, ycc, i));
                return;
            }
        }
        System.out.println(-1);
    }

    private static String assemble(String kucc, String ycc, int n) {
        int unitK = kucc.length() / n;
        int unitY = ycc.length() / (n + 1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(ycc.substring(i * unitY, (i + 1) * unitY));
            result.append(kucc.substring(i * unitK, (i + 1) * unitK));
        }
        result.append(ycc.substring(n * unitY, (n + 1) * unitY));
        return result.toString();
    }
}
