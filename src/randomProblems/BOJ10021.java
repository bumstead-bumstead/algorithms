package randomProblems;

import java.io.*;
import java.util.*;

public class BOJ10021 {

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        parents = new int[N];
        Arrays.fill(parents, -1);
        List<Integer[]> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodes.add(new Integer[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                Integer[] n1 = nodes.get(i);
                Integer[] n2 = nodes.get(j);
                edges.add(new Edge(i, j, (n1[0] - n2[0])*(n1[0] - n2[0]) + (n1[1] - n2[1])*(n1[1] - n2[1])));
            }
        }

        Collections.sort(edges);
        int sumOfCost = 0;
        int numOfEdge = 0;

        for (Edge edge : edges) {
            if (edge.cost < C) {
//                System.out.println(edge.cost);
                continue;
            }
            if (numOfEdge == N - 1) break;

            int p1 = find(edge.x);
            int p2 = find(edge.y);

            if (p1 == p2) continue;

            if (p1 < p2) parents[p2] = p1;
            else parents[p1] = p2;

            sumOfCost += edge.cost;
//            System.out.println(edge.cost);
            numOfEdge++;
        }

        if (numOfEdge < N - 1) System.out.println(-1);
        else System.out.println(sumOfCost);
        bw.close();
    }

    private static int find(int x) {
        if (parents[x] == -1) return x;

        return find(parents[x]);
    }
}

class Edge implements Comparable<Edge> {
    int x;
    int y;
    int cost;

    public Edge(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.cost - edge.cost;
    }
}
