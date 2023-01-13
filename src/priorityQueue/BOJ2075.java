package priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static binarySearchTree.BOJ23326.N;

public class BOJ2075 {
    private static List<PriorityQueue<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(new PriorityQueue<>(Comparator.reverseOrder()));
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list.get(j).offer(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < N - 1; i++) {
            list.get(findmMaxQueue()).poll();
        }

        System.out.println(list.get(findmMaxQueue()).poll());
    }

    private static int findmMaxQueue() {
        int tmpMax = -1000000001;
        int maxIdx = -1;
        for (int i = 0; i < list.size(); i++) {
            if (tmpMax < list.get(i).peek()) {
                tmpMax = list.get(i).peek();
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
