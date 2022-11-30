package twoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lp = 0; //set 첫 번째 원소 idx
        int rp = 1; //set 마지막 원소 idx+1, 현재 원소의 개수 = rp - lp
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        set.add(arr[0]);

        while (rp < n) {
            if (set.size() != rp - lp) {
                System.out.println((rp - 1) + "에서 중복 발견");
                while (arr[lp] != arr[rp - 1] || !set.isEmpty()) {
                    set.remove(arr[lp++]);
                }
                if (set.isEmpty()){
                    System.out.println(set.size() + "append : a");
                    set.add(arr[rp - 1]);
                    answer += set.size();
                }
                else {
                    lp++;
                    System.out.println(set.size() + "append : a");
                    answer += set.size();
                }
            }
            else {
                System.out.println(set.size() + "append : b");
                answer += set.size();
                set.add(arr[rp++]);
            }
        }

        //rp == n인 경우만 처리?
        if (set.size() == rp - lp) {
            answer += set.size();
        }

        System.out.println(answer);
    }
}
