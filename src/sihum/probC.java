package sihum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class probC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String query = st.nextToken();
            if (query.equals("c")) {
                list.add(st.nextToken());
//                set.add(st.nextToken());
            } else if (query.equals("d")) {
//                set.remove(st.nextToken());
                list.remove(list.indexOf(st.nextToken()));
            } else {
                StringBuilder sb = new StringBuilder();
                for (String x : list) {
                    sb.append(x).append(" ");
                }

                System.out.println(sb.toString());
            }
        }
    }
}
