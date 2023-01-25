package graph;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ1707_matrix {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            visited = new boolean[V + 1];

            boolean[][] graph = makeGraph(V, E);

            if (isBipartiteGraph(graph)) {
                System.out.println("YES");
                continue;
            }
            System.out.println("NO");
        }
    }

    private static boolean isBipartiteGraph(boolean[][] graph) {
        for (int i = 1; i < graph.length; i++) {
            visited[i] = true;
            for (int j = 1; j < graph.length; j++) {
                if (!graph[i][j]) continue;
                for (int k = 1; k < graph.length; k++) { //인접의 인접들
                    if (!graph[j][k] || visited[k]) continue;
                    if (graph[k][i]) return false;
                }
            }
        }
        return true;
    }

    private static boolean[][] makeGraph(int V, int E) throws IOException {
        boolean[][] result = new boolean[V + 1][V + 1];

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            result[a][b] = true;
            result[b][a] = true;
        }

        return result;
    }
}
