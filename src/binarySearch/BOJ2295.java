package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;


//두개의 값을 묶은 후 어느 한쪽의 ㅏㄱㅄ을 이분탐색으로 찾아서 시간복잡도를 낮추는 아이디어

public class BOJ2295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        List<Integer> two = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                two.add(arr[i] + arr[j]);
            }
        }
        Collections.sort(two);
//        int tmpMax = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (binarySearch(two, arr[i] - arr[j]) == -1) continue;
                System.out.println(arr[i]);
                return;
            }
        }
//        System.out.println(tmpMax);
    }

    private static int binarySearch(List<Integer> arr, int target) {
        int st = 0;
        int en = arr.size() - 1;
        int mid = (st + en) / 2;

        while (st <= en) {
            if (arr.get(mid) == target) return arr.get(mid);
            else if (arr.get(mid) > target) en = mid -1;
            else st = mid + 1;

            mid = (st + en) / 2;
        }
        return -1;
    }
}
