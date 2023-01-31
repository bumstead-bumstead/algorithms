package tree;

import java.io.*;
import java.util.*;

public class BOJ14267 {
    static int[] chingchans; //각 사람 별로 시작칭찬 점수 저장
    static int[] answer;
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] bosses = new int[N + 1];
        chingchans = new int[N + 1];
        answer = new int[N + 1];
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();

        for (int i = 1; i < N; i++) {
            int tmpBoss = Integer.parseInt(st.nextToken());
            graph.get(tmpBoss).add(i + 1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            chingchans[p] += w;
        }

        chingchanDFS(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            sb.append(answer[i]).append(" ");
        }
        bw.write(sb.toString());
        bw.close();
    }

    private static void chingchanBFS(int boss) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(boss);

        while (!queue.isEmpty()) {
            int tmp = queue.poll();

            for (int x : graph.get(tmp)) {
                answer[x] += chingchans[x];
                queue.offer(x);
            }
        }
    }

    private static void chingchanDFS(int start, int w) {
        for (int x : graph.get(start)) {
            int tmpWeight = chingchans[x] + w;
            answer[x] += tmpWeight;
            chingchanDFS(x, tmpWeight);
        }
    }
}
