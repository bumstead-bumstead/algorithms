package priorityQueue;


import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* 컵라면 수 기준으로 내림차순 정렬(같으면 데드라인 오름차순) -> 우선순위 큐에 저장
* 0. 각 문제를 poll()하며 검사
* 1. 해당 문제와 같은 데드라인 날짜를 가진 문제를 해결한 적이 있는지 확인
* 1-1. 있다면, 해당 문제의 데드라인 날짜-- 한후 1번 반복
* 1-2. 없다면, answer += 해당 컵라면 수
* */
public class BOJ1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        boolean[] days = new boolean[N + 1];
        days[0] = true;
        PriorityQueue<Problem> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int maximumCups = 0;
        int impossible = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.offer(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while (!queue.isEmpty()) {
            Problem tmp = queue.poll();
            int tmpDeadline = tmp.deadline;
            if (tmp.deadline <= impossible) continue;
            while (tmpDeadline > 0 && days[tmpDeadline]) tmpDeadline--;
            if (tmpDeadline == 0) {
                impossible = tmp.deadline;
                continue;
            }
            days[tmpDeadline] = true;
            maximumCups += tmp.cups;
        }

        bw.write(maximumCups + "");
        bw.close();

    }

}

class Problem implements Comparable<Problem> {
    int cups;
    int deadline;

    public Problem(int deadline, int cups) {
        this.cups = cups;
        this.deadline = deadline;
    }

    @Override
    public int compareTo(Problem problem) {
        if (this.cups == problem.cups) return problem.deadline - this.deadline;
        return this.cups - problem.cups;
    }
}