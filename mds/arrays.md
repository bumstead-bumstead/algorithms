## 1. 처음

  알고리즘 구현을 위한 배열을 공부했다. 자바에서의 배열(array)은 원소 추가/삭제가 안되는 등 다른 언어에 비해 strict한 특성을 갖고 있다.

---

## 2. 중간

  이번 포스팅의 결론부터 말하자면, 내가 생각한 알고리즘에서 배열을 효과적으로 활용하는 방법 중 하나는 바로 **“연속된 정수로 표현될 수 있는 데이터들을 검사하는 문제”**의 시간복잡도를 줄이는 것이다. 내가 느낀 것을 paraphrase한 것이라 이 표현이 충분히 직관적일 지는 모르겠다. 관련된 연습문제를 확인하자. 

### 1. BOJ10808 - 알파벳 개수

![](https://velog.velcdn.com/images/bumstead/post/1de4ca15-b842-4bdb-a161-ccbf7780d96b/image.png)

  언뜻 봤을 때 가장 먼저 떠올릴 만한 직관적인 방법은, 입력된 문자열을 알파벳 별로 스물 여섯번 검사하며 배열을 채워나가는 것이다. 이렇게 했을 때는 O(n)의 시간복잡도를 갖게 된다.

  위와 같은 문제에서 배열의 활용으로 시간복잡도를 O(1)로 줄일 수 있다. 

```java
import java.io.*;

public class BOJ10808 {
    public static void main(String[] args) throws IOException {
        //알파벳 아스키 97 to 122
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();
        int[] answer = new int[26];

        for (char x : chars) {
            answer[x-97]++;
        }

        for (int x : answer) {
            bw.write(x + " ");
        }
        bw.close();
    }
}
```

  character를 아스키코드로 치환해 ‘a’의 아스키코드인 97을 빼면, 알파벳 순서대로 인덱스를 얻을 수 있다. 알파벳이 위에서 말한, **“연속된 정수로 표현될 수 있는 데이터”**인 것이다. 이렇게 input 문자열을 한 바퀴 순회함으로써 문제를 해결할 수 있다.

---

### 2. 연습문제 (baaaaaaaarkingDog 기술 블로그)

![](https://velog.velcdn.com/images/bumstead/post/224972d5-f796-4a03-9e7f-8a9342e562af/image.png)


  이 문제 역시 가장 떠올리기 쉬운 해법은 이중 for문을 통해 모든 두 원소의 조합을 검사하는 것이다. O(n^2)을 갖게 된다.

  하지만 여기에서 우리의 target이, 즉 검사하려하는 데이터들이 연속된 정수(0~100의 범위로 제한되어있다)로 표현될 수 있음에 주목하자. 이 사실을 알고 나면, 1번 문제와 같이 이 연속된 정수를 인덱스로 갖는 배열을 만들어 시간복잡도를 낮출 수 있다.

```java
public class Prt0x01{
    public int func2(int[] arr, int N) {
        int[] idxArr = new int[101];

        for (int x : arr) {
            idxArr[x]++;
        }
        for (int x : arr) {
            if (idxArr[100-x] == 1) return 1;
        }
        return 0;

    }
}
```

---

### 3. BOJ2577

![](https://velog.velcdn.com/images/bumstead/post/c66a21df-4c54-4b74-963b-c613284a7968/image.png)


  위의 두 문제를 풀고 나서 보면 해법이 훨씬 명확할 것이다. 이 문제에서 우리가 **검사하길 원하는 연속된 정수**는 각 자릿수에 들어가는 0~9이다.

```java
import java.io.*;

public class BOJ2577 {
    public static void main(String[] args) throws IOException {
        int[] answer = new int[10];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        char[] chars = Integer.toString(a * b * c).toCharArray();

        for (char x : chars) {
            answer[x - '0']++;
        }
        for (int x : answer) {
            bw.write(x + " ");
        }

        bw.close();
    }
}
```

---

### 4. BOJ1475

![](https://velog.velcdn.com/images/bumstead/post/5a730e85-e1cf-483f-9aef-254dbefdea5b/image.png)


```java
import java.io.*;
import java.util.Arrays;

public class BOJ1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String number = br.readLine();
        int[] answer = new int[10];
        for (char x : number.toCharArray()) {
            if (x == '6' || x == '9') {
                answer[9]++;
            }
            else answer[x-'0']++;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            if (answer[i] > max) max = answer[i];
        }

        int sixnine = answer[9]%2==0? answer[9]/2 : (answer[9]+1)/2;
        bw.write(max > sixnine ? max+"" : sixnine+"");

        bw.close();
    }
}
```

  

---

## 3. 끝

  특정한 형태의 데이터를 검사하는 알고리즘의 시간 복잡도를 줄이기 위해 배열을 활용하는 한 가지 방법을 공부했다. 두루뭉실하게 느끼기만 했던 다양한 문제들의 공통된 특징, 특성들을 정리해서 더 확실하게 내 것으로 만드는 공부였던 것 같다. 물론 엄청 간단한 문제였다. 이것을 시작으로 천천히, 그리고 꾸준히 정복해나가야겠다. 정진!!!!!!!!!!
  
  
연습문제 출처
[링크텍스트](https://blog.encrypted.gg/927?category=773649)
