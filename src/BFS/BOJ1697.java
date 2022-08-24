package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


//100000을 넘어가는 경우 예외처리안해서 한번 틀림
public class BOJ1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int k = Integer.parseInt(a[1]);
        if (n==k) {
            System.out.println(0);
            return;
        }
        boolean[] visited = new boolean[100001];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        visited[n] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int threshold = queue.size();
            for (int i = 0; i < threshold; i++) {
                System.out.println(queue.size());
                int[] tmp = createArray(queue.poll());
                for (int x : tmp) {
                    if (x > 100000 || x < 0) continue;
                    if (x == k) {
                        System.out.println(++cnt);
                        return;
                    }
                    if (!visited[x]) {
                        queue.offer(x);
                        visited[x] = true;
                    }
                }
            }
            cnt++;
        }
    }
    private static int[] createArray(int a) {
        int[] result = {a - 1, a + 1, a * 2};
        return result;
    }
}
