package stack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


// [ ] ( ) : 91 93 40 41
/*
* 0. 모든 괄호에 대해서 검사
* 1. 왼쪽 괄호인 경우, base에 2 or 3 add, 해당 괄호 stack에 push()
* 2. 오른쪽 괄호인 경우
* 2-1. 직전의 괄호와 맞아떨어지는 경우, base에 있는 수 다 곱해서 sum에 더함
* 2-2. 안맞는 괄호인 경우, 0 출력, break
* 2-3. 둘 다 아닌 경우, stack에서 pop, base에서 제일 최근 원소 remove();
* */
public class BOJ2504_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> base = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        char prev;

        char[] brackets = br.readLine().toCharArray();
        if (brackets[0] == '[') base.add(3);
        else if (brackets[0] == '(') base.add(2);
        else {
            System.out.println(0);
            return;
        }
        stack.push(prev = brackets[0]);
        int tmp = 1;
        int sum = 0;
        for (int i = 1; i < brackets.length; i++) {
            System.out.println(base);

            if (brackets[i] == '(') {
                base.add(2);
                stack.push(brackets[i]);
            } else if (brackets[i] == '[') {
                base.add(3);
                stack.push(brackets[i]);
            } else if ((brackets[i] == ']' && prev == '[') || (brackets[i] == ')' && prev == '(')){
                for (int x : base) {
                    tmp *= x;
                }

                sum += tmp;
                tmp = 1;
                stack.pop();
                base.remove(base.size() - 1);
            } else if (stack.isEmpty()) {
                System.out.println(0);
                return;
            }
            else if ((brackets[i] == ']' && stack.peek() == '[') || (brackets[i] == ')' && stack.peek() == '(')) {
                stack.pop();
                base.remove(base.size() - 1);
            } else {
                System.out.println(0);
                return;
            }
            prev = brackets[i];
        }

        if (!stack.isEmpty()) System.out.println(0);
        else System.out.println(sum);
    }
}
