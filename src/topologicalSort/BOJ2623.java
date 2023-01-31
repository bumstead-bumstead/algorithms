package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegrees = new int[N + 1];

        for (int i = 0; i < M; i++) {
            String[] tmp = br.readLine().split(" ");

            for (int j = 1; j < tmp.length - 1; j++) {
                int s = Integer.parseInt(tmp[j]);
                int d = Integer.parseInt(tmp[j + 1]);

                indegrees[d]++;
                graph.get(s).add(d);
            }
        }
//        System.out.println(Arrays.toString(indegrees));
//        System.out.println(graph);
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < indegrees.length; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }

        List<Integer> answer = new ArrayList<>();

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            int tmp = queue.poll();
            answer.add(tmp);

            for (int x : graph.get(tmp)) {
                if (--indegrees[x] == 0) queue.offer(x);
            }
        }

        if (answer.size() != N) {
            bw.write("0");
            bw.close();
            return;
        }

        for (int x : answer) {
            bw.write(x + "");
            bw.newLine();
        }
        bw.close();
    }
}
