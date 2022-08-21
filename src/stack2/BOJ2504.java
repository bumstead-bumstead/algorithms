package stack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/*
* 1. 붙어있는 올바른 괄호 있는지 확인 -> 숫자로 바꿈
* 2. 숫자 끼리 붙어있는지 확인 -> 뽑아서 곱해서 집어넣기
* 3. 괄호가 숫자 하나를 감싸고 있는지 확인 -> 숫자로 바꿔서 곱함
*
* */


//너무어렵싸ㅃ싸리와요
public class BOJ2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] bracketArr = br.readLine().split("");
        Stack<String> stack = new Stack<>();
        List<String> tmpList = new ArrayList<>();
        for (String x : bracketArr) {
            tmpList.add(x);
        }

        String prev;

        while (tmpList.size() > 1) {
            stack.push(tmpList.get(0));
            for (int i = 1; i < tmpList.size(); i++) {

                // 1. 괄호일 때
                if (tmpList.get(i).matches("[\\[\\]\\(\\)]")) {
                    if (tmpList.get(i).equals(")") && stack.peek().equals("(")) {
                        stack.pop();
                        stack.push("2");
                    } else if (tmpList.get(i).equals("]") && stack.peek().equals("[")) {
                        stack.pop();
                        stack.push("3");
                    }
                }
                //2. 숫자일 때
                else {
                    if (!stack.peek().matches("[\\[\\]\\(\\)]")) {
                        stack.push(Integer.parseInt(stack.pop()) + Integer.parseInt(tmpList.get(i)) + "");
                    }
                }
            }
        }



    }
}
