package priorityQueue;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int middle = Integer.parseInt(br.readLine());
        bw.write(middle + "");
        bw.newLine();
        PriorityQueue<Integer> under = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> upper = new PriorityQueue<>();

        for (int i = 1; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp < middle) under.offer(tmp);
            else upper.offer(tmp); //tmp와 같은 값도 오른쪽으로 넣어버린다

            //under, upper 배열 길이 차이가 1보다 크면, middle 조정
            int dSize = under.size() - upper.size();

            //under가 더 긴 경우
            if (dSize >= 1) {
                upper.offer(middle);
                middle = under.poll();
            } else if (dSize < -1) {
                under.offer(middle);
                middle = upper.poll();
            }

            bw.write(middle + "");
            bw.newLine();
        }

        bw.close();

    }
}
