package binarySearchTree;


import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

//현재 위치로부터 얼마나 떨어졌는지를 어떻게 유지할 건지??
//현재 위치로부터 떨어진 거리를 저장하는 우선순위 큐
public class BOJ23326 {

    public static TreeSet<Integer> treeSet = new TreeSet<>();
    public static int location = 0;
    public static int N;
    public static int Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        String[] A = br.readLine().split(" ");

        for (int i = 0; i < A.length; i++) {
            if (A[i].equals("0")) continue;

            treeSet.add(i);
        }

        for (int i = 0; i < Q; i++) {
            String[] tmp = br.readLine().split(" ");
            if (tmp[0].equals("1")) {
                one(Integer.parseInt(tmp[1]));
            } else if (tmp[0].equals("2")) {
                two(Integer.parseInt(tmp[1]));
            } else {
                three(bw);
            }

        }
        bw.close();
    }

    private static void one(int n) {
        if (treeSet.contains(n - 1)) {
            treeSet.remove(n - 1);
            return;
        }
        treeSet.add(n - 1);
    }

    private static void two(int n) {
        location = (location + n) % N;
    }

    private static void three(BufferedWriter bw) throws IOException{
        int answer;
        if (treeSet.contains(location)) answer = 0;
        else if (treeSet.higher(location) == null) {
            if (treeSet.lower(location) == null) answer = -1;
            else answer = N - location + treeSet.first();
        }
        else answer = treeSet.higher(location) - location;
        bw.write(answer +"");
        bw.newLine();
    }
}
