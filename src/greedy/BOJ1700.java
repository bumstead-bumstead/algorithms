package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
* 0. 각 전기용품 별 사용횟수 유지
* 1. 멀티탭 구멍 수만큼 꽂음
* 2. 그 다음부터의 모든 전기용품에 대해서, 현재 꽂혀 있는지 확인
* 2-1. 그렇다면, continue
* 2-2. 그렇지 않다면, (현재 꽂힌 용품 중 남은 사용 횟수가 제일 작은 것)이 아니라 그 다음에 나오는데까지 가장 오래걸리는 것? 뺌
* 사용 횟수가 적은데 가까이 있으면?
*
* */
public class BOJ1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());
        List<Integer>[] machines = new List[101];
        int[] arr = new int[k];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (machines[tmp] == null) machines[tmp] = new ArrayList<>();
            machines[tmp].add(0, i);
            arr[i] = tmp;
        }


        //list로 선언해서...
        int[] tmpPlugs = new int[n];

        int cnt = 0; //tmpPlugs에 채운 숫자 수
        int idx = 0; // arr[idx]
        while (cnt < n && idx < k) {
            machines[arr[idx]].remove(machines[arr[idx]].size() - 1);
            if (contains(tmpPlugs, arr[cnt])) {
                idx++;
                continue;
            }
            tmpPlugs[cnt] = arr[idx];
            cnt++;
            idx++;
        }

        int answer = 0;

        //꽂을 전기용품마다 돌아간다
        for (int i = idx; i < k; i++) {
            machines[arr[i]].remove(machines[arr[i]].size() - 1); //???
            if (contains(tmpPlugs, arr[i])) continue;


            int willBeUnplug = -1; //뽑을 plug idx
            int tmpMax = Integer.MIN_VALUE; //뽑는 순서 중 가장 먼 것
            //뽑을 전기용품 찾기
            for (int j = 0; j < tmpPlugs.length; j++) {
                List<Integer> tmpList = machines[tmpPlugs[j]];
                //다시 꽂을 일 없는 거 바로뽑기
                if (tmpList.size() == 0) {
                    willBeUnplug = j;
                    break;
                }
                if (tmpMax < tmpList.get(tmpList.size()-1)) {
                    tmpMax = tmpList.get(tmpList.size()-1);
                    willBeUnplug = j;
                }
            }

            System.out.println((tmpPlugs[willBeUnplug]) + " 뽑았음 : " + (i + 1) + "번째에서");
            tmpPlugs[willBeUnplug] = arr[i];
            answer++;
        }

        System.out.println(answer);
    }

    private static boolean contains(int[] arr, int n) {
        for (int x : arr) {
            if (n == x) return true;
        }
        return false;
    }
}
