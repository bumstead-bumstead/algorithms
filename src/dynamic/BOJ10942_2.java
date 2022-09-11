package dynamic;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// D[i][j] = i~j까지의 문자의 펠린드롬 여부
// D[i][j] = j번째부터 시작하는 길이가 i인 배열의 펠린드롬 여부 -> 어떰?
// -> D[i-2][j+1] && arr[j] == arr[j+i-1]인지 확인
// 기본 값 : d[0], d[1] 싹다 true;
public class BOJ10942_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[][] d = new boolean[n + 1][n + 1];

        String[] arr = new String[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n+1; i++) {
            arr[i] = st.nextToken();
        }

        Arrays.fill(d[0], true);
        Arrays.fill(d[1], true);

        for (int i = 2; i < n+1; i++) {
            for (int j = 1; i + j <= n + 1; j++) {
//                System.out.println(j + "부터 길이가 " + i + "인 배열 검사");
                d[i][j] = d[i - 2][j + 1] && arr[j].equals(arr[j + i - 1]);
            }
        }

//        for (int i = 0; i < n+1; i++) {
//            System.out.println(i + " : " + Arrays.toString(d[i]));
//        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write((d[b-a+1][a] ? 1 : 0) + "");
            bw.newLine();
        }
        bw.close();
    }
}
