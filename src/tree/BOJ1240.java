package tree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1240 {
    static int[][] graph;
    static int[][] distance;
    static boolean[] visited;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        distance = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[u][v] = d;
            graph[v][u] = d;
        }
        for (int i = 1; i < N + 1; i++) {
            visited[i] = true;
            fillDistance(i, i, 0);
            Arrays.fill(visited, false);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bw.write(distance[u][v] + "");
            bw.newLine();
        }
        bw.close();
    }

    private static void fillDistance(int start, int tmpLocation, int dist) {
        for (int i = 1; i < N + 1; i++) {
            if (graph[tmpLocation][i] == 0 || visited[i]) continue;
            visited[i] = true;
            distance[start][i] = dist + graph[tmpLocation][i];
            fillDistance(start, i, distance[start][i]);
        }
    }

}
