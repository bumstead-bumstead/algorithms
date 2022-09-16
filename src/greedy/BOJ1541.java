package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* 뒤에서부터 빼기 계산해버리깅
*
* */
public class BOJ1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char chr : str.toCharArray()) {
            if (chr == '+' || chr == '-') {
                list.add(sb.toString());
                list.add(chr + "");
                sb = new StringBuilder();
                continue;
            }

            sb.append(chr);
        }
        list.add(sb.toString());
        Stack<Integer> stack = new Stack<>();

        int prev = Integer.parseInt(list.get(list.size() - 1));

        for (int i = list.size() - 2; i >= 0; i -= 2) {
            if (list.get(i).equals("+")) {
                prev += Integer.parseInt(list.get(i - 1));
            } else {
                stack.push(prev);
                prev = Integer.parseInt(list.get(i - 1));
            }
        }

        stack.push(prev);
//        System.out.println(stack);
        int answer = stack.pop();
        while (!stack.isEmpty()) {
            answer -= stack.pop();
        }
        System.out.println(answer);
    }


}
