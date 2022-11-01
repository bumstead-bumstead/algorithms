# 처음
  자바의 큐(queue)를 공부했다. 큐는 이름대로 저장된 요소 중 가장 오래된 요소부터 내보내는 자료구조이다. LIFO인 스택과 반대다. (FIFO : First In First Out) 요소를 pop()하는 순서 외에는 시간 복잡도 등 모두 스택과 동일하다. 추가적으로 자바 collections의 queue는 push(), pop() 대신 offer()과 poll()이라는 이름의 메서드를 사용한다.

---
# 중간
### 1. BOJ10845, BOJ18258 - 큐
![](https://velog.velcdn.com/images/bumstead/post/0982c0d4-ffb7-4ab9-b020-95f456e86089/image.png)
  이전 단원과 마찬가지로 간단하게 큐를 구현함으로써 동작 방법을 익히는 문제다.
  ```java
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

```
CustomQueue 클래스 내부에 데이터를 담을 배열 (arr), 큐의 시작과  끝 index를 가리키는 변수 2개 (front, back)을 선언해 이용했다. offer됐을 때 back++, poll됐을 때 front++되는 식이다. 배열의 길이는 문제의 n의 범위만큼 지정해주면 된다. 최대로 offer 될 수 있는 수가 n개이기 때문이다. 

---
### 2. BOJ2164
![](https://velog.velcdn.com/images/bumstead/post/86445dfd-9053-428f-b37a-aaff915bc746/image.png)
  가장 위에 있는 카드 (카드 뭉치에 가장 오래 머문 카드)를 지우거나, 다시 넣거나 하는 등의 행동을 해야하기 때문에, 직관적으로 큐를 이용해야한다는 것을 알 수 있다. 
```java
package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int a = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);

        }
        while (queue.size() > 1) {
            if (a++ % 2 == 0) queue.poll();
            else {
                queue.offer(queue.poll());
            }
        }
        System.out.println(queue.peek());
    }
}

```
큐에 1~n의 순서로 담아주고, "버리기"와 "뽑아서 뒤에 넣기"를 반복하면 된다.
<br>이 문제와 같이 요소를 넣었다 뺐다 순환하며 매번 검증하는 방식에는 큐가 적합하다는 사실을 인지해야한다.

---
# 끝
  스택에 비해서 복잡하게 이 자료구조를 써먹고 응용해야하는 문제는 없었다. 큐의 앞에서 빼고 뒤에서 넣는 특징을 잘 인지해서 필요한 상황(2164번 문제같은 것!!!!)에서 잘 써먹어야될 것이다. 
  
>   연습문제 출처 : [encrypted-def github
](https://github.com/encrypted-def/basic-algo-lecture/blob/master/workbook.md)
