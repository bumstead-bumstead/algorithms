package twoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
* 다시 풀기
* */
public class BOJ13144_2 {
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
        int rp = 0; //set 마지막 원소 idx
        int answer = 1;
        HashSet<Integer> set = new HashSet<>();
        set.add(arr[0]);

        while (rp < arr.length - 1) {
            rp++;
            while (set.contains(arr[rp])) {
                set.remove(arr[lp++]);
            }
            set.add(arr[rp]);
            answer += set.size();
        }

        System.out.println(answer);
    }
}
