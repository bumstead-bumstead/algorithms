package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
* 선긋기 시작점 기준으로 정렬, 시작점 같으면 끝점 작은 것부터
* 1. 검사하는 시작점과 이전 끝점 비교
* 1-1. 이전 끝점이 크거나 같다면, tmpEnd = 검사하는 끝점
* 1-2. 그렇지 않다면 answer += tmpEnd - tmpStart, tmpEnd, tmpStart 검사하는 선으로 갱신
* */
class Pair implements Comparable<Pair> {
    int start;
    int end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pair pair) {
        if (pair.start == this.start) return this.end - pair.end;
        return this.start - pair.start;
    }

    @Override
    public String toString() {
        return "(" + start + " to " + end + ")";
    }
}

public class BOJ2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Pair> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.offer(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        long answer = 0;
        Pair tmp = queue.poll();
        int tmpStart = tmp.start;
        int tmpEnd = tmp.end;

        while (!queue.isEmpty()) {
            tmp = queue.poll();


            //tmp.end가 tmpEnd보다 더 클때만..
            if (tmpEnd >= tmp.start && tmpEnd < tmp.end) tmpEnd = tmp.end;
            else if (tmpEnd < tmp.start){
                answer += tmpEnd - tmpStart;
                tmpStart = tmp.start;
                tmpEnd = tmp.end;
            }
        }

        answer += tmpEnd - tmpStart;

        System.out.println(answer);
    }
}
