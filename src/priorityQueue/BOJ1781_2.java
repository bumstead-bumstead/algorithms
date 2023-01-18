package priorityQueue;


import java.io.*;
import java.util.*;

/*
* 0. 문제를 데드라인 오름차순으로 (데드라인이 같으면 컵라면 내림차순) 정렬한 뒤에 순차적으로 탐색
* 1. 우선순위 큐(해결할 문제를 모아놓음. 컵라면 수 수로 내림차순, 컵라면 수 같으면 데드라인 오름차순?)의 길이가 검사하는 놈의 데드라인과 비교
* 1-1. 큐 길이가 더 길면 두낫띵
* 1-2. 큐 길이가 더 짧으면, peek()이 더 짧은 동안 poll() 반복
* 2. 해당 문제 큐에 offer()
* 3. queue 모든 컵라면 수 합 출력
* */
public class BOJ1781_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<Problem> problems = new ArrayList<>();

        PriorityQueue<Problem> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            problems.add(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(problems, new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.deadline == o2.deadline) return o2.cups - o1.cups;
                return o1.deadline - o2.deadline;
            }
        });

        for (Problem problem : problems) {
            if (queue.size() == problem.deadline) {
                if (queue.peek().cups > problem.cups) continue;
                queue.poll();
            }
            queue.offer(problem);
        }
        System.out.println(queue);
        int answer = 0;
        while (!queue.isEmpty()) {
            answer += queue.poll().cups;
        }
        bw.write(answer + "");
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

    @Override
    public String toString() {
        return "cups = " + cups + ", deadLine = " +
                deadline;
    }
}