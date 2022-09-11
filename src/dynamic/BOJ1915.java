package dynamic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* D[i][j] = (i,j)를 오른쪽 아래 꼭짓점으로 갖는 가장 큰 정사각형의 한 변의 길이
* D[i][j-1] == D[i-1][j]인 경우 :
* D[i][j-1] != D[i-1][j] 인 경우 : D[i][j] = 둘 중에 작은거 + 1
* */
public class BOJ1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] d = new int[n+1][m+1];
        int[][] arr = new int[n+1][m+1];

        for (int i = 1; i < n+1; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 1; j < m+1; j++) {
                arr[i][j] = tmp[j-1] - '0';
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (arr[i][j] == 0) continue;
                int x = d[i][j - 1];
                int y = d[i - 1][j];

                if (x == y) {
                    if (arr[i-x][j-y] == 1) d[i][j] = x+1;
                    else d[i][j] = x;
                }
                else d[i][j] = Math.min(x, y) + 1;
            }
        }

        int answer = -1;
        for (int i = 0; i < n+1; i++) {
//            System.out.println(Arrays.toString(d[i]));
            for (int j = 0; j < m+1; j++) {
                answer = d[i][j] > answer ? d[i][j] : answer;
            }
        }
        System.out.println(answer*answer);
    }
}
