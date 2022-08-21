package stack2;

import java.io.*;
import java.util.Stack;

public class BOJ4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();

        a : while (true) {
            stack.clear();
            String tmpStr = br.readLine();
            if (tmpStr.equals(".")) break;

            for (char x : tmpStr.toCharArray()) {
                if (x == '[' || x == '(') stack.push(x);
                else if (x == ']') {
                    if (!stack.isEmpty() && stack.peek() == '[') stack.pop();
                    else {
                        bw.write("no");
                        bw.newLine();
                        continue a;
                    }
                }
                else if (x == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                    else {
                        bw.write("no");
                        bw.newLine();
                        continue a;
                    }
                }
            }
            bw.write(stack.isEmpty() ? "yes" : "no");
            bw.newLine();
        }
        bw.close();
    }
}
