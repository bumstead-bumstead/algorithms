package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
* 가장 가까운 제곱수를 쌔려버림
*
* */
public class BOJ1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

//        int[] d = new int[n + 1];
        List<Integer> list = new ArrayList<>();
//        d[1] = 1;
//        d[2] = 2;
//        d[3] = 3;

        for (int i = 1; i < n + 1; i++) {
            long tmp = i*i;
            if (tmp > n) break;
            list.add((int) tmp);
        }

        int cnt = 0;
        int pointer = list.size() - 1;
        while(n > 0) {
            while (n >= list.get(pointer)) {
                System.out.println(list.get(pointer));
                n -= list.get(pointer);
                cnt++;
            }
            pointer--;
        }
        System.out.println(cnt);
    }
}
