package graph;

import java.io.*;
import java.util.*;


public class BOJ1043_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        boolean[] visited = new boolean[N + 1];
        List<Integer> truthList = new ArrayList<>();
        List<List<Integer>> parties = new ArrayList<>();
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            truthList.add(tmp);
        }

        for (int i = 0; i < M; i++) {
            parties.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                parties.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        boolean[][] graph = new boolean[N + 1][N + 1];
        for (List<Integer> list : parties){
            for (int i = 0; i < list.size()-1; i++) {
                for (int j = i+1; j < list.size(); j++) {
                    graph[list.get(i)][list.get(j)] = true;
                    graph[list.get(j)][list.get(i)] = true;
                }
            }
        }

//        for (int i = 0; i < graph.length; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }

        Queue<Integer> queue = new LinkedList<>();
        for (int x : truthList) {
            if (visited[x]) continue;
            visited[x] = true;
            queue.add(x);

            while (!queue.isEmpty()) {
                int tmp = queue.poll();

                for (int i = 1; i < N + 1; i++) {
                    if (!graph[tmp][i] || visited[i]) continue;

                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        int answer = 0;
        a : for (List<Integer> party : parties){
            for (int x : party) {
                if (visited[x]) continue a;
            }
            answer++;
        }

        System.out.println(answer);
    }
}
