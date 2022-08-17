package stack;

import java.io.*;

class CustomStack {
    private int[] nums;
    private int size;
    private int cursor;

    public CustomStack() {
        this.size = 10000;
        nums = new int[10000];
        cursor = 0;
    }

    void push(int X) {
        nums[cursor++] = X;
    }

    int pop() {
        if (cursor != 0) {
            return nums[--cursor];
        }
        return -1;
    }

    int size() {return cursor;}

    int empty() {
        return cursor==0 ? 1 : 0;
    }

    int top() {return cursor==0 ? -1 : nums[cursor-1];}
}

public class BOJ10828 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int repetations = Integer.parseInt(br.readLine());

        CustomStack customStack = new CustomStack();

        for (int i = 0; i < repetations; i++) {
            String[] command = br.readLine().split(" ");
            if (command[0].equals("top")) bw.write(String.valueOf(customStack.top()));
            else if (command[0].equals("size")) bw.write(String.valueOf(customStack.size()));
            else if (command[0].equals("empty")) bw.write(String.valueOf(customStack.empty()));
            else if (command[0].equals("pop")) bw.write(String.valueOf(customStack.pop()));
            else {
                customStack.push(Integer.parseInt(command[1]));
                continue;
            }
            bw.newLine();
        }
        bw.close();
    }

}
