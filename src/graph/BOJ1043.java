package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        HashSet<Integer> truthList = new HashSet<>();
        List<HashSet<Integer>> parties = new ArrayList<>();
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            truthList.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            parties.add(new HashSet<>());
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                parties.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (HashSet<Integer> set : parties){
            for (int x : truthList) {
                if (set.contains(x)) {
                    for (int y : set) {
                        truthList.add(y);
                    }
                    set.clear();
                    break;
                }
            }
        }
        System.out.println(truthList);
        int answer = 0;
        a : for (HashSet<Integer> party : parties) {
            if (party.isEmpty()) continue;

            for (int x : truthList) {
                if (party.contains(x)) continue a;
            }
            answer++;
        }
        System.out.println(answer);
    }
}
