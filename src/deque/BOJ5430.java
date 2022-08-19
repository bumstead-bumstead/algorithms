package deque;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/*
*
*
* */
public class BOJ5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int reps = Integer.parseInt(br.readLine());

        a : for (int i = 0; i < reps; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            char[] commands = br.readLine().toCharArray();
            LinkedList<Integer> deque = new LinkedList<>();
            int arrN = Integer.parseInt(br.readLine());
            String[] a = br.readLine().replaceAll("\\[", "").replaceAll("\\]", "").split(",");

            if (!a[0].equals("")){
                for (String x : a) {
                    deque.offer(Integer.parseInt(x));
                }
            }

            boolean reversed = false;

            for (char command : commands) {
                if (command == 'R') reversed = reversed ? false : true;
                else {
                    if (deque.isEmpty()) {
                        bw.write("error");
                        bw.newLine();
                        continue a;
                    }
                    if (reversed) {
                        deque.pollLast();
                    } else deque.poll();
                }
            }
            if (reversed) {
                if (!deque.isEmpty()) sb.append(deque.pollLast());
                while (!deque.isEmpty()) {
                    sb.append(",").append(deque.pollLast());
                }
            }
            else {
                if (!deque.isEmpty()) sb.append(deque.poll());
                while (!deque.isEmpty()) sb.append(",").append(deque.poll());
            }
            bw.write(sb.append("]").toString());
            bw.newLine();
        }
        bw.close();
    }
}
