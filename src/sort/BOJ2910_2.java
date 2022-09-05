package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2910_2 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);

        str = br.readLine().split(" ");
        HashMap<Integer, Integer> list = new LinkedHashMap<Integer, Integer>();

        //해쉬맵을 이용
        for (int i = 0; i < N; i++) {
            // 키가 존재하면 value를 +1
            if (list.containsKey(Integer.parseInt(str[i]))) {
                list.replace(Integer.parseInt(str[i]), list.get(Integer.parseInt(str[i])) + 1);
            }
            // 키가 없을시 value 값을 1로 생성한다.
            else {
                list.put(Integer.parseInt(str[i]), 1);
            }
        }
        System.out.println(list);
        //key들을 모두 배열에 저장한다.
        ArrayList<Integer> v = new ArrayList<Integer>(list.keySet());

        //배열에 저장된 키들의 값을 value값으로 내림차순 정렬한다.
        Collections.sort(v, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                //list.get(b) 와 list.get(a)의 위치가 바뀌면 오름차순이 된다.
                return Integer.compare(list.get(b), list.get(a));
            }
        });
        //Iterator를 통해서 출력한다.
        Iterator<Integer> it = v.iterator();
        while (it.hasNext()) {
            Integer element = it.next();
            for(int i=0; i<list.get(element); i++){
                System.out.print(element+" ");
            }

        }
    }
}
