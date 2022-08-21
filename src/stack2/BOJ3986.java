package stack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int reps = Integer.parseInt(br.readLine());
        int cnt = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < reps; i++) {
            String tmp = br.readLine();
            for (char x : tmp.toCharArray()) {
                if (stack.isEmpty()) stack.push(x);
                else if (stack.peek() == x) stack.pop();
                else stack.push(x);
            }
            if (stack.isEmpty())  cnt++;
            stack.clear();
        }
        System.out.println(cnt);
    }
}
