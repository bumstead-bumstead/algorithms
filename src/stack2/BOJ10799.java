package stack2;

import java.util.Scanner;
import java.util.Stack;

public class BOJ10799 {
    //레이저 "()"는 현재 stack 사이즈만큼 ++, 막대기 닫힐때는 1만 ++;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Stack<Character> stack = new Stack<>();
        char prev = '0';
        int cnt = 0;

        for (char x : str.toCharArray()) {
            if (x == ')') {
                if (prev == '(') {
                    stack.pop();
                    cnt += stack.size();
                }
                else {
                    stack.pop();
                    cnt++;
                }
            }
            else stack.push(x);

            prev = x;
        }

        System.out.println(cnt);
    }
}
