package graph;

import java.io.*;
import java.util.*;

public class BOJ11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<Integer>[] graph = new List[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        Queue<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[V + 1];
        int answer = 0;

        for (int i = 1; i < V + 1; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            answer++;
            queue.offer(i);
            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                for (int x : graph[tmp]) {
                    if (visited[x]) continue;
                    queue.offer(x);
                    visited[x] = true;
                }
            }
        }

        bw.write(answer + "");
        bw.close();
    }
}
