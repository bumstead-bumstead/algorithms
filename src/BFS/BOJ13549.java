package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


//n = k 인 경우에 틀렸습니다가 아니라 런타임 에러가 떠서 (while문을 true로 해둠) 한시간 버린문제
class Subin {
    int location;
    int seconds;

    public Subin(int location, int seconds) {
        this.location = location;
        this.seconds = seconds;
    }
}
public class BOJ13549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int k = Integer.parseInt(a[1]);
        if (n == k) {
            System.out.println(0);
            return;
        }
        boolean[] visited = new boolean[100001];
        Queue<Subin> queue = new LinkedList<>();
        queue.offer(new Subin(n, 0));
        visited[n] = true;

        while (!queue.isEmpty()) {
            Subin tmp = queue.poll();
            System.out.println(queue.size());

            if (tmp.location*2 <= 100000 && !visited[tmp.location*2]){
                if (tmp.location*2 == k) {
                    System.out.println(tmp.seconds);
                    return;
                } else {
                    queue.offer(new Subin(tmp.location * 2, tmp.seconds));
                    visited[tmp.location * 2] = true;
                }
            }


            if (tmp.location-1 >= 0 && !visited[tmp.location-1]) {
                if (tmp.location-1 == k) {
                    System.out.println(tmp.seconds+1);
                    return;
                } else {
                    queue.offer(new Subin(tmp.location - 1, tmp.seconds + 1));
                    visited[tmp.location - 1] = true;

                }
            }

            if (tmp.location + 1 <= 100000 && !visited[tmp.location + 1]) {
                if (tmp.location + 1 == k) {
                    System.out.println(tmp.seconds + 1);
                    return;
                } else {
                    queue.offer(new Subin(tmp.location + 1, tmp.seconds + 1));
                    visited[tmp.location + 1] = true;

                }
            }
        }
    }
}
