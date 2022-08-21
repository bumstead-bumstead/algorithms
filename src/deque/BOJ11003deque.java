package deque;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

class AWithIndex {
    private int value;
    private int index;

    public AWithIndex(int value, int index) {
        this.index = index;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AWithIndex{" +
                "value=" + value +
                ", index=" + index +
                '}';
    }

    public int getIndex() {
        return index;
    }
}

/* 모든 A에 대해서 검사
 * 1. peekLast() 해서 A와 비교
 * 1-1. A가 더 작거나 같다면, pollLast(), 1 반복
 * 1-2. 그렇지 않다면, offer();
 *  --> 이 과정을 통해서 deque에는 최솟값으로 쓰일 A들만 남게 된다.
 * 2. peek()해서 인덱스가 i-L+1 보다 작은지 확인
 * 2-1. 그렇다면 poll()
 * 3. Stringbuilder에 pee()해서 집어넣깅
 * https://loosie.tistory.com/324
 * */
public class BOJ11003deque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] a = br.readLine().split(" ");
        int N = Integer.parseInt(a[0]);
        int L = Integer.parseInt(a[1]);
        String[] b = br.readLine().split(" ");
        int[] arrA = new int[N];
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(b[i]);
        }

        LinkedList<AWithIndex> deque = new LinkedList<>();
        deque.offer(new AWithIndex(arrA[0], 0));
        sb.append(arrA[0]).append(" ");

        for (int i = 1; i < arrA.length; i++) {

            if (deque.peekLast().getValue() > arrA[i]) {
                while (true) {
                    if (deque.isEmpty()) break;

                    if (deque.peekLast().getValue() > arrA[i]) deque.pollLast();
                    else break;
                }
            }

            deque.offer(new AWithIndex(arrA[i], i));

            if (deque.peek().getIndex() < i-L+1) deque.poll();

            sb.append(deque.peek().getValue()).append(" ");
        }

        System.out.println(sb);
    }

}
