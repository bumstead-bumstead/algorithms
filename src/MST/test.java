package MST;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class test {
    public static void main(String[] args) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>();
        List<Integer> a = Arrays.asList(1, 2);
        List<Integer> b = Arrays.asList(2, 3);
        List<Integer> c = Arrays.asList(1, 5);




        queue.offer(a);
        queue.offer(b);
        queue.offer(c);
        System.out.println(queue);
    }
}
