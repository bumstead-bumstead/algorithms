package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//https://ideone.com/F0uTwN 반례 참고 -> 근데 답 6 10 9임
//그냥 n= N부터 시작해서 매번 모든 격자 check할 수도 있을 듯
public class BOJ1780 {
    static int answer = 0;
    static int N;
    static int[][] paper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(paper0(0, 0, N, -1));
        System.out.println(paper0(0, 0, N, 0));
        System.out.println(paper0(0, 0, N, 1));

    }
    private static int paper0(int x, int y, int n, int target) {
//        System.out.println("paper2(x = " + x + ", y = " + y + ", n = "+ n + ") 실행");

        if (n == 1) {
            if (paper[x][y] == target) return -1;
            return 0;
        }

        int answer = 0;
        int isUnified = 0;

        //검사하는 영역이 unified면 -1 리턴
        //그 다음 단계에서는 합이 -9이면 1 리턴

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int tmp = paper0(x +  n/3 * i, y +  n/3 * j, n/3, target);
                if (tmp == -1) isUnified++;
                answer += tmp;
            }
        }
//        System.out.println("answer = " + answer);
//        System.out.println("-------------------------");
        if (answer == -9) {
            if (N == n) {
//                System.out.println("걸림");
                return 1;
            }
            return -1;
        }
        return answer + 2*isUnified; // -7 + 16 = 9
    }

}
