package greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 그리디의 핵심은 이거다. 딱보고 그냥 후려쳐서 풀어버리면 되는 것 아닌가? 생각이 드는 문제들!!!
* */
public class BOJ1744_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp > 0) pos.add(tmp);
            else neg.add(tmp);
        }

        Collections.sort(pos, Collections.reverseOrder());
        Collections.sort(neg);

        int answer = 0;

        for (int i = 0; i < pos.size(); i += 2) {
            if (i == pos.size() - 1) {
                answer += pos.get(i);
                break;
            }
            if (pos.get(i+1) == 1) {
                answer += pos.get(i) + 1;
                continue;
            }
            if (pos.get(i) == 1 && pos.get(i + 1) == 1) {
                answer += 2;
                continue;
            }
            answer += pos.get(i) * pos.get(i + 1);
        }

        //홀수개면 마지막 하나 남음
        for (int i = 0; i < neg.size() - 1; i += 2) {
            answer += neg.get(i) * neg.get(i + 1);
        }
        if (neg.size() % 2 != 0) answer += neg.get(neg.size() - 1);

        System.out.println(answer);
    }
}
