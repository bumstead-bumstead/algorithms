package twoPointers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1. 배열을 오름차순 정렬한다.
* 2. lp = 0, rp = 1에 대해서, rp-lp를 계산한다.
* 3-0. lp + 1 = rp일 경우, rp++
* 3-1. m보다 작을 경우, rp++한다.
* 3-2. m일 경우, return한다.
* 3-3. m보다 클 경우, 현재 최솟값과 비교해서 넣은 후 lp++한다. 
* 
* 이분탐색 이용해야 한다면, 뺀 값 모두 계산해서 정렬한뒤 탐색하기... 이건 O(n^2)이긴 한뎅
* 
* */
public class BOJ2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int lp = 0;
        int rp = 1;
        int minLength = Integer.MAX_VALUE;
        int tmpLength;
        while (rp < n - 1) {
            tmpLength = arr[rp] - arr[lp];
//            System.out.println("rp = " + rp + ", lp = " + lp);
//            System.out.println("tmpLength = " + tmpLength);


            if (tmpLength == m) {
                System.out.println(m);
                return;
            } else if (tmpLength > m) {
                minLength = Math.min(tmpLength, minLength);
                if (rp - lp == 1) {
                    rp++;
                }
                lp++;
            } else {
                rp++;
            }
        }

        while(lp < n-1) {
//            System.out.println("rp = " + rp + ", lp = " + lp);
            tmpLength = arr[rp] - arr[lp++];

            if (tmpLength >= m){
                minLength = Math.min(tmpLength, minLength);
            }
        }

        System.out.println(minLength);

    }
}
