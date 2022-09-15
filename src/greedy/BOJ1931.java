package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting> {
    int start;
    int end;

    public Meeting(int start, int time) {
        this.start = start;
        this.end = time;
    }

    @Override
    public int compareTo(Meeting m) {
        if (m.end == this.end) return this.start - m.start;
        return this.end - m.end;
    }

    @Override
    public String toString() {
        return "(" + this.start + ", " + this.end + ")";
    }
}

public class BOJ1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Meeting> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.offer(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int answer = 1;
        Meeting prev = queue.poll();
        while (!queue.isEmpty()) {
            Meeting tmp = queue.poll();

            if (prev.end <= tmp.start) {
                answer++;
                prev = tmp;
            }
        }

        System.out.println(answer);
    }
}
