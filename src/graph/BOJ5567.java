package graph;

import java.io.*;
import java.util.*;

public class BOJ5567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < M + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        int answer = 0;

        for (int i = 0; i < 2; i++) {
            int tmpLen = queue.size();
            for (int j = 0; j < tmpLen; j++) {
                int tmp = queue.poll();

                for (int x : graph.get(tmp)) {
                    if (visited[x]) continue;
                    queue.add(x);
                    visited[x] = true;
                }
            }
            answer += queue.size();
        }

        System.out.println(answer);

        bw.close();
    }
}
