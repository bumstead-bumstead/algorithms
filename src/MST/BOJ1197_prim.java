package MST;

import java.io.*;
import java.util.*;

public class BOJ1197_prim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<ArrayList<Edge2>> graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(v1).add(new Edge2(v2, weight));
            graph.get(v2).add(new Edge2(v1, weight));
        }

        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Edge2> queue = new PriorityQueue<>();
        visited[1] = true;
        for (Edge2 e : graph.get(1)) {
            queue.offer(e);
        }

        int weightSum = 0;
        int edgeCnt = 0;

        while (edgeCnt < V - 1) {
            Edge2 tmp = queue.poll();
            if (visited[tmp.next]) continue;
            visited[tmp.next] = true;
            edgeCnt++;
            weightSum += tmp.weight;
            for (Edge2 e : graph.get(tmp.next)) queue.offer(e);
        }

        System.out.println(weightSum);
        bw.close();
    }
}

class Edge2 implements Comparable<Edge2> {
    int next;
    int weight;

    public Edge2(int next, int weight) {
        this.next = next;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge2 edge2) {
        return this.weight - edge2.weight;
    }
}