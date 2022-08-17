package stack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BOJ1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int repetations = Integer.parseInt(br.readLine());

        List<String> answer = new ArrayList<>();

        //stack 초기설정
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        answer.add("+");

        int cnt = 1; //스택에 어디까지 썼는지. 제일최근에 넣은 수
        for (int i = 0; i < repetations; i++) {
            int tmp = Integer.parseInt(br.readLine());

            //tmp가 stack에 사용한 수보다 클 경우, 채워줌
            while (cnt < tmp) {
                stack.push(++cnt);
                answer.add("+");

            }

            if (tmp == stack.peek()) {
                answer.add("-");
                stack.pop();
            }
            //불가능
            else {
                bw.write("NO");
                bw.close();
                return;
            }
        }

        for (String x : answer) {
            bw.write(x);
            bw.newLine();
        }
        bw.close();
    }
}
