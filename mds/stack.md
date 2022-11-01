# 처음
 <br> 자바에서의 자료구조 스택(stack)에 대해서 공부했다. 스택은 "쌓는다"는 이름대로 **데이터를 적재하듯이 저장하고, 쌓인 순서대로 뽑을 수 있는 자료구조이다.**(LIFO) 당연하게도 데이터를 추가하고 삭제하는 push와 pop에도 O(1)의 시간복잡도를 요한다. 개념과 구현이 모두 매우 간단한 자료구조이기 때문에 바로 관련 문제들을 풀어봤다.
  
---
# 중간
### 1. BOJ10828 - 스택
![](https://velog.velcdn.com/images/bumstead/post/bf6a0e6d-5232-4ae4-8178-1a485b69fb8f/image.png)
  스택을 간단하게 구현해보는 문제였다.
  ```java
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
```
 스택의 구현 역시 매우 간단하기 때문에 설명은 필요 없을 것 같다. 스택의 기능과 형태를 다시 한 번 생각하는 데 도움이 됐다.
 ### 2. BOJ10773 - 제로
 ![](https://velog.velcdn.com/images/bumstead/post/d733af78-4717-43b3-8153-4cb1ffc0ace3/image.png)
  **가장 최근에 쓴 수를 지워야한다** 는 말에 주목해야한다. 모든 측면에서 스택이 적합한 자료구조라는 것을 알 수 있을 것이다. 매우 간단한 문제다.
  ```java
  public class BOJ10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int repetations = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < repetations; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp == 0) stack.pop();
            else stack.push(tmp);
        }

        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();

        System.out.println(sum);
    }
}
```

---

 ### 3. BOJ1874 - 스택 수열
 ![](https://velog.velcdn.com/images/bumstead/post/91cb1e8d-4a0f-4028-bcb9-1cfdb51250c6/image.png)
사실 문제가 요구하는 것을 이해하는 게 이 문제에서 가장 어려운 부분이었다. 요약하자면, 1~n 사이의 임의의 수열이 주어졌을 때, 그 수열을 스택의 pop()을 이용해서 만들 수 있는지를 확인하는 것이다. 그리고 그 수열에는 1부터 오름차순으로 숫자를 push()할 수 있다.
```java
public class BOJ1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int repetations = Integer.parseInt(br.readLine());

        List<String> answer = new ArrayList<>();

        //stack 초기설정
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        answer.add("+");

        int cnt = 1; //스택에 어디까지 썼는지. 제일최근에 넣은 수
        for (int i = 0; i < repetations; i++) {
            int tmp = Integer.parseInt(br.readLine());

            //tmp가 stack에 사용한 수보다 클 경우, 채워줌
            while (cnt < tmp) {
                stack.push(++cnt);
                answer.add("+");

            }

            if (tmp == stack.peek()) {
                answer.add("-");
                stack.pop();
            }
            //불가능
            else {
                bw.write("NO");
                bw.close();
                return;
            }
        }

        for (String x : answer) {
            bw.write(x);
            bw.newLine();
        }
        bw.close();
    }
}
```
  수열을 처음부터 모두 검사한다. 그리고 각각의 수(n)에 대해서
  
  1. 가장 최근에 push()한 수와 n을 비교한다.
  1-1. n이 크다면, n이 될 때까지 push()한다.
  1-2. n이 작다면, 불가능하므로 "NO"를 출력한다.
  1-3. n과 같다면, pop()한다.
  
위와 같은 간단한 로직으로 해결할 수 있다. 역시 스택의 활용에 익숙해지기 위한 문제였다.

---

### 4. BOJ2493 - 탑
![](https://velog.velcdn.com/images/bumstead/post/38abdc51-8761-4e41-b191-af929095810c/image.png)
본격적으로 스택을 응용해서 풀어야하는 문제였다. 문제를 풀 때 내가 주목한 점은, 모든 탑들에 대해서 **자신보다 큰 가장 가까운 탑**에만 관심이 있다는 것이다. 즉, 그 사이의 자신보다 작은 탑들은 전혀 신경쓰지 않아도 된다. 신경 쓸 필요 없는 요소들을 걸러내는 데 스택을 활용하면, 시간복잡도를 훨씬 줄일 수 있다.
```java
public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repetations = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] tops = new int[repetations];
        String[] strings = br.readLine().split(" ");

        for (int i = 0; i < repetations; i++) {
            tops[i] = Integer.parseInt(strings[i]);
        }

        int tmpMax = tops[0];
        sb.append(0).append(" ");
        for (int i = 1; i < repetations; i++) {
            if (tmpMax < tops[i]) {
                tmpMax = tops[i];
                sb.append(0).append(" ");
                continue;
            }
            for (int j = i-1; j >= 0; j--) {
                if (tops[i] < tops[j]) {
                    sb.append(j+1).append(" ");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}
```
  stack을 사용하지 않고 모든 경우를 고려한 풀이이다. 모든 탑에 대해서, 자신보다 왼쪽에 있는 모든 탑을 검사해야하기 때문에 시간복잡도는 (n/2)\*(n/2), 즉 O(n^2)이다.

### 스택을 활용한 풀이
```java
public class BOJ2493Stack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeatations = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] tops = new int[repeatations];
        String[] strings = br.readLine().split(" ");

        for (int i = 0; i < repeatations; i++) {
            tops[i] = Integer.parseInt(strings[i]);
        }

        //스택에 들어가는 값은 배열 idx가 아니라 위치. (idx+1)
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        sb.append(0).append(" ");

        for (int i = 1; i < repeatations; i++) {
            while (!stack.isEmpty()) {
                if (tops[stack.peek()-1] >= tops[i]) {
                    sb.append(stack.peek()).append(" ");
                    stack.push(i+1);
                    break;
                }
                else {
                    stack.pop();
                }
            }

            if (stack.isEmpty()) {
                stack.push(i+1);
                sb.append(0).append(" ");
            }
        }
        System.out.println(sb);
    }
}
```
 스택을 활용한 풀이의 해결 방법은, 탑들의 수신에 영향을 미칠 요소만을 스택에 저장하고, 스택에서 수신할 탑을 검색하는 것이다. 위의 풀이처럼 왼편의 모든 탑을 검사할 필요가 없다. 로직은 다음과 같다.
 
 0. 첫 번째 탑의 위치를 스택에 저장한다.
 1. 현재 탑을 스택의 가장 위의 원소와 비교한다.
 1-1. 스택의 원소가 크거나 같다면, 해당 원소의 위치를 출력하고, 현재 탑의 위치를 push한다.
 1-2. 스택의 원소가 작다면, 해당 원소를 pop하고, (지금 검사하는 탑이 그것을 가릴 것이기 때문) 1을 반복한다.
 
이 문제를 풀고, 알고리즘에서 스택은 이런 식으로 써먹을 수 있구나 하는 느낌이 확 들었다. 다만 스택을 이용하는 문제라는 것을 모른 채였다면 접근법을 생각해내기 힘들었을 것 같다.

---

### 5. BOJ6198 - 옥상 정원 꾸미기
![](https://velog.velcdn.com/images/bumstead/post/50b93078-8e16-4578-a60a-de73887c43fb/image.png)
  
 이전 문제와 아주 유사한 문제다. 다른 점은 출력 방식과 검사해야하는 방향 정도 밖에는 없다.
 ```java
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
                    sum += stack.peek() - i -1;
                    stack.push(i);
                    break;
                }
                stack.pop();
            }

            if (stack.isEmpty()) {
                sum += buildings.length - i - 1;
                stack.push(i);
            }

        }
        System.out.println(sum);
    }
}
```
 <br> 처음엔 계속 오답이 나왔는데 원인이 무엇인지 몰랐다. h와 n의 범위가 2^31보다 작기 때문에 overflow는 아닐 것이라고 생각했다. 모든 빌딩이 서로 볼 수 있다면, sum이 80000!이 될 수 있다는 것을 매우 뒤늦게 깨달았다.... 
  <br>탑 문제와 거의 비슷하기 때문에 따로 설명하지 않겠다. 
  
  ---
  ### 6. BOJ3015 - 오아시스 재결합
  ![](https://velog.velcdn.com/images/bumstead/post/1093b4df-1570-41e7-9497-761feb12d271/image.png)
 <br> 위의 두 문제와 매우 유사하지만, 조금 더 복잡하다. 그 차이를 만드는 것은 두 문제와 달리 비교하는 두 대상과의 관계의 문제라는 것과 (이전 두 문제는 검사하는 요소의 입장에서 거리 혹은 위치를 구하는 문제였다.), 크기가 같은 경우도 인정된다는 점이다.
  ```java
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
```

<br>기본적인 접근은 비슷하다. 모든 요소를 검사해가며 자신보다 낮으면 스택에서 지우고, 자신보다 높으면 스택에 자신을 추가하는 방식이다. 하지만 요소의 값이 같을 경우가 문제였는데, 스택이 내림차순으로 정렬되어 있지만 **같은 요소가 스택에 얼마나 많을 지 확인해야하기 때문이다.** 
<br>처음에는 스택에서 하나하나 뺀 뒤에 반복된 개수를 pairs 변수에 더하고나서, 다시 값을 하나하나 넣어주는 방식으로 구현했는데 역시나 시간초과가 떴다.
<br>그래서 생각한 방식은, 키(height)와, 연속된 횟수(reps) 두 개의 값을 갖는 Pair 인스턴스를 이용해 위 과정을 줄이는 것이었다. 반복될 때마다 reps를 늘려주고 reps를 pairs 변수에 더해주기만 하면 되는 것이었다.

그런데 지금 생각해보니 클래스 이름은 적절하지 못한 것 같다.

---
# 끝
자료구조 스택의 개념, 기능에 대해서 복습했고, 알고리즘에서 스택을 활용/응용하는 방법에 대해 많이 생각해봤다. 
<br>풀어본 스택 응용 문제들의 공통점은 **비교를 통해 불필요한 검사를 없애는 것**이다. 또한 이 불필요한 검사는 검사가 일어나는 도중에, 검사하는 요소가 영향을 끼친다. 즉, **검사를 받는 요소와 검사의 기준(우리가 스택으로 만들었던 것)이 서로에게 영향을 준다.** 그리고 이 과정을 스택을 활용하면 직관적이고 쉽게 할 수 있는 것이다.
  <br>위의 세 문제의 로직을 보면 이해하기 쉬울 것이다. 이것을 굳이 이렇게 이해하기 힘든 말로 표현하는 게 도움이 될 진 모르겠지만, 이 문제를 일반화하고 싶었다. 요것을 잘 인지해서 다음에 비슷한 문제가 나왔을 때 스택을 떠올릴 수 있었으면 좋겠다.
  
>   연습문제 출처 : [encrypted-def github](https://github.com/encrypted-def/basic-algo-lecture/blob/master/workbook.md)
