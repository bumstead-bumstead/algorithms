package dynamic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//n이 최대 1000이라 O(n^2)도 된당...
//d[i] = arr[i]가 마지막 원소인 최대 증가수열 합
//0~i-1까지 검사하면서 arr[i]를 받아들일 수 있는 놈들 확인. 그리고 그색기들중에서 제일큰놈으로 갱신
public class BOJ11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] d = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        d[0] = arr[0];

        for (int i = 1; i < n; i++) {
            d[i] = arr[i];

            //j번째 원소가 i번째 원소보다 작으면서, (증가여야대니깡~) d[j]+arr[i]가 더크면 재껴버림
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && d[j] + arr[i] > d[i]) {
                    d[i] = d[j] + arr[i];
                }
            }
        }
        System.out.println(max(d));
    }

    private static int max(int[] d) {
        int answer = -1;
        for (int x : d) {
            if (x > answer) answer = x;
        }
        return answer;
    }
}
