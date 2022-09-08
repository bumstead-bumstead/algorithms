package dynamic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1. D[i] = i까지의 모든 부분합 중의 최대 부분합..?
* 혼자 못풀었음 다시보기
* */
public class BOJ1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] d = new long[n + 1];
        d[1] = arr[1];
        long max = arr[1];
        for (int i = 2; i < n+1; i++) {

            //이전까지의 최대 부분합 + i까지 랑 현재 i의 원소랑 비교. 현재 최대부분합보다 i원소가 크면 i부터 시작하는게 이득임
            //자기자신을 포함시켜서 값이 커지면 무조건 이어가야함. (당연)
            //계속 양수만 나오면, 어차피 다포함시킬거. 끊어지는 부분은 아마도 음수 직후 쯤.
            //부분합이 음수에, i번째 원소가 양수면, 여기서 cut 부분합이 양수면 무조건 가져가야되는거 ->요거다
            if (d[i-1] + arr[i] > arr[i]) {
                d[i] = d[i - 1] + arr[i];
            } else d[i] = arr[i];

            max = Math.max(d[i], max);
        }

//        System.out.println(Arrays.toString(d));
        System.out.println(max);

    }

    private static long max(long[] d) {
        long max = -1001;

        for (long x : d) {
            if (x > max) max = x;
        }

        return max;
    }
}
