# 처음
  '괄호'로서 구분될 수 있는 스택을 활용하는 문제들을 몇 가지 풀었다. 
  <br> 괄호라고 표현한 것은, 괄호와 같이 서로 다른 두 문자가 만났을 때 지워지도록 하는 연산에 대한 문제들이기 때문이다. 그리고 이 연산에는 스택이 정말 적절한 자료구조다. 아래의 첫 번째 문제를 그 예시로 확인해보자.

---
# 중간
### 1. BOJ 4949 - 균형잡힌 세상
![](https://velog.velcdn.com/images/bumstead/post/2efa69ee-af49-4709-9f0b-88c0c762b645/image.png)
문장들에 포함된 괄호가 올바르게 짝지어져있는 지 확인하는 문제다. 아마 스택을 어떻게 활용해야하는 지 감이 올 것이다.
<br> 괄호들을 하나하나 스택에 담아가며, 스택의 가장 위에 있는 왼쪽 괄호 { ( or \[ } 가 검사하는 괄호와 맞아떨어지면, pop()해버리면 되는 것이다. 만약 검사가 끝날 때까지 스택에 원소가 남아있다면, 균형 잡힌 문장이 아닌 것이다. 
```java
package stack2;

import java.io.*;
import java.util.Stack;

public class BOJ4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();

        a : while (true) {
            stack.clear();
            String tmpStr = br.readLine();
            if (tmpStr.equals(".")) break;

            for (char x : tmpStr.toCharArray()) {
                if (x == '[' || x == '(') stack.push(x);
                else if (x == ']') {
                    if (!stack.isEmpty() && stack.peek() == '[') stack.pop();
                    else {
                        bw.write("no");
                        bw.newLine();
                        continue a;
                    }
                }
                else if (x == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                    else {
                        bw.write("no");
                        bw.newLine();
                        continue a;
                    }
                }
            }
            bw.write(stack.isEmpty() ? "yes" : "no");
            bw.newLine();
        }
        bw.close();
    }
}

```
위의 설명한 로직에서 추가된 부분은, 잘못된 괄호쌍이 보이면 끝까지 갈 것 없이 반복문 중간에서 거르게 한 것이다. 예를 들어 (] 나 \[) 형태의 괄호가 연결되어 나오면, 균형잡힌 문장이 될 수 없기 때문이다.

---
### 2. BOJ 3986 - 좋은 단어
![](https://velog.velcdn.com/images/bumstead/post/4ee367d6-b1f0-46ed-9cc3-60e8e674ffd0/image.png)
짝을 지어서 올바른 지 확인하는 괄호 문제의 일종이다. 아치형 곡선을 그어 쌍을 짓는 다는 것이 위 문제의 괄호를 선별하는 과정과 아예 똑같기 때문이다. 이 문제에서는 A라는 괄호와 B라는 괄호가 있는 것이다.
```java
package stack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int reps = Integer.parseInt(br.readLine());
        int cnt = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < reps; i++) {
            String tmp = br.readLine();
            for (char x : tmp.toCharArray()) {
                if (stack.isEmpty()) stack.push(x);
                else if (stack.peek() == x) stack.pop();
                else stack.push(x);
            }
            if (stack.isEmpty())  cnt++;
            stack.clear();
        }
        System.out.println(cnt);
    }
}

```
1번보다 간단하기 때문에 설명은 생략하겠다.

---
### 3. BOJ 107779 - 쇠막대기
![](https://velog.velcdn.com/images/bumstead/post/4e2df09b-fd21-481e-b267-c9267a1a7866/image.png)
스택을 이용해서 푸는 문제라는 사실을 모른 채 이 문제를 접했으면, 쉽게 접근할 수 있었을 지 모르겠다. 괄호 문제를 아주 잘 응용한 문제인 것 같다.
<br> 우선 괄호가 직접 만날 때, (다시 말해 stack.peek()과 그 이전 원소가 괄호쌍을 이룰 때) 레이저가 되어 쇠막대기 조각이 발생한다는 것이다. 그리고 이 쇠막대기 조각은, 레이저가 발사되는 순간에 놓여져 있던 쇠막대기의 수와 같다. 즉, 레이저가 발사될 때마다 stack의 size()만큼 쇠막대기가 생겨나는 것이다. 이 부분만 파악하면 어렵지 않게 해결할 수 있는 문제였다.
```java
package stack2;

import java.util.Scanner;
import java.util.Stack;

public class BOJ10799 {
    //레이저 "()"는 현재 stack 사이즈만큼 ++, 막대기 닫힐때는 1만 ++;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Stack<Character> stack = new Stack<>();
        char prev = '0';
        int cnt = 0;

        for (char x : str.toCharArray()) {
            if (x == ')') {
                if (prev == '(') {
                    stack.pop();
                    cnt += stack.size();
                }
                else {
                    stack.pop();
                    cnt++;
                }
            }
            else stack.push(x);

            prev = x;
        }

        System.out.println(cnt);
    }
}

```
괄호가 짝지어질 때, 그것이 레이저 발사인지 쇠막대기의 끝인지를 구분하기 위해 prev 변수에 직전에 검사한 문자를 저장했다. prev가 '('이고, 지금 검사하는 문자가 ')'여야만 레이저인 것이다. 

---
### 4. BOJ 2504 - 괄호의 값

![](https://velog.velcdn.com/images/bumstead/post/18ca671a-6b4f-4287-8660-2b0bf065ec7d/image.png)
보기에는 괄호 문제를 조금 응용한 정도라고 생각했는데, 막상 구현해보니 세 시간동안 이것 저것 해보다가 결국 실패했다.... [다른 블로그](https://ilmiodiario.tistory.com/27')에서, 코드는 보지 않고 로직을 참고했는데, 문제 해결의 핵심은 **분배 법칙**이었다. 
<br> 배열의 원소가 하나가 될 때 까지 여러번 반복하며 괄호의 배치를 보고 연산자를 따로 저장하는 배열을 만들거나, 정규표현식으로 괄호를 숫자로 대체하는 등 여러 가지 시도를 해봤지만 실패했고, 좌절감에 빠져 구글링을 하다가 분배 법칙이라는 말을 보자마자 눈이 탁 트이면서 아쉬움의 한숨을 내쉬었다...
<br> 접근법은 이렇다. 바로 위의 쇠막대기 문제들처럼, 괄호를 저장하며 진행하다가 직접 괄호쌍이 만들어지는 부분에서 스택에 쌓인 괄호들을 분배법칙처럼 곱해주는 것이다. 여러 번 겹쳐있는 괄호는 곱셈으로 계산하기 때문이다.


0. 모든 괄호에 대해서 검사
1. 왼쪽 괄호인 경우, base에 2 or 3 add, 해당 괄호 stack에 push()
2. 오른쪽 괄호인 경우
2-1. 직전의 괄호와 맞아떨어지는 경우, base에 있는 수 다 곱해서 sum에 더함
2-2. 안맞는 괄호인 경우, 0 출력, break
2-3. 둘 다 아닌 경우, stack에서 pop, base에서 제일 최근 원소 remove();

여기서 base는 쌓인 괄호의 값 (2 or 3)을 순서대로 저장하는 List이다.
```java
package stack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BOJ2504_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> base = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        char prev;

        char[] brackets = br.readLine().toCharArray();
        if (brackets[0] == '[') base.add(3);
        else if (brackets[0] == '(') base.add(2);
        else {
            System.out.println(0);
            return;
        }
        stack.push(prev = brackets[0]);
        int tmp = 1;
        int sum = 0;
        for (int i = 1; i < brackets.length; i++) {
            System.out.println(base);

            if (brackets[i] == '(') {
                base.add(2);
                stack.push(brackets[i]);
            } else if (brackets[i] == '[') {
                base.add(3);
                stack.push(brackets[i]);
            } else if ((brackets[i] == ']' && prev == '[') || (brackets[i] == ')' && prev == '(')){
                for (int x : base) {
                    tmp *= x;
                }

                sum += tmp;
                tmp = 1;
                stack.pop();
                base.remove(base.size() - 1);
            } else if (stack.isEmpty()) {
                System.out.println(0);
                return;
            }
            else if ((brackets[i] == ']' && stack.peek() == '[') || (brackets[i] == ')' && stack.peek() == '(')) {
                stack.pop();
                base.remove(base.size() - 1);
            } else {
                System.out.println(0);
                return;
            }
            prev = brackets[i];
        }

        if (!stack.isEmpty()) System.out.println(0);
        else System.out.println(sum);
    }
}

```


# 끝
마지막 문제에서는 특히나 계속해서 연습하면 이런 아이디어를 떠올리는 연습도 되는 것일까 하는 생각이 들었다. 우선은 자료구조와 그 활용을 이렇게 문제를 통해 계속해서 연습하는 수 밖에는 없겠지.
<br> 오늘의 공부에서 주목할 부분은 **하나씩 추가해가며 짝이 지어졌을 때 없애버리는 연산**에 스택이 적합하다는 것과 세번째, 네번째 문제처럼 그런 연산을 어떻게 또 활용할 수 있는지였던 것 같다.

> 연습문제 출처 : [encrypted-def - github](https://github.com/encrypted-def/basic-algo-lecture/blob/master/workbook/0x08.md)
