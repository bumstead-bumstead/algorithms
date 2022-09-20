package greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
* 그리디의 핵심은 이거다. 딱보고 그냥 후려쳐서 풀어버리면 되는 것 아닌가? 생각이 드는 문제들!!!
* */
public class BOJ1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int answer = 0;
        int idx = n-1;

        while (idx > 0) {
            if (arr[idx] * arr[idx-1] > 0) {
                answer += arr[idx] * arr[idx - 1];
                idx -= 2;

                //이거 아님
            } else if (arr[idx] == 0){
                idx -= 2;
            }
        }
        if (idx == 0) answer += arr[0];

        System.out.println(answer);
    }
}
