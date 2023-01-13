package priorityQueue;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ11286 {

    private static PriorityQueue<Integer> neg = new PriorityQueue<>(Comparator.reverseOrder());
    private static PriorityQueue<Integer> pos = new PriorityQueue<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {


        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp < 0) neg.add(tmp);
            else if (tmp > 0) pos.add(tmp);
            else {
                poll();
            }
        }

        bw.close();
    }

    private static void poll() throws IOException {
        if (pos.isEmpty()) {
            if (neg.isEmpty()) bw.write("0");
            else bw.write(neg.poll() + "");
        } else if (neg.isEmpty()) bw.write(pos.poll() + "");
        else if ((-1) * neg.peek() > pos.peek())bw.write(pos.poll() + "");
        else if ((-1) * neg.peek() < pos.peek()) bw.write(neg.poll() + "");
        else bw.write(neg.poll() + "");

        bw.newLine();
    }
}
