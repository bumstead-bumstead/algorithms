package recursion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ11729 {
    static List<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        hanoiTower(n, 1, 3);

        bw.write(list.size()+"");
        bw.newLine();
        for (String x : list) {
            bw.write(x);
            bw.newLine();
        }

        bw.close();
    }

    private static void hanoiTower(int n, int start, int target) {
        if (n == 1) {
            list.add(start + " " + target);
//            System.out.println(start + " " + target);
            return;
        }

        //n-1개의 탑을 2번으로 옮김
        hanoiTower(n-1, start, 6-start-target);
        //n번째 원판을 3번으로 옮김
        hanoiTower(1, start, target);
        // 2번의 n-1개 탑을 3번으로 옮김
        hanoiTower(n-1, 6-start-target, target);
    }
}
