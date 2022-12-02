package hash;

import java.io.*;
import java.util.*;

public class BOJ7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");

            if (tmp[1].equals("enter")) {
                set.add(tmp[0]);
            } else {
                set.remove(tmp[0]);
            }
        }

        List<String> answer = new ArrayList<>(set);
        Collections.sort(answer, Comparator.reverseOrder());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (String x : answer) {
            bw.write(x);
            bw.newLine();
        }
        bw.close();
    }
}
