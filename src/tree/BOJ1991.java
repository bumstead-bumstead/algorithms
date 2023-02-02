package tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ1991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Node2> nodes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            nodes.add(new Node2(Character.toString('A' + i)));
        }
        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            if (!tmp[1].equals(".")) {
                nodes.get(tmp[0].charAt(0) - 'A').left = nodes.get(tmp[1].charAt(0) - 'A');
            }
            if (!tmp[2].equals(".")) {
                nodes.get(tmp[0].charAt(0) - 'A').right = nodes.get(tmp[2].charAt(0) - 'A');
            }
        }

        preorder(nodes.get(0));
        bw.newLine();
        inorder(nodes.get(0));
        bw.newLine();
        postorder(nodes.get(0));
        bw.close();
    }

    private static void preorder(Node2 node) throws IOException{
        bw.write(node.alphabet);
        if (!(node.left == null)) preorder(node.left);
        if (!(node.right == null)) preorder(node.right);
    }
    private static void inorder(Node2 node) throws IOException{
        if (!(node.left == null)) inorder(node.left);
        bw.write(node.alphabet);
        if (!(node.right == null)) inorder(node.right);
    }
    private static void postorder(Node2 node) throws IOException{
        if (!(node.left == null)) postorder(node.left);
        if (!(node.right == null)) postorder(node.right);
        bw.write(node.alphabet);
    }
}

class Node2 {
    String alphabet;
    Node2 left;
    Node2 right;

    public Node2(String alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public String toString() {
        return alphabet;
    }
}
