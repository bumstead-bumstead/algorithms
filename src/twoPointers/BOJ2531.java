package twoPointers;


import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
* 1. rp - lp + 1 이 k보다 작을 동안, set에 arr[rp++] append를 반복한다.
* 2. k가 되면, set에 coupon이 포함되어있는 지 확인하고 answer 갱신
*  */
public class BOJ2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        int rp = 0;
        HashSet<Integer> set = new HashSet<>();
        set.add(arr[0]);
        int[] visited = new int[3001];
        int tmpCnt = 0; //현재 고른 서로 다른 초밥 종류 개수

        for (int i = 0; i < k; i++) {
            if (!(visited[arr[rp]] > 0)) {
                tmpCnt++;
            }
            visited[arr[rp++]]++;
        }

//        System.out.println();

        for (int lp = 1; lp < n; lp++) {
//            System.out.println("------ " + lp + " : " + (rp % n) + "------");
//            printTempSushi(visited);
//            System.out.println("tmpCnt = " + tmpCnt);

            visited[arr[lp - 1]]--;
            if (visited[arr[lp-1]] < 1) tmpCnt--;

            if (visited[arr[rp]] < 1) {
                tmpCnt++;
            }
            visited[arr[rp]]++;
            rp = (rp + 1) % n;

            if (visited[c] > 0) answer = Math.max(answer, tmpCnt);
            else answer = Math.max(answer, tmpCnt + 1);
        }

        System.out.println(answer);
    }

    private static void printTempSushi(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
