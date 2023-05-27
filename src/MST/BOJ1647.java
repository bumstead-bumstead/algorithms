package MST;

import java.io.*;
import java.util.*;

public class BOJ1647 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i < N+1; i++) {
            parent[i] = i;
        }

        List<Integer[]> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Integer[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        edges.sort((Comparator.comparingInt(o -> o[2])));

        int linkCnt = 0;
        int cost = 0;
        int maxCost = 0;

        for (Integer[] edge : edges) {

            if (linkCnt == N - 1) break;
            if (union(edge[0], edge[1])) continue;

            cost += edge[2];
            maxCost = edge[2];
            linkCnt++;
        }

        System.out.println(cost-maxCost);

        bw.close();
    }

    public static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) return true;

        if (px < py) parent[y] = parent[x];
        else parent[x] = parent[y];

        return false;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
