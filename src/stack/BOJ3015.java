package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Pair {
    int height;
    int reps;

    public Pair(int height, int reps) {
        this.height = height;
        this.reps = reps;
    }
}

//https://lotuslee.tistory.com/105
public class BOJ3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeatations = Integer.parseInt(br.readLine());

        int[] people = new int[repeatations];

        for (int i = 0; i < repeatations; i++) {
            people[i] = Integer.parseInt(br.readLine());
        }

        Stack<Pair> stack = new Stack<>();

        stack.push(new Pair(people[0], 1));

        long pairs = 0;

        for (int i = 1; i < people.length; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek().height > people[i]) {
                    stack.push(new Pair(people[i], 1));
                    pairs++;
                    break;
                }
                else if (stack.peek().height == people[i]) {
                    pairs += stack.peek().reps++;
                    if (stack.size() != 1) pairs++;
                    break;
                }
                else {
                    pairs+= stack.pop().reps;
                }
            }

            if (stack.isEmpty()) stack.push(new Pair(people[i], 1));
        }
        System.out.println(pairs);
    }
}
