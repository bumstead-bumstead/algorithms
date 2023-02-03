package MST;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1368 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] weights = new int[N + 1];
        int[][] graph = new int[N + 1][N + 1];
        boolean[] visited = new boolean[N + 1];
        int min = 100001;
        int minIdx = -1;
        for (int i = 1; i < N + 1; i++) {
            weights[i] = Integer.parseInt(br.readLine());
            if (weights[i] < min) {
                minIdx = i;
                min = weights[i];
            }
        }

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        PriorityQueue<Edge3> queue = new PriorityQueue<>();
        visited[minIdx] = true;

        for (int i = 1; i < N + 1; i++) {
            if (visited[i]) continue;
            queue.offer(new Edge3(i, Math.min(weights[i], graph[i][minIdx])));
        }


        int walls = 1;
        int costs = min;

        while (walls < N) {
            Edge3 tmp = queue.poll();
            if (visited[tmp.next]) continue;

            walls++;
            costs += tmp.weight;
            visited[tmp.next] = true;

            for (int i = 1; i < N + 1; i++) {
                if (visited[i]) continue;
                queue.offer(new Edge3(i, Math.min(weights[i], graph[i][tmp.next])));
            }
        }

        System.out.println(costs);
        bw.close();
    }
}

class Edge3 implements Comparable<Edge3> {
    int next;
    int weight;

    public Edge3(int next, int weight) {
        this.next = next;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge3 edge3) {
        return this.weight - edge3.weight;
    }
}
