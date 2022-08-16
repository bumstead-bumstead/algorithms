package linkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


/* 가능한 원인
* 1. 매 바퀴 돌아가는 if
* 2. list 이용해서 저장하는 방식 -> 아님
* https://vanillacreamdonut.tistory.com/11*/
public class BOJ1158Queue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        int K = Integer.parseInt(a[1]);
        int N = Integer.parseInt(a[0]);

        Queue<Integer> people = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            people.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        while (people.size() > 1) {

            for (int i = 0; i < K-1; i++) {
                people.offer(people.poll());
            }
            sb.append(people.poll()).append(", ");
        }

        sb.append(people.poll()).append(">");
        System.out.println(sb);
        br.close();
    }
}
