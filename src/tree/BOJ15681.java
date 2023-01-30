package tree;

import java.io.*;
import java.util.*;


//잊지말자 메모이제이션!!!!
public class BOJ15681 {

    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static Node root;
    static Queue<Node> queue = new LinkedList<>();
    static int[] cnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int rootNumber = Integer.parseInt(st.nextToken());
        int nQueries = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        cnt = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        root = new Node(rootNumber);
        queue.offer(root);
        visited[rootNumber] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            for (int number : graph.get(tmp.number)) {
                if(visited[number]) continue;
                Node child = new Node(number);
                tmp.children.add(child);
                queue.offer(child);
                visited[number] = true;
            }
        }
        queue.clear();
        recursivelyGetNumberOfNodesInSubtree(root);
        for (int i = 0; i < nQueries; i++) {
            int query = Integer.parseInt(br.readLine());
            bw.write(cnt[query] + "");
            bw.newLine();
        }

        bw.close();
    }

    private static Node get(int number) {
        if (number == root.number) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            for (Node node : tmp.children) {
                if (node.number == number) return node;
                queue.offer(node);
            }
        }
        return null;
    }

    private static int recursivelyGetNumberOfNodesInSubtree(Node node) {
        if (node.children.isEmpty()) {
            cnt[node.number] = 1;
//            System.out.println(node.number + " 말단 채움");
            return 1;
        }

        int answer = 1;

        for (Node child : node.children) {
            answer += recursivelyGetNumberOfNodesInSubtree(child);
        }
//        System.out.println(node.number + " 채움 : " + answer);
        cnt[node.number] = answer;
        return answer;
    }

    private static int getNumberOfNodesInSubtree(Node node) {
        if (node.children.isEmpty()) return 1;
        int answer = 1;

        queue.offer(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            for (Node child : tmp.children) {
                answer++;
                queue.offer(child);
            }
        }

        return answer;
    }
}

class Node {
    int number;
    List<Node> children;

    public Node(int number) {
        this.number = number;
        children = new ArrayList<>();
    }
}
