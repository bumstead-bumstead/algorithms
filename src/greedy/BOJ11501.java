package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;


//이지랄할필요없이 뒤에서부터 검색하면 됨... 바보
public class BOJ11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int n2 = Integer.parseInt(br.readLine());
            int[] arr = new int[n2];
            StringTokenizer st = new StringTokenizer(br.readLine());

            Deque<Integer[]> queue = new LinkedList<>(); // [0] : 값, [1] : 인덱스
            queue.offer(new Integer[]{arr[0] = Integer.parseInt(st.nextToken()), 0});
            for (int j = 1; j < n2; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                while (!queue.isEmpty()) {
                    if (queue.peekLast()[0] > tmp) break;
                    queue.pollLast();
                }
                queue.offer(new Integer[]{tmp, j});
                arr[j] = tmp;
            }

            long answer = 0;
            int prev = 0;

            while (!queue.isEmpty()) {
                Integer[] tmp = queue.poll();
                for (int j = prev; j < tmp[1]; j++) {
                    if (tmp[0] > arr[j]) {
                        answer += tmp[0] - arr[j];
                    }
                }
                prev = tmp[1] + 1;
            }
            System.out.println(answer);
        }
    }
}
