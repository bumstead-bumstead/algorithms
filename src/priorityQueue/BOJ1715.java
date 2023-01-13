package priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        while (queue.size() > 1) {
            int tmp = queue.poll() + queue.poll();
            answer += tmp;
            queue.offer(tmp);
        }

        System.out.println(answer);
    }
}
