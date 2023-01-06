package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1351 {
    static Map<Long, Long> map = new HashMap<>();
    static long p;
    static long q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map.put((long) 0, (long) 1);

        System.out.println(getSequenceNumber(n));
    }

    private static long getSequenceNumber(long n) {
        if (!map.containsKey(n)) map.put(n, getSequenceNumber(n / p) + getSequenceNumber(n / q));
        return map.get(n);
    }
}

/*
* n의 범위가 10^12였기 때문에 메모이제이션은 불가능했다. hashmap에 저장함으로써 메모리를 적게 사용하면서 탐색의 시간복잡도를 낮추는 효과도 얻을 수 있다.
* 해시 자료구조의 핵심은 "반복적인 탐색이 일어나는 경우", "반복적인 중복 검사가 일어나는 경우" 등이 있다. 얏호우~
*
* */
