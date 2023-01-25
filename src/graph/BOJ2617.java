package graph;

import java.io.*;
import java.util.*;

public class BOJ2617 {
    static List<List<Integer>> graph = new ArrayList<>();
    static status[] statuses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        statuses = new status[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
            statuses[i] = new status();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            graph.get(s).add(b);
        }

        for (int i = 1; i < V + 1; i++) {
            bfs(i);
        }


        int threshold = (V + 1) / 2;
        int answer = 0;
        for (status x : statuses) {
            if (x.larger >= threshold || x.smaller >= threshold) answer++;
        }

        System.out.println(answer);
        bw.close();
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size() + 1];
        queue.add(start);
        int cnt = 0;

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for (int x : graph.get(tmp)) {
                if (visited[x]) continue;
                visited[x] = true;
                queue.add(x);
                statuses[x].smaller++;
                cnt++;
            }
        }

        statuses[start].larger += cnt;
    }
}

class status {
    int larger;
    int smaller;

    public status() {
        larger = 0;
        smaller = 0;
    }

    @Override
    public String toString() {
        return "larger : " + larger + ", " + "smaller : " + smaller;
    }
}
