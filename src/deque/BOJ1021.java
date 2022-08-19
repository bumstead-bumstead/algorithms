package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


//이 문제 deque는 linkedList로 쓰면 된다.. 이색기가 deque 구현해서 indexOf()랑 deque기능 다쓸 수 ㅣㅇㅆ음
public class BOJ1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        String[] b = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        Queue<Integer> queue = new LinkedList<>(); //왼쪽에서 뽑는놉
        Deque<Integer> deque = new ArrayDeque<>(); //오른쪽에서 뽑는놈의 자식
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
            deque.offer(i);
        }

        int tmp;
        int cntl=0;
        int cntr=0;
        int answer=0;
        for (String x : b) {
            tmp = Integer.parseInt(x);
            while (queue.peek() != tmp) {
                cntl++;
                queue.offer(queue.poll());
            }
            queue.poll();

            while (deque.peek() != tmp) {
                cntr++;
                deque.offerFirst(deque.pollLast());
            }
            deque.poll();

            answer += cntl < cntr ? cntl : cntr;
            cntl =0; cntr = 0;
        }
        System.out.println(answer);
    }
}
