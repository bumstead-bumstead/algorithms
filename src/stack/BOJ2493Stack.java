package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2493Stack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeatations = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] tops = new int[repeatations];
        String[] strings = br.readLine().split(" ");

        for (int i = 0; i < repeatations; i++) {
            tops[i] = Integer.parseInt(strings[i]);
        }

        //스택에 들어가는 값은 배열 idx가 아니라 위치. (idx+1)
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        sb.append(0).append(" ");

        for (int i = 1; i < repeatations; i++) {
            while (!stack.isEmpty()) {
                if (tops[stack.peek()-1] >= tops[i]) {
                    sb.append(stack.peek()).append(" ");
                    stack.push(i+1);
                    break;
                }
                else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) {
                stack.push(i+1);
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb);

    }
}
ㅐ