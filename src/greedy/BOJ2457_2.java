package greedy;
/*
* 1. 시작 날짜 순으로 정렬 (같으면 종료 날짜)
* 2. 3월 1일 이전에 시작하는 놈들 중에 제일 긴놈 tmpFlower 저장
* 3. 다음 꽃들 중에 '날짜가 겹치면서 종료 날짜 제일 큰놈 저장
* 반복
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Flower2 implements Comparable<Flower2> {
    int start;
    int end;

    public Flower2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Flower2 flower) {
        if (this.start == flower.start) return this.end - flower.end;
        return this.start - flower.start;
    }

    @Override
    public String toString() {
        return "(" + start + ", " + end + ")";
    }

}
public class BOJ2457_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Flower2> list = new ArrayList<>();
        int start;
        int end;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String month = st.nextToken();
            String day = st.nextToken();
            if (day.length() == 1) start = Integer.parseInt(month + "0" + day);
            else start = Integer.parseInt(month + day);

            month = st.nextToken();
            day = st.nextToken();
            if (day.length() == 1) end = Integer.parseInt(month + "0" + day);
            else end = Integer.parseInt(month + day);

            list.add(new Flower2(start, end));
        }

        Collections.sort(list);
//        System.out.println(list);

        int tmpEnd = 301;
        int tmpMax = 0; //현재 최대 end날짜
        int answer = 0;
        int idx = 0;

        while (idx < list.size()) {
            Flower2 flower = list.get(idx);

            if (flower.start > tmpEnd) {
                if (tmpMax == 0) {
                    System.out.println(0);
                    return;
                }
//                System.out.println(tmpMax + " 사용");
                tmpEnd = tmpMax;
                tmpMax = 0;
                answer++;
                continue;
            }

            tmpMax = tmpMax > flower.end ? tmpMax : flower.end;
            if (tmpMax >= 1201) {
                tmpEnd = tmpMax;
                answer++;
                break;
            }
            idx++;
        }

        if (tmpEnd < 1201) System.out.println(0);
        else System.out.println(answer);
    }
}
