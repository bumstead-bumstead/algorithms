package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegrees = new int[N + 1];
        List<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            indegrees[d]++;
            graph.get(s).add(d);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 1; i < N + 1; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }

        StringBuilder answer = new StringBuilder();

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            answer.append(tmp).append(" ");

            for (int next : graph.get(tmp)) {
                if (--indegrees[next] == 0) queue.offer(next);
            }
        }

        System.out.println(answer);

        bw.close();
    }
}
