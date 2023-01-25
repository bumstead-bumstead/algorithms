package graph;

import java.io.*;
import java.util.*;

public class BOJ1707 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] status;
    public static void main(String[] args) throws IOException {

        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            status = new int[V + 1];

            List<List<Integer>> graph = makeGraph(V, E);

            if (isBipartiteGraph(graph)) {
                System.out.println("YES");
                continue;
            }
            System.out.println("NO");
        }
    }

    private static boolean isBipartiteGraph(List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < graph.size(); i++) {
            if (status[i] != 0) continue;

            queue.add(i);
            status[i] = 1;
            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                for (int x : graph.get(tmp)) {
                    if (status[x] == status[tmp]) return false;
                    if (status[x] != 0) continue;

                    queue.add(x);
                    status[x] = (-1) * status[tmp];
                }
            }
        }

        return true;
    }

    private static List<List<Integer>> makeGraph(int V, int E) throws IOException{
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < V + 1; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            result.get(a).add(b);
            result.get(b).add(a);
        }

        return result;
    }
}

