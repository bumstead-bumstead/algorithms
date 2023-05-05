package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSumSam {

    public int solution(int[] queue1, int[] queue2) {
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int x : queue1) q1.offer(x);
        for (int x : queue2) q2.offer(x);

        int len1 = queue1.length;
        int len2 = queue2.length;
        int numberOfMoves = 0;

        long total = sum1 + sum2;

        if (total % 2 == 1) return -1;
        if (sum1 == sum2) return numberOfMoves;

        while (!q1.isEmpty() && !q2.isEmpty() && numberOfMoves < (len1 + len2) * 4) {

            if (sum1 > sum2) {
                int move = q1.poll();
                q2.offer(move);
                sum1 -= move;
                sum2 += move;
            } else {
                int move = q2.poll();
                q1.offer(move);
                sum1 += move;
                sum2 -= move;
            }

            numberOfMoves++;
            if (sum1 == sum2) return numberOfMoves;
        }


        int answer = -1;
        return answer;
    }
}

