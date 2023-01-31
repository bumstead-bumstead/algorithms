package topologicalSort;

import java.io.*;
import java.util.*;


//자신의 조상 중에, indegree가 제일 큰놈이 root, 제일 작은놈이 바로 윗단 -> 반대로
//조상이 자식들을 바라보는 방향으로. -> indegree가 1이면 직계 자식

public class BOJ21276 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[] people = br.readLine().split(" ");
        Arrays.sort(people);
        List<String> peopleList = Arrays.asList(people);
        HashMap<String, ArrayList<String>> graph = new HashMap<>();
        HashMap<String, ArrayList<String>> answer = new HashMap<>();
        HashMap<String, Integer> indegrees = new HashMap<>();
        for (String person : people) {
            graph.put(person, new ArrayList<>());
            indegrees.put(person, 0);
            answer.put(person, new ArrayList<>());
        }
        
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] tmp = br.readLine().split(" ");

            indegrees.put(tmp[0], indegrees.get(tmp[0]) + 1);
            graph.get(tmp[1]).add(tmp[0]);
        }

//        System.out.println(indegrees);
//        System.out.println(graph);

        Queue<String> queue = new LinkedList<>();
//        List<String> roots = new ArrayList<>();

        for (String person : people) {
            if (indegrees.get(person) == 0) queue.offer(person);
        }

        bw.write(queue.size()+"");
        bw.newLine();
        for (String root : queue) {
            bw.write(root + " ");
        }
        bw.newLine();

        while (!queue.isEmpty()) {
            String tmp = queue.poll();
//            System.out.println("tmp = " + tmp);
            for (String child : graph.get(tmp)) {
                indegrees.put(child, indegrees.get(child) - 1);
                if(indegrees.get(child) == 0) {
//                    System.out.println(child);
                    answer.get(tmp).add(child);
                }
                queue.offer(child);
            }
//            System.out.println("----------");
        }
        for (String x : people) {
            StringBuilder sb = new StringBuilder();
            List<String> tmpList = answer.get(x);
            Collections.sort(tmpList);
            sb.append(x).append(" ").append(tmpList.size()).append(" ");
            for (String y : tmpList) {
                sb.append(y).append(" ");
            }

            bw.write(sb.toString());
            bw.newLine();
        }

        bw.close();
    }
}
