package tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11725 {

    private static int[] parent;
    private static boolean[] visited;
    private static List<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        parent = new int[N + 1];
        visited = new boolean[N + 1];
        visited[1] = true;

        DFS(1);

        for (int i = 2; i < parent.length; i++) {
            bw.write(parent[i]+"");
            bw.newLine();
        }

        bw.close();
    }

    private static void DFS(int start) {
        for (int tmp : graph.get(start)) {
            if (visited[tmp]) continue;
            visited[tmp] = true;
            parent[tmp] = start;
            DFS(tmp);
        }
    }
}
