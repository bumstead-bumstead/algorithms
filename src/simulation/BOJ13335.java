package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Truck {
    int weight;
    int left;

    public Truck(int weight, int left) {
        this.weight = weight;
        this.left = left;
    }

    @Override
    public String toString() {
        return "weight = " + weight + ", left = " + left;
    }
}

public class BOJ13335 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //트럭 수
        int w = Integer.parseInt(st.nextToken()); //다리 길이
        int L = Integer.parseInt(st.nextToken()); //최대 하중
        int[] trucks = new int[n];
        Queue<Truck> queue = new LinkedList<>();
        int tmpWeight = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
        queue.offer(new Truck(trucks[0], w));
        tmpWeight += trucks[0];

        int totalTime = 1;

        for (int i = 1; i < n; i++) {
//            System.out.println(queue);
//            System.out.println(totalTime);

            if (tmpWeight + trucks[i] > L) {
                while (tmpWeight + trucks[i] > L) {
                    int tmpLeft = queue.peek().left;
                    for (Truck truck : queue) {
                        truck.left -= tmpLeft;
                    }

                    totalTime += tmpLeft;
                    tmpWeight -= queue.poll().weight;
                }
            } else {
                for (Truck truck : queue) truck.left--;
                totalTime++;
                if (!queue.isEmpty() && queue.peek().left == 0) tmpWeight -= queue.poll().weight;
            }
            queue.offer(new Truck(trucks[i], w));
            tmpWeight += trucks[i];

        }

        while (!queue.isEmpty()) {
            int tmpLeft = queue.peek().left;
            for (Truck truck : queue) truck.left -= tmpLeft;

            totalTime += tmpLeft;
            queue.poll();
        }

        System.out.println(totalTime);
    }
}
