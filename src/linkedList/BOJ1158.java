package linkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
* 1. linkedList와 iteator 활용 - 시간초과
* 2. circularLinkedList 구현 https://shipinthewater.tistory.com/5
* 3.
* */
public class BOJ1158 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        int K = a.charAt(2) - '0';
        int N = a.charAt(0) - '0';

        LinkedList<Integer> people = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            people.add(i);
        }

        int cnt = 0;
        while (!people.isEmpty()) {
            Iterator<Integer> iterator = people.iterator();
            while (iterator.hasNext()) {
                int tmp = iterator.next();
                if (++cnt == K) {
                    answer.add(tmp);
                    iterator.remove();
                    cnt = 0;
                    continue;
                }

            }
        }

        String answer2 = answer.toString();
        String answer3 = answer2.substring(1, answer2.length() - 1);
        System.out.println("<" + answer3 + ">");

    }
}
