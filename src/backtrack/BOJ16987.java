package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.StringTokenizer;

class Egg {
    int weight;
    int durability;

    public Egg(int durability,int weight) {
        this.weight = weight;
        this.durability = durability;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "weight=" + weight +
                ", durability=" + durability +
                '}';
    }
}
public class BOJ16987 {
    static int n;
    static Egg[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Egg[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(breakEgg(0));

    }

    private static int breakEgg(int depth) {
//        System.out.println(Arrays.toString(arr));
        if (depth == n) {
//            System.out.println(Arrays.toString(arr));
            int cnt = 0;
            for (Egg egg : arr) {
                if (egg.durability <= 0) cnt++;
            }
            return cnt;
        }

        int answer = -1;
        int tmpBroken;
        boolean beated = false;

        if (arr[depth].durability <= 0) { //
//                visited[i] = true;
            return breakEgg(depth + 1);
//                visited[i] = false;
        }

        for (int i = 0; i < n; i++) {
            if (arr[i].durability <= 0 || depth == i) continue; // visited[i] ||
                beated = true;
//                visited[i] = true;
                arr[i].durability -= arr[depth].weight;
                arr[depth].durability -= arr[i].weight;

                tmpBroken = breakEgg(depth+1);

//                visited[i] = false;
                arr[i].durability += arr[depth].weight;
                arr[depth].durability += arr[i].weight;

            if (tmpBroken > answer) answer = tmpBroken;
        }

        if (!beated) return breakEgg(depth + 1);
        return answer;
    }
}
