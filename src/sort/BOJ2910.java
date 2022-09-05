package sort;

import javax.swing.text.html.parser.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://zoonvivor.tistory.com/132
//comparable로 (값, 개수)
public class BOJ2910 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            map.put(tmp, map.getOrDefault(tmp, 0)+1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        System.out.println(list);

        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });

        Iterator<Map.Entry<Integer,Integer>> iter = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> tmp = iter.next();

            for (int i = 0; i < tmp.getValue(); i++) {
                sb.append(tmp.getKey()).append(" ");
            }
        }
        System.out.println(sb);
    }
}
