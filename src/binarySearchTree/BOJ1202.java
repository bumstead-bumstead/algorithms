package binarySearchTree;


import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 이진 트리는 대소 비교, 범위 검색, 빠른 삽입/검색/삭제에 유리하다
*
* */

public class BOJ1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long answer = 0;

        Queue<Jewel> jewels = new PriorityQueue<>(Comparator.reverseOrder());
        TreeMap<Integer, Integer> bags = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < k; i++) {
            int tmp = Integer.parseInt(br.readLine());
            bags.put(tmp, bags.getOrDefault(tmp, 0) + 1);
        }
        System.out.println(bags);

        while (!jewels.isEmpty() && !bags.isEmpty()) {
            Integer tmpBag;
            Jewel tmpJewel = jewels.poll();

            if (bags.containsKey(tmpJewel.weight)) {
                tmpBag = tmpJewel.weight;
            } else {
                tmpBag = bags.higherKey(tmpJewel.weight);
            }

            if (tmpBag == null) continue;
            bags.put(tmpBag, bags.get(tmpBag) - 1);
            if (bags.get(tmpBag) < 1) bags.remove(tmpBag);

            System.out.println(bags);

            answer += tmpJewel.price;
        }

        System.out.println(answer);
    }
}

class Jewel implements Comparable<Jewel> {
    int weight;
    int price;

    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Jewel jewel) {
        return this.price - jewel.price;
    }

    @Override
    public String toString() {
        return "[" + weight + ", " + price + "]";
    }
}
