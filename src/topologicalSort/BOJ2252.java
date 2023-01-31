package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegrees = new int[N + 1];
        List<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            indegrees[d]++;
            list.get(s).add(d);
        }
//        System.out.println(list);
//        System.out.println(Arrays.toString(indegrees));

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        StringBuilder answer = new StringBuilder();

        while (!queue.isEmpty()) {
//            System.out.println(queue);
            int tmp = queue.poll();
            answer.append(tmp).append(" ");

            for (int x : list.get(tmp)) {
                if (--indegrees[x] == 0) queue.offer(x);
            }
        }

        bw.write(answer.toString());

        bw.close();
    }
}
