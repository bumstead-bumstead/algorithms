package greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//compareTo 수정하기
class Flower implements Comparable<Flower>{
    int[] start;
    int[] end;

    public Flower(int s1, int s2, int e1, int e2) {
        this.start = new int[]{s1, s2};
        this.end = new int[]{e1, e2};
    }

    @Override
    public int compareTo(Flower f1) {
        if (this.start[0] == f1.start[0] && this.start[1] == f1.start[1]) {
            if (this.end[0] == f1.end[0]) return this.end[1] - this.end[1];
            return this.end[0] - f1.end[0];
        }

        if (this.start[0] == f1.start[0]) return this.start[1] - f1.start[1];

        return this.start[0] - f1.start[0];
    }

    @Override
    public String toString() {
        return "Flower{" +
                "start=" + Arrays.toString(start) +
                ", end=" + Arrays.toString(end) +
                '}';
    }
}
public class BOJ2457 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Flower> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.offer(new Flower(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
//        System.out.println(queue);

        int nFlower = 0;
        Flower tmp;
        Flower prev = new Flower(0, 0, 3, 1);

        for (int i = 0; i < n; i++) {
            if (prev.end[0] >= 12) break;

            tmp = queue.poll();
            if (tmp.start[0] >= tmp.end[0] && tmp.start[1] >= tmp.end[1]) continue;

            if (!isEarlier(tmp, prev) || isLonger(prev, tmp)) {
                continue;
            }
            //다음 꽃 (종료 날짜가 더 큰)이 가능하면 continue;
            if (!queue.isEmpty() && isEarlier(queue.peek(), prev) && isLonger(queue.peek(), tmp)) {
                continue;
            }

            prev = tmp;
            nFlower++;
        }

        if (prev.end[0] < 12) System.out.println(0); //불가능
        else if (prev.start[0] == 0 && prev.start[1] == 0) System.out.println(0);
        else System.out.println(nFlower);
    }

    private static boolean isEarlier(Flower f1, Flower f2) {
        return f1.start[0] < f2.end[0] || (f1.start[0] == f2.end[0] && f1.start[1] <= f2.end[1]);
    }

    private static boolean isLonger(Flower f1, Flower f2) {
        return f1.end[0] > f2.end[0] || (f1.end[0] == f2.end[0] && f1.end[1] >= f2.end[1]);
    }
}
