package graph;

import java.io.*;
import java.util.*;

public class BOJ1260 {

    static Queue<Integer> queue = new LinkedList<>();
    static Stack<Integer> stack = new Stack<>();
    static boolean[] visited;
    static boolean[][] graph;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        graph = new boolean[V + 1][V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i < E + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1][v2] = true;
            graph[v2][v1] = true;
        }
//        visited[start] = true;
//        bw.write(start + " ");
        DFS2(start);
        bw.newLine();
        Arrays.fill(visited, false);
        BFS(start);
        bw.close();
    }

    private static void DFS(int start) throws IOException{

        for (int i = 1; i < graph.length; i++) {
            if (!graph[start][i] || visited[i]) continue;
            visited[i] = true;
            bw.write(i + " ");
            DFS(i);
        }
    }

    private static void DFS2(int start) throws IOException {
        stack.push(start);
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            if (visited[tmp]) continue;
            visited[tmp] = true;
            bw.write(tmp + " ");
            for (int i = graph.length - 1; i > 0; i--) {
                if (!graph[tmp][i] || visited[i]) continue;
//                System.out.println(i + ": 써먹을거임");
                stack.push(i);
            }
        }
    }

    private static void BFS(int start) throws IOException {
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            bw.write(tmp + " ");
            for (int i = 1; i < graph.length; i++) {
                if (visited[i] || !graph[tmp][i]) continue;
                queue.offer(i);
                visited[i] = true;
            }
        }
    }
}
