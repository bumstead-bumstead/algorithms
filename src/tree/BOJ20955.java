package tree;

import java.io.*;
import java.util.*;


//유니온 파인드 복습~!11
public class BOJ20955 {
    static int cycleCnt = 0;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static List<Integer> cycles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visited = new boolean[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> startOfEachTrees = new ArrayList<>();
        //뭉탱이 찾기
        for (int i = 1; i < V + 1; i++) {
            if (visited[i]) continue;
            queue.offer(i);
            visited[i] = true;
            startOfEachTrees.add(i);

            while (!queue.isEmpty()) {
                int tmp = queue.poll();
                for (int x : graph.get(tmp)) {
                    if (visited[x]) continue;
                    visited[x] = true;
                    queue.offer(x);
                }
            }
        }

        int nGroups = startOfEachTrees.size();

        //사이클 찾기
//        for (int start : startOfEachTrees) {
//            Arrays.fill(visited, false);
//            dfs(start, -1);
//            while (!cycles.isEmpty()) {
//                Arrays.fill(visited, false);
//                graph.get(cycles.get(0)).remove(Integer.valueOf(cycles.get(1)));
//                graph.get(cycles.get(1)).remove(Integer.valueOf(cycles.get(0)));
//                cycles.clear();
//                dfs(start, -1);
//            }
//        }
        System.out.println(nGroups - 1 + (E + nGroups - 1) - (V-1));
        bw.close();
    }

//    private static void dfs(int tmp, int prev) {
//        visited[tmp] = true;
//        for (int x : graph.get(tmp)) {
//            if (prev == x) continue;
//            if (!cycles.isEmpty()) return;
//            if (visited[x]) {
//                cycleCnt++;
//                cycles.add(tmp);
//                cycles.add(x);
//                return;
//            }
//            dfs(x, tmp);
//        }
//    }
}
