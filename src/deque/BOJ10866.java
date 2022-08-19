package deque;


import java.io.*;
import java.util.Arrays;

class CustomDeque {
    int[] arr;
    int front; int back;
    public CustomDeque(){
        arr = new int[20000];
        front = 10000;
        back = 10000;
    }

    void push_back(int X) {
        arr[back++] = X;
    }
    void push_front(int X) {
        arr[--front] = X;
    }

    int pop_front() {
        if (empty() == 1) return -1;
        return arr[front++];
    }

    int pop_back() {
        if (empty() == 1) return -1;
        return arr[--back];
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

public class BOJ10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int reps = Integer.parseInt(br.readLine());

        CustomDeque deque = new CustomDeque();

        for (int i = 0; i < reps; i++) {
            String[] command = br.readLine().split(" ");

            //switch문은 hashcode를 활용하기 때문에 같은 문자열이면 됨
            switch (command[0]) {
                case "push_front" : deque.push_front(Integer.parseInt(command[1])); break;
                case "push_back" : deque.push_back(Integer.parseInt(command[1])); break;
                case "pop_front" : bw.write(String.valueOf(deque.pop_front()));bw.newLine();break;
                case "pop_back" : bw.write(String.valueOf(deque.pop_back()));bw.newLine();break;
                case "front" : bw.write(String.valueOf(deque.front()));bw.newLine();break;
                case "back" : bw.write(String.valueOf(deque.back()));bw.newLine();break;
                case "empty" : bw.write(String.valueOf(deque.empty()));bw.newLine();break;
                case "size" : bw.write(String.valueOf(deque.size()));bw.newLine();break;
            }
            System.out.println(Arrays.toString(Arrays.copyOfRange(deque.arr, deque.front, deque.back)));

        }
        bw.close();

    }

}
