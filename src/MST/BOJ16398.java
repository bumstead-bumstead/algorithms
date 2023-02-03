package MST;

import java.io.*;
import java.util.*;

public class BOJ16398 {

    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
//        if (N == 1) {
//            System.out.println(0);
//            return;
//        }

        int[][] graph = new int[N + 1][N + 1];
        List<Edge4> list = new ArrayList<>();

        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N + 1; j++) {
                if (graph[i][j] == 0) continue;
                list.add(new Edge4(i, j, graph[i][j]));
            }
        }

        Collections.sort(list);

        int edgeCnt = 0;
        long costSum = 0;
        parents = new int[N + 1];
        Arrays.fill(parents, -1);

        for (int i = 0; i < list.size(); i++) {
            if (edgeCnt == N - 1) {
                break;
            }
            Edge4 tmp = list.get(i);

            if (union(tmp.v1, tmp.v2)) {
                edgeCnt++;
                costSum += tmp.weight;
            }
        }
        System.out.println(costSum);
        bw.close();
    }

    private static boolean union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);

        if (v1 == v2) {
            return false;
        }
        if (v1 < v2) parents[v2] = v1;
        else parents[v1] = v2;
        return true;
    }

    private static int find(int x) {
        if (parents[x] == -1) return x;
        return find(parents[x]);
    }
}

class Edge4 implements Comparable<Edge4> {
    int v1;
    int v2;
    int weight;

    public Edge4(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge4 edge4) {
        return weight - edge4.weight;
    }

    @Override
    public String toString() {
        return v1 + ", " + v2 + " : " + weight;
    }
}