package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int repetations = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < repetations; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp == 0) stack.pop();
            else stack.push(tmp);
        }

        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();

        System.out.println(sum);
    }
}
