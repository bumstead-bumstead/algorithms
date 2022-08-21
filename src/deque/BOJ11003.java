package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

class As implements Comparable<As>{
    private int num;
    private int index;

    public As(int num, int index) {
        this.index = index;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(As as) {
        return this.getNum() >= as.getNum() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "As{" +
                "num=" + num +
                ", index=" + index +
                '}';
    }
}

public class BOJ11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] NL = br.readLine().split(" ");
        int L = Integer.parseInt(NL[1]);
        int N = Integer.parseInt(NL[0]);
        PriorityQueue<As> priorityQueue = new PriorityQueue<>();
        int[] answer = new int[N];
        boolean[] filled = new boolean[N];

        int[] A = new int[Integer.parseInt(NL[0])];
        int cnt = 0;
        for (String x : br.readLine().split(" ")) {
            A[cnt++] = Integer.parseInt(x);
        }

        for (int i = 0; i < A.length; i++) {
            priorityQueue.offer(new As(A[i], i));
        }

        boolean completed;

        while (!priorityQueue.isEmpty()) {
            As a = priorityQueue.poll();
            int thresholdL = a.getIndex(); // - L + 1 > 0 ? a.getIndex() - L + 1 : 0
            int thresholdR = a.getIndex() + L - 1 < N-1 ? a.getIndex() + L -1 : N-1;
            for (int i = thresholdL; i <= thresholdR; i++) {
                if (filled[i]) continue;
                answer[i] = a.getNum();
                filled[i] = true;
            }

        }

        for (int x : answer) {
            sb.append(x).append(" ");
        }
        System.out.println(sb);
    }
}
