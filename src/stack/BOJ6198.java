package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


//sum 변수를 int로 지정해서 overflow. h 범위가 10억이라 범위문제는 아닐것이라고 착각했는데, sum이 최대 80000!이 될 수 있었음...
public class BOJ6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeatations = Integer.parseInt(br.readLine());

        int[] buildings = new int[repeatations];

        for (int i = 0; i < repeatations; i++) {
            buildings[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();

        stack.push(repeatations-1);
        long sum = 0;

        for (int i = buildings.length-2; i >= 0 ; i--) {
            int tmp = 0;
            if (buildings[stack.peek()] >= buildings[i]) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty()) {
                if (buildings[stack.peek()] >= buildings[i]) {
//                    System.out.println(i + "부터 " + stack.peek() + "번 째 건물까지");
//                    System.out.println(stack.peek() - i - 1);
                    sum += stack.peek() - i -1;
                    stack.push(i);
                    break;
                }
                stack.pop();
            }

            if (stack.isEmpty()) {
//                System.out.println(i + "번 째 건물은 끝이 없는놈..");
//                System.out.println(buildings.length - i - 1);
                sum += buildings.length - i - 1;
                stack.push(i);
            }

        }
        System.out.println(sum);
    }
}
