package topologicalSort;

import java.io.*;
import java.util.*;


//자신의 조상 중에, indegree가 제일 큰놈이 root, 제일 작은놈이 바로 윗단 -> 반대로
//조상이 자식들을 바라보는 방향으로. -> indegree가 1이면 직계 자식

/*
* 1. 모든 노드에 대해서 진입 차수 저장
* 2. 진입 차수가 0인 노드에 대해서 다음을 반복
* 2-1. 자신의 자식의 indgree--
* 2-2. 자신의 자식의 indgree가 0인 경우, 노드의 직계 자식으로 저장
* 2-3. 자식을 queue에 offer()
* */

//위상정렬와 관련된 키워드 - 방향 그래프, 트리, 순서, 원소간의 선후관계

public class BOJ21276_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[] people = br.readLine().split(" ");
        Arrays.sort(people);
        HashMap<String, Integer> peopleMapper = new HashMap<>();
        List<ArrayList<Integer>> graph = new ArrayList<>();
        List<ArrayList<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            peopleMapper.put(people[i], i);
            graph.add(new ArrayList<>());
            answer.add(new ArrayList<>());
        }

        int[] indegrees = new int[N];

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String[] tmp = br.readLine().split(" ");
            indegrees[peopleMapper.get(tmp[0])]++;
            graph.get(peopleMapper.get(tmp[1])).add(peopleMapper.get(tmp[0]));
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (indegrees[i] == 0) queue.offer(i);
        }
        bw.write(queue.size() + "");
        bw.newLine();
        for (int root : queue) {
            bw.write(people[root] + " ");
        }
        bw.newLine();

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
//            System.out.println("tmp = " + tmp);
            for (int child : graph.get(tmp)) {
                if (--indegrees[child] == 0) {
//                    System.out.println(child);
                    answer.get(tmp).add(child);
                    queue.offer(child);
                }
            }
//            System.out.println("----------");
        }

        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            List<Integer> tmpList = answer.get(i);
            Collections.sort(tmpList);
            sb.append(people[i]).append(" ").append(tmpList.size()).append(" ");
            for (int y : tmpList) {
                sb.append(people[y]).append(" ");
            }

            bw.write(sb.toString());
            bw.newLine();
        }

        bw.close();
    }
}
