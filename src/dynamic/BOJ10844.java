package dynamic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
* d[i] = i자리의 계단수 갯수
* 매 단계별로 마지막 자리수 1~9 별로 갯수 저장
* 다음 단계에서 빼는 값 = 0, 9 개수 합
* 모듈러 연산 공부하기
* */
public class BOJ10844 {
    static long[] numNum = new long[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] d = new long[n];

        d[0] = 9;
        for (int i = 1; i < n; i++) {
            System.out.println(Arrays.toString(numNum));
            setLastNum();
            for (int j = 0; j <= 9; j++) {
                d[i] += numNum[j];
                d[i] %= 1000000000;
            }
        }
//        System.out.println("---------------------------");
//        System.out.println(Arrays.toString(d));
        System.out.println(d[n-1]);
    }

    private static void setLastNum() {
        long[] newThang = new long[10];
        newThang[0] = numNum[1]; //numNum의 모든 인자는 10억을 넘을 수 없음.
        newThang[9] = numNum[8];

        for (int i = 1; i < 9; i++) {
            newThang[i] = (numNum[i - 1] + numNum[i + 1]) % 1000000000;
        }
        numNum = newThang;
    }
}
