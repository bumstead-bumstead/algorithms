package sihum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class probC_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        LinkedHashMap<String, Integer> map2 = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String query = st.nextToken();

            if (query.equals("nugu")) {
                StringBuilder sb = new StringBuilder();
                for (String x : map2.keySet()) {
                    sb.append(x).append(" ");
                }

                System.out.println(sb.toString());
            }
            else {
                String person = st.nextToken();
                if (query.equals("c")) {
                    map2.put(person, map.getOrDefault(person, 0) + 1);
                } else if (query.equals("d")) {
                    map2.remove(person);
                }
            }
        }

    }

}
