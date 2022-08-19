package queue;

import java.io.*;

class CustomQueue {
    int[] arr;
    int front; int back;
    public CustomQueue(){
        arr = new int[10000];
        front = 0;
        back = 0;
    }

    void push(int X) {
        arr[back++] = X;
    }

    int pop() {
        if (empty() == 1) return -1;
        return arr[front++];
    }

    int empty() {return front == back ? 1 : 0;}

    int front() {
        if (empty() == 1) return -1;
        return arr[front];
    }

    int back() {
        if (empty() == 1) return -1;
        return arr[back-1];
    }

    int size() { return back - front;}
}

public class BOJ10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int reps = Integer.parseInt(br.readLine());

        CustomQueue queue = new CustomQueue();
        for (int i = 0; i < reps; i++) {
            String[] command = br.readLine().split(" ");

            //switch문은 hashcode를 활용하기 때문에 같은 문자열이면 됨
            switch (command[0]) {
                case "push" : queue.push(Integer.parseInt(command[1])); break;
                case "pop" : bw.write(String.valueOf(queue.pop()));bw.newLine();break;
                case "front" : bw.write(String.valueOf(queue.front()));bw.newLine();break;
                case "back" : bw.write(String.valueOf(queue.back()));bw.newLine();break;
                case "empty" : bw.write(String.valueOf(queue.empty()));bw.newLine();break;
                case "size" : bw.write(String.valueOf(queue.size()));bw.newLine();break;
            }
        }
        bw.close();
    }

}
