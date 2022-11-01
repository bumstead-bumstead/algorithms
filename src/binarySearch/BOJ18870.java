package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sortedArr = Arrays.copyOf(arr, arr.length);

        Arrays.sort(sortedArr);

        List<Integer> arr2 = new ArrayList<>();

        int prev = Integer.MAX_VALUE;
        for (int x : sortedArr) {
            if (x == prev) continue;

            arr2.add(x);
            prev = x;
        }

        StringBuilder result = new StringBuilder();
        for (int x : arr) {
            result.append(binarySearch(arr2, x) + " ");
        }

        System.out.println(result);
    }

    private static int binarySearch(List<Integer> arr, int target) {
        //arr 모든 원소에 대해서 이 함수 호출? 이거 해보자
        //아님 매핑해놓은 곳에서 매번 검색?

        int st = 0;
        int en = arr.size() - 1;
        int mid = (st + en) / 2;

        while (st <= en) { // <, <= 둘다 똑같은 결과 맞지??
            if (arr.get(mid) == target) return mid;
            else if (arr.get(mid) > target) en = mid - 1;
            else st = mid + 1;

            mid = (st + en) / 2;
        }

        return -1;
    }
}
