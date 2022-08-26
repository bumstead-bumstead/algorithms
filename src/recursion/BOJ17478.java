package recursion;

import java.io.*;

public class BOJ17478 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] advice = {"\"재귀함수가 뭔가요?\"", "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어."
    , "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.", "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\""};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());
        bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        bw.newLine();
        doctorSays(0);
        bw.close();
    }

    private static void doctorSays(int n) throws IOException{
        if (n == N) {
            for (int j = 0; j < n; j++) {
                bw.write("----");
            }
            bw.write("\"재귀함수가 뭔가요?\"");
            bw.newLine();
            for (int j = 0; j < n; j++) {
                bw.write("____");
            }
            bw.write("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
            bw.newLine();
            for (int j = 0; j < n; j++) {
                bw.write("____");
            }
            bw.write("라고 답변하였지.");
            return;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                bw.write("____");
            }
            bw.write(advice[i]);
            bw.newLine();
        }

        doctorSays(n+1);

        bw.newLine();
        for (int i = 0; i < n; i++) {
            bw.write("____");
        }
        bw.write("라고 답변하였지.");
    }
}
