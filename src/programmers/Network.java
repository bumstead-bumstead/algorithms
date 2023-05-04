package programmers;

import java.util.*;

public class Network {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int n, int[][] computers) {

        boolean[] visited = new boolean[n];
        int answer =0;

        for(int i=0; i < computers.length; i++) {
            if (visited[i]) continue;
            answer++;
            bfs(computers, visited, i, n);
            // System.out.println(Arrays.toString(visited));
        }


        return answer;
    }

    private void bfs(int[][] computers, boolean[] visited, int computer, int n) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(computer);
        visited[computer] = true;

        while(!queue.isEmpty()) {
            Integer tmpComputer = queue.poll();
            visited[tmpComputer] = true;
            // System.out.println(tmpComputer + " : check 시작");

            for (int i = 0; i < n; i++) {
                int next = computers[tmpComputer][i];

                if (visited[i] || next == 0) {
                    continue;
                }
                // System.out.println(tmpComputer+", " + i);

                queue.offer(i);
            }
        }
    }

}