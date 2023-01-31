package tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2250 {
    static int[] width;
    static int[][] tree;
    static Node2250[] nodes;
    static List<ArrayList<Node2250>> nodesByDepth;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        width = new int[N + 1];
        tree = new int[N + 1][2];
        nodes = new Node2250[N + 1];
        nodesByDepth = new ArrayList<>();
        nodesByDepth.add(new ArrayList<>());

        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            nodes[i] = new Node2250(i);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if (left != -1) {
                nodes[tmp].left = nodes[left];
                visited[left] = true;
            }
            if (right != -1) {
                nodes[tmp].right = nodes[right];
                visited[right] = true;
            }
        }
        Node2250 root = nodes[0];

        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                root = nodes[i];
                break;
            }
        }

        size(root, 1);
        root.position = 1;
        if (root.left != null) root.position += root.left.size;

        position(root);
        int maxWidth = -1;
        int maxDepth = -1;
        for (int i = 1; i < nodesByDepth.size(); i++) {
            ArrayList<Node2250> tmpList = nodesByDepth.get(i);
            int tmpWidth = tmpList.get(tmpList.size() - 1).position - tmpList.get(0).position + 1;
            if (tmpWidth > maxWidth) {
                maxWidth = tmpWidth;
                maxDepth = i;
            }
        }

        bw.write(maxDepth + " " + maxWidth);

        bw.close();
    }

    private static int size(Node2250 tmp, int depth) { //size 채우고 depth 별로 저장

        if (nodesByDepth.size() - 1 < depth) nodesByDepth.add(new ArrayList<>());
        nodesByDepth.get(depth).add(tmp);

        if (tmp.left != null) tmp.size += size(tmp.left, depth + 1);
        if (tmp.right != null) tmp.size += size(tmp.right, depth + 1);
        return tmp.size;
    }

    private static void position(Node2250 tmp) {

        if (tmp.left != null) {
            tmp.left.position = tmp.position - 1;
            if (tmp.left.right != null) tmp.left.position -= tmp.left.right.size;
            position(tmp.left);
        }
        if (tmp.right != null) {
            tmp.right.position = tmp.position + 1;
            if (tmp.right.left != null) tmp.right.position += tmp.right.left.size;
            position(tmp.right);
        }
    }
}

class Node2250 {
    int number;
    int position;
    Node2250 left;
    Node2250 right;

    int size;

    public Node2250(int number) {
        this.number = number;
        this.size = 1;
    }
}