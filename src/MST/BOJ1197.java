package MST;

import java.io.*;
import java.util.*;

public class BOJ1197 {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge> list = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Edge(v1, v2, weight));
        }
        Collections.sort(list);

        parents = new int[V + 1];
        Arrays.fill(parents, -1);


        int edgeCnt = 0;
        int weightSum = 0;

        for (Edge edge : list) {
            if (edgeCnt == V-1) break;
            int p1 = find(edge.v1);
            int p2 = find(edge.v2);
            if(p1 == p2) continue;

            if (p1 < p2) parents[p2] = p1;
            else parents[p1] = p2;
            edgeCnt++;
            weightSum += edge.weight;
        }

        System.out.println(weightSum);
        bw.close();
    }

    private static int find(int v1) {
        if (parents[v1] == -1) return v1;

        return find(parents[v1]);
    }
}

class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

}
