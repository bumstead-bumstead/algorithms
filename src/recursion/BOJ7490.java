package recursion;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ7490 {

    static List<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            System.out.println();
            int N = Integer.parseInt(br.readLine());
            makeExpression("", 0, N);
            Collections.sort(answer);
            answer.forEach(System.out::println);
            answer.clear();
        }

        bw.close();
    }

    private static void makeExpression(String result, int depth, int N) {
        result += (depth + 1);
        if (depth == N - 1) {
            if (isZero(result)) answer.add(result);
            return;
        }
        makeExpression(result + "+", depth+1, N);
        makeExpression(result + "-", depth+1, N);
        makeExpression(result + " ", depth+1, N);

    }

    private static boolean isZero(String expression) {
        List<Character> operators = new ArrayList<>();
//        List<Integer> operands = new ArrayList<>();
        expression = expression.replace(" ", "");
        String[] operands = expression.split("[+-]");

        int result = Integer.parseInt(operands[0]);
        int operandPtr = 1;
        for (int i = 0; i < expression.length(); i++) {
            char a = expression.charAt(i);
            if (a == '+') {
                result += Integer.parseInt(operands[operandPtr++]);
            } else if (a == '-') {
                result -= Integer.parseInt(operands[operandPtr++]);
            }
        }

        return result == 0;
    }
}
