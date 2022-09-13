package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ9084_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            int money = Integer.parseInt(br.readLine());

            int[] d = new int[money + 1]; // d[i] = i원을 만드는 동전의 조합 수
            d[0] = 1;

//            for (int coin : coins) {
//                d[coin]++;
//            }


            //이 방법은 coin부터 더하니까 coin 이전의 숫자들에 coin을 더하는 계산이 누락됨.
//            for (int coin : coins) {
//                if (coin > money)  continue;
//                d[coin]++;
//                for (int j = coin; j + coin < money+1; j++) {
//                    d[j + coin] += d[j]; //j를 만드는 조합에다가 coin을 추가 -> 이러면 저번 풀이랑 똑같잖아..
//                }
//                System.out.println(Arrays.toString(d));
//            }

            for (int coin : coins) {
                for (int j = coin; j < money + 1; j++) {
                    d[j] += d[j - coin];
                }
            }

            System.out.println(d[money]);
//            System.out.println(Arrays.toString(d));
        }
    }
}
