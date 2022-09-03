package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ11652 {
    public static void main(String[] args) throws IOException {
        //정렬은 이렇게 써먹는다!!!

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long tmp = Long.parseLong(br.readLine());
            map.put(tmp, map.getOrDefault(tmp, (long) 0) + 1);
        }

        long answer = Integer.MIN_VALUE; //key 값
        long maxN = -1; //값

        for (long x : map.keySet()) {
            long tmp = map.get(x); //현재 개수, x는 키
//            System.out.println(x + ", " +tmp);

            if (tmp == maxN && x < answer) answer = x;
            else if (tmp > maxN) {
                maxN = tmp;
                answer = x;
            }
        }
        System.out.println(answer);
    }
}
