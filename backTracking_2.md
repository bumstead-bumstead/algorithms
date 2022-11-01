# 처음
백트래킹의 개념을 공부하고 연습 문제를 몇 개 풀어봤고, 이번에는 대표적인 백트래킹 연습문제인 N과 M 문제를 모두 풀어볼 것이다. N과 M 문제들을 풀면서 백트래킹의 동작 원리와 활용에 익숙해질 것이라고 생각한다.
<br> 각 문제들에서 요구하는 것이 어떻게 달라지는지와, 이에 따라서 함수는 어떤 식으로 구현해야하는 지 비교하는 것에 집중해서 공부했다.

---

# 중간
## N과 M 문제

### [1. 백준 15650 - N과 M (2)](https://www.acmicpc.net/problem/15650)
![](https://velog.velcdn.com/images/bumstead/post/a19d4dab-8d1c-4009-ba31-3cf2982a4dcf/image.png)
N과 M 1번과 달라진 점은, 수열이 오름차순이어야한다는 것이다. 이것은 순열이 아닌 조합을 구하라는 의미로도 볼 수 있다. 이것을 해결하기 위해서는, 검사하는 단계 k에 대해서 k-1단계에서 검사한 수보다 큰 수만을 검사해야한다.

```java
package backtrack;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ15650 {
    static int[] arr;
    static boolean[] visited;
    static int n;
    static int m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());



        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n+1];
        backTrack(0, 0);
        bw.close();

    }

    private static void backTrack(int k, int threshold) throws IOException{
        if (k == m) {
            for (int i = 1; i < n+1; i++) {
                if (visited[i]) bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold+1; i < n+1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTrack(k+1, i);
                visited[i] = false;
            }
        }
    }
}

```

thrshold라는 변수를 함수에 추가했다. 각 단계에서는 바로 이전 단계에서 검사한 수의 다음부터 검사한다. 이것이 바로 조합을 계산하는 방법이다.

---
### [3. 백준 15651 - N과 M (3)](https://www.acmicpc.net/problem/15651)
![](https://velog.velcdn.com/images/bumstead/post/f542a8cd-ae5e-473b-9280-407a5c27c581/image.png)
n과 m 1번과 달라진 점은 중복을 허용한다는 것이다. 중복을 제거하기 위해 만들었던 visited 배열을 통한 검사를 없애면 되겠다.
```java
package backtrack;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15651 {
    static int n;
    static int m;
    static int[] arr;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];


        backTrack(0);
        bw.close();
    }

    private static void backTrack(int k) throws IOException{
        if (k == m) {
            for (int x : arr) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i < n+1; i++) {
            arr[k] = i;
            backTrack(k+1);
        }
    }
}
```

---
### [3. 백준 15652 - N과 M (4)](https://www.acmicpc.net/problem/15652)
![](https://velog.velcdn.com/images/bumstead/post/4771f737-23b3-4cd3-a093-b64036897d00/image.png)
1, 2번이 합쳐졌다. 검사의 범위를 제한하고 중복 검사를 제거하면 되겠다.
```java
package backtrack;


import java.io.*;
import java.util.StringTokenizer;

/*
* threshold 정해서, 중복 허용해서 하깅
* arr 필요없을것같은뎅??
* */
public class BOJ15652 {
    static int[] arr;
    static int n;
    static int m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        backTrack(0, 1);
        bw.close();
    }

    private static void backTrack(int k, int threshold) throws IOException{
        if (k == m) {
            for (int x : arr) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n+1; i++) {
            arr[k] = i;
            backTrack(k+1, i);
        }
    }
}

```

---
### [4. 백준 15654 - N과 M (5)](https://www.acmicpc.net/problem/15654)
---
![](https://velog.velcdn.com/images/bumstead/post/932560b9-ece5-4373-a32a-ea0acd320fcb/image.png)
이번에는 N개의 자연수를 직접 줬다. N개의 자연수도 모두 다르기 때문에 n과 m 첫번째 문제와 같다. 다만 오름차순으로 출력해야되기 때문에 입력을 정렬할 필요가 있겠다.
```java
package backtrack;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//오름차순 정렬해놓고 시작??
public class BOJ15654 {
    static int n;
    static int m;
    static int[] arr; //
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n];
        answer = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        backTrack(0);
        bw.close();
    }

    private static void backTrack(int k) throws IOException{
        if (k == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            answer[k] = arr[i];
            backTrack(k+1);
            visited[i] = false;
        }
    }
}

```

이전 문제들에선 그대로 저장하고 출력했던 i 값을 arr에서 찾아온다는 점만이 다르다.

---

### [5. 백준 15655 - N과 M (6)](https://www.acmicpc.net/problem/15655)
![](https://velog.velcdn.com/images/bumstead/post/049fcc8a-c381-4875-8806-9a126f718fd3/image.png)
이전 문제에서 오름차순 조건만이 추가되었다. 검사 구간을 제한하는 변수를 추가했다.

```java
package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 이전 문제랑 똑같이 하되, 오름차순으로 출력, 중복 안되니께 threshold 정하깅*/
public class BOJ15655 {
    static int n;
    static int m;
    static boolean[] visited;
    static int[] arr;
    static int[] answer;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        arr = new int[n];
        answer = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        backTrack(0, 0);
        bw.close();
    }

    private static void backTrack(int depth, int threshold) throws IOException{
        if (depth == m) {
//            for (int i = 0; i < n; i++) if (visited[i]) bw.write(arr[i] + " ");
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n; i++) {
            answer[depth] = arr[i];
            backTrack(depth+1, i+1);
        }
    }
}

```

---
### [6. 백준 15656 - N과 M (7)](https://www.acmicpc.net/problem/15656)
![](https://velog.velcdn.com/images/bumstead/post/b67407ff-90ab-4ed3-b2d1-3c7bc73ce3ad/image.png)
중복을 허용했다. 2번 문제와 같다.
```java
package backtrack;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15656 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0);
        bw.close();
    }

    private static void backTrack(int depth) throws IOException {
        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; i++) {
            answer[depth] = arr[i];
            backTrack(depth+1);
        }
    }
}

```


---
### [7. 백준 15657 - N과 M (8)](https://www.acmicpc.net/problem/15657)
![](https://velog.velcdn.com/images/bumstead/post/4a02227d-81db-4ab3-afe1-2080c566965c/image.png)
역시 6, 7번이 합쳐졌다. 해법은 4번과 같다.
```java
package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//이전문제랑 똑같지만, 비내림차순이니까 자기보다 작은 숫자는 그뒤에 나오면 안됨. -> threshold
public class BOJ15657 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0, 0);
        bw.close();
    }

    private static void backTrack(int depth, int threshold) throws IOException {
        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n; i++) {
            answer[depth] = arr[i];
            backTrack(depth+1, i);
        }
    }
}

```

---
### [8. 백준 15663 - N과 M (9)](https://www.acmicpc.net/problem/15663)
![](https://velog.velcdn.com/images/bumstead/post/3426ae68-9391-484e-8771-c603d4f3bbc3/image.png)
단순히 M개의 순열을 고르는 문제지만, 입력되는 원소들이 중복되기도 한다는 점이 다르다. 중복되는 수는 하나로 충분하기 때문에 같은 숫자는 검사하지 않았다. 오름차순 정렬하면 같은 수는 인접해있기 때문에 바로 이전 원소를 저장해서 비교하면 된다.
```java
package backtrack;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


//다시풀기... list에 저장해서 매번싹다확인해서 시간초과
public class BOJ15663 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0);
        bw.close();
    }

    private static void backTrack(int depth) throws IOException {

        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }
        int prev = -1;
        for (int i = 0; i < n; i++) {

            if (visited[i] || arr[i] == prev) continue;

            answer[depth] = arr[i];
            visited[i] = true;
            backTrack(depth+1);
            visited[i] = false;
            prev = arr[i];
        }
    }
}
```
이전 원소와 같으면 건너뛰는 검사만을 추가했다.

---
### [9. 백준 15664 - N과 M (10)](https://www.acmicpc.net/problem/15664)
![](https://velog.velcdn.com/images/bumstead/post/b5a911f4-f50b-405a-bd49-a7ca090e5bf6/image.png)

이전 문제들에서 했던 것과 같이 순열이 아닌 조합을 구하는 조건이 추가됐다.
```java
package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


//다시풀기... list에 저장해서 매번싹다확인해서 시간초과
public class BOJ15664 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0, 0);
        bw.close();
    }

    private static void backTrack(int depth, int threshold) throws IOException {

        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }
        int prev = -1;
        for (int i = threshold; i < n; i++) {

            if (visited[i] || arr[i] == prev) continue;

            answer[depth] = arr[i];
            visited[i] = true;
            backTrack(depth+1, i+1);
            visited[i] = false;
            prev = arr[i];
        }
    }
}

```

---
### [10. 백준 15665 - N과 M (11)](https://www.acmicpc.net/problem/15665)
![](https://velog.velcdn.com/images/bumstead/post/13ccc4b7-1725-4221-880e-f59537a0db5e/image.png)
중복이 허용된 문제다. 중복 검사를 삭제했다.
```java
package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


//이전 문제에서 visited없애고, threshold 없앰
public class BOJ15665 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0);
        bw.close();
    }

    private static void backTrack(int depth) throws IOException {

        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }
        int prev = -1;
        for (int i = 0; i < n; i++) {

            if (arr[i] == prev) continue;

            answer[depth] = arr[i];

            backTrack(depth+1);

            prev = arr[i];
        }
    }
}

```

---
### [11. 백준 15666 - N과 M (12)](https://www.acmicpc.net/problem/15666)
![](https://velog.velcdn.com/images/bumstead/post/efa7beac-b355-49bf-a101-3793c20bb3e3/image.png)
이전 문제에서 오름차순 조건이 추가됐다.
```java
package backtrack;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


//이전 문제에서 threshold 추가
public class BOJ15666 {
    static int n;
    static int m;
    static int[] arr;
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        answer = new int[m];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0, 0);
        bw.close();
    }

    private static void backTrack(int depth, int threshold) throws IOException {

        if (depth == m) {
            for (int x : answer) {
                bw.write(x + " ");
            }
            bw.newLine();
            return;
        }
        int prev = -1;
        for (int i = threshold; i < n; i++) {

            if (arr[i] == prev) continue;

            answer[depth] = arr[i];

            backTrack(depth+1, i);

            prev = arr[i];
        }
    }
}

```

---

## 추가(응용) 문제
### [1. 백준 6603 - 로또](https://www.acmicpc.net/problem/6603)
![](https://velog.velcdn.com/images/bumstead/post/7dc34c5f-4266-488e-8e5d-4621f6ac4681/image.png)![](https://velog.velcdn.com/images/bumstead/post/60a16f79-10a9-4972-a70d-8650bf37b6d6/image.png)
단순히 조합을 구하는 문제였다.
```java
package backtrack;

import java.io.*;
import java.util.*;

public class BOJ6603 {
    static int n;
    static boolean[] visited;
    static int[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (st.countTokens() == 1) break;
            n = Integer.parseInt(st.nextToken());
            arr = new int[n];
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            backTrack(0, 0);
            bw.newLine();
        }
        bw.close();
    }

    private static void backTrack(int depth, int threshold) throws IOException{
        if (depth == 6) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) bw.write(arr[i] + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = threshold; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            backTrack(depth+1, i+1);
            visited[i] = false;
        }
    }

}

```
위에서 푼 방법대로 검사 범위를 단계별로 제한하는 방법으로 조합을 구했다.

---
### [2. 백준 1941 - 소문난 칠공주](https://www.acmicpc.net/problem/1941)
![](https://velog.velcdn.com/images/bumstead/post/c5ca15ce-8d58-4aa8-bbe7-bf2ea0d3b53f/image.png)
재귀 부분을 잘못 작성해서 애를 먹었다. 코드를 꼼꼼히 봐야겠다...... 내가 문제를 해결한 로직은 다음과 같다.

1. 25개의 자리 중에 7개를 뽑는 조합을 계산한다.
2. 모든 조합에 대해서 이다솜파 학생의 수가 4명 이상인지 확인한다.
2-1. 그렇다면, bfs로 7개의 자리가 이어져있는지 확인한다. -> cnt++
3. cnt를 반환한다.

```java
package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;


/*
* 모든 노드중에 7개뽑는 조합 다확인 --> 연결 됐는지, 4개이상인지
* 좌표를 1차원으로 표현한 뒤, 검사할때 coordination으로 바꾸기.. 기억!!
* */
class Coordination{
    int x;
    int y;

    public Coordination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{ x = " + x + ", y = " + y + "}";
    }
}

public class BOJ1941 {
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Coordination[] arr = new Coordination[7];

    static int cnt = 0;
    static int cc = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];
        visited = new boolean[5][5];

        for (int i = 0; i < 5; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                board[i][j] = tmp[j];
            }
        }

        princess(0, 0, 0);
        System.out.println(cnt);
    }

    private static void princess(int depth, int tmpC, int cntS) {
        if (depth == 7) {
            if (cntS >= 4) bfs();
            return;
        }

        for (int i = tmpC; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;

            if(visited[x][y]) continue;

            if (board[x][y] == 'S') {
                arr[depth] = new Coordination(x, y);
                visited[x][y] = true;
                princess(depth + 1, i + 1, cntS + 1);
            }
            else {
                arr[depth] = new Coordination(x, y);
                visited[x][y] = true;
                princess(depth + 1, i + 1, cntS);
            }
            visited[x][y] = false;
        }
    }

    private static void bfs() {
        Queue<Coordination> queue = new LinkedList<>();
        boolean[][] BFSvisited = new boolean[5][5];
        int connectedN = 0;

        queue.offer(arr[0]);
        BFSvisited[queue.peek().x][queue.peek().y] = true;

        while (!queue.isEmpty()) {
            Coordination tmp = queue.poll();
            for (int j = 0; j < 4; j++) {
                int tmpx = tmp.x + dx[j];
                int tmpy = tmp.y + dy[j];

                if (tmpx < 0 || tmpy < 0 || tmpx > 4 || tmpy > 4 || BFSvisited[tmpx][tmpy]) continue;
                if (!visited[tmpx][tmpy]) continue;

                queue.offer(new Coordination(tmpx, tmpy));
                BFSvisited[tmpx][tmpy] = true;
                connectedN++;
            }
        }

        if (connectedN == 6) {
            cnt++;
        }
    }

}

```
순열이 아닌 조합을 구해야했기 때문에, 검사 범위를 제한했다. 2차원 배열을 검색할 때는 이 제한이 까다롭기 떄문에 0~24, 1차원으로 검사범위를 정해놓고 그 안에서 2차원으로 변환했다. 백준의 정답 코드를 참고했다.

---
### [3. 백준 16987 - 계란으로 계란 치기](https://www.acmicpc.net/problem/16987)
![](https://velog.velcdn.com/images/bumstead/post/9e731aa2-e5a2-422e-b7dd-a70c02181347/image.png)백트래킹으로 모든 경우의 수를 확인한 뒤 깨진 계란 수의 최댓값을 출력하면 되는 문제였다. 문제에서 제시한 내구도와 무게에 대한 조건들과, 자신 이전의 계란들도 부술 수 있다는 점 정도를 주의해야했다.
```java
package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.StringTokenizer;

class Egg {
    int weight;
    int durability;

    public Egg(int durability,int weight) {
        this.weight = weight;
        this.durability = durability;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "weight=" + weight +
                ", durability=" + durability +
                '}';
    }
}
public class BOJ16987 {
    static int n;
    static Egg[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Egg[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(breakEgg(0));

    }

    private static int breakEgg(int depth) {
        if (depth == n) {
            int cnt = 0;
            for (Egg egg : arr) {
                if (egg.durability <= 0) cnt++;
            }
            return cnt;
        }

        int answer = -1;
        int tmpBroken;
        boolean beated = false;

        if (arr[depth].durability <= 0) { //
            return breakEgg(depth + 1);
        }

        for (int i = 0; i < n; i++) {
            if (arr[i].durability <= 0 || depth == i) continue; // visited[i] ||
                beated = true;
                arr[i].durability -= arr[depth].weight;
                arr[depth].durability -= arr[i].weight;

                tmpBroken = breakEgg(depth+1);

                arr[i].durability += arr[depth].weight;
                arr[depth].durability += arr[i].weight;

            if (tmpBroken > answer) answer = tmpBroken;
        }

        if (!beated) return breakEgg(depth + 1);
        return answer;
    }
}

```
백트래킹 문제로서 주의해야했던 점들은, 자신 이전의 계란도 깰 수 있도록, 즉 조합이 아닌 순열의 문제로 해결했어야 했다는 것 정도가 있다. 

---


# 끝
백트래킹을 알고 있다면 너무 당연하게 풀 수 있는 문제들이 대부분이었지만, 매 문제마다 알고리즘의 진행을 계속해서 상기시키며 체화하는 데 많은 도움이 됐다. 순열/조합 등과 적은 입력 범위에서 백트래킹이 쓰일 수 있다는 것을 명심해야겠다.
<br>응용 문제를 풀고 나서는 결국 중요한 것은 백트래킹을 비롯한 스킬들과 유연한 해법에 대한 아이디어인 것 같다. 이것은 계속해서 알고리즘을 공부하고 문제들을 접하면 키울 수 있겠지.

> 연습문제 출처 : [encrypted-def github](https://github.com/encrypted-def/basic-algo-lecture/blob/master/workbook/0x0C.md)
