# 처음
   전반적인 자료구조에 대한 복습은 마쳤고, 이제 본격적으로 알고리즘들에 대해서 공부하기 시작했다. 첫 번째가 바로 **BFS(Breadth First Search), 즉 넓이 우선 탐색**이다. 이름에서 알 수 있듯이 그래프나 트리를 탐색하는 하나의 방법이다. 
   ![](https://velog.velcdn.com/images/bumstead/post/441464f6-26e5-4304-899d-fe1a01c7b81d/image.png)
  위와 같은 트리 구조의 데이터가 있다고 생각해보자. 이 트리 안의 모든 노드들에 접근해서 어떤 검사를 해야할 때 여러 가지 방법이 있겠지만, BFS 알고리즘은 노드를 **계층 순서대로 검사한다. ** 
![](https://velog.velcdn.com/images/bumstead/post/94b43128-1c60-40dc-85c2-71908ffb5eea/image.png)

각 계층의 노드들을 순서대로 모두 검사하고 나서야, 다음 계층으로 넘어간다. 왼쪽과 오른쪽 중 어느 방향으로 검사하느냐는 별로 중요하지 않지만, (문제에 따라 바꿀 수 있으니께) 왼쪽에서 시작한다고 생각했을 때 1-2-3-4-5-6-7-8-9-10-11-12-13-14-15 순서대로 노드를 검사하게 될 것이다.
<br> 처음엔 트리/그래프에 대해서 계층 순서대로 탐색한다는 얘기를 들어도 와닿지 않았다. 트리나 그래프로 해석될 수 있는 자료구조가 어떤 것이 있는 지도 몰랐고, 누구나 생각할 수 있을만 한 알고리즘이라는 생각이 들었던 것 같다. 하지만 BFS라는 이름으로 정리되어 있는 이유가 있다는 것을 이를 활용한 문제들을 풀어보면서 느꼈다. 다 생각해볼 수 있을 만한 알고리즘을 BFS라는 이름으로 일반화해놓으면, 적용했을 때 쉽게 해결할 수 있는 문제들이 얼마나 많은 지 연습문제들을 풀면서 알아보자.

---

# 중간
### 1. [백준 1926](https://www.acmicpc.net/problem/1926) - 그림
![](https://velog.velcdn.com/images/bumstead/post/0ebcb191-49f1-488b-aaaa-94e2621f0289/image.png)
BFS의 개념을 알려주고 나서 풀기에는 너무나 직관적인 문제다. 입력되는 2차원 배열의 원소들을 트리의 형태로 표현해보면 더 쉽다. 하나의 그림으로 판별되려면 인접한 곳, 즉 상 하 좌 우에 1이 똑같이 존재해야 한다. 이것을 검사하기 위해, 모든 노드(x, y)에 대해서 (x-1, y), (x+1, y), (x, y+1), (x, y-1) 네 가지를 확인해야 할 것이다. 이렇게 트리가 뻗어나가면서, 주어진 배열의 범위를 넘어가거나 그림이 아닌 경우(0인 경우)를 제외해가면 될 것이다. 
```java
package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }
}

public class BOJ1926 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);

        int[][] picture = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        Queue<Pair> pairsOngoing = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                picture[i][j] = Integer.parseInt(b[j]);
            }
        }
        int pictureCnt = 0; //그림 갯수
        int maxArea = 0; //제일 큰 그림의 넓이

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || picture[i][j] == 0) continue;

                pictureCnt++;

                pairsOngoing.offer(new Pair(i, j));
                visited[i][j] = true;

                int areaCnt = 0;

                while (!pairsOngoing.isEmpty()) {
                    Pair tmp = pairsOngoing.poll();
                    areaCnt++; //여기서?
                    int x = tmp.getX();
                    int y = tmp.getY();
                    if (x > 0 && !visited[x-1][y] && picture[x-1][y] == 1) {
                        pairsOngoing.offer(new Pair(x - 1, y));
                        visited[x-1][y] = true;
                    }
                    if (x < n-1 && !visited[x+1][y] && picture[x+1][y] == 1) {
                        pairsOngoing.offer(new Pair(x + 1, y));
                        visited[x + 1][y] = true;
                    }
                    if (y > 0 && !visited[x][y-1] && picture[x][y-1] == 1) {
                        pairsOngoing.offer(new Pair(x, y-1));
                        visited[x][y-1] = true;
                    }
                    if (y < m-1 && !visited[x][y+1] && picture[x][y+1] == 1) {
                        pairsOngoing.offer(new Pair(x, y+1));
                        visited[x][y+1] = true;
                    }
                }
                if (maxArea < areaCnt) maxArea = areaCnt;
            }
        }
        System.out.println(pictureCnt);
        System.out.println(maxArea);

    }
}

```
BFS의 구현은 **queue를 활용하면 정말 쉽다.** 첫 번째 노드를 offer하고, 그 노드와 연결된 노드들을 검사한 뒤 다시 offer하면 다음 계층의 노드들이 순서대로 차곡차곡 쌓이는 것이다. 위의 그림으로 봤던 순서대로 말이다.
<br> BFS에서 정말 중요한 점은 picture과 같은 크기로 선언된** 2차원 배열 visited다.** 이것은 노드가 queue에 offer될 때마다 (노드를 검사할 때마다) 그 노드의 좌표를 visited로 체크하는 변수다. 만약에 한 번 방문한 노드를 다시 방문한다면, queue에는 무한히 많은 원소들이 쌓일 것이다. **이미 검사한 노드를 다시 검사할 필요는 없다. **

<br> BFS 구현의 방법은 이 예제와 대동소이하고, 이 문제에서 요구하는 것을 구하기 위해 areaCnt와 pictureCnt를 선언해 사용했다. while()문이 끝났다는 것은 더 이상 인접한 1이 없다는 것이기 때문에 그 때마다 pictureCnt(그림의 개수)를 늘려주면 되고, while()문이 반복된 횟수는 검사한 노드의 개수, 즉 1의 개수와 같으므로 반복문이 돌 때마다 areaCnt를 늘려주면 된다.

---
### 2. [백준 2178](https://www.acmicpc.net/problem/2178) - 미로 탐색
![](https://velog.velcdn.com/images/bumstead/post/8bb5d92c-a66d-434f-83c7-acadb28e0ddb/image.png)
이전 문제는 우리가 원하는 조건에 맞는 영역의 범위를 BFS를 활용해서 퍼져나가듯이 검사하는 문제였다. 반면에 이 문제는 보다시피 모든 원소들을 검사하되, 구해야하는 것은 **이동의 최솟값**이다. 우리가 여기서 떠올려야하는 것은, **BFS가 계층의 순서대로 검사를 한다는 것이다.** 그럼 이 문제들에서 '계층'이라는 것은 무엇을 의미할까?
<br> 이전 문제를 예시로 생각해보면, 첫 번째 계층 (H0)은 검사가 시작되는 노드 (queue에 처음 offer되는 노드)인 (0, 0)이다. 그럼 두 번째 계층은 (1, 0), (0, 1)이, 세 번째 계층은 (1,1), (2, 0), (0, 2)가 될 것이다. 그러면 놀랍게도, 이 계층이라는 숫자는 이동 횟수를 나타내게 된다 (정확히는 이동 횟수 - 1). 이 문제에서 BFS를 활용해야하는 이유다. 1. 모든 가능한 노드들을 검사해야 하면서, 2. 검사 경로의 최솟값을 찾는 문제 이기 때문이다.
<br> 실제 구현을 보자.
```java
package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair3 {
    private int x;
    private int y;

    private int reps;

    public Pair3(int x, int y, int reps) {
        this.x = x;
        this.y = y;
        this.reps = reps;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public int getReps() {
        return reps;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class BOJ2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] board = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<Pair3> queue = new LinkedList<>();
        int cnt = 1;

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(b[j]);
            }
        }

        queue.offer(new Pair3(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pair3 tmp = queue.poll();
            if (tmp.getX() == n-1 && tmp.getY() == m-1) {
                System.out.println(tmp.getReps());
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tmp.getX() + dx[i];
                int ny = tmp.getY() + dy[i];

                if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
                if (visited[nx][ny] || board[nx][ny] == 0) continue;

                queue.offer(new Pair3(nx, ny, tmp.getReps()+1));
                visited[nx][ny] = true;
            }
        }
    }
}

```
노드의 정보를 저장하는 Pair3 클래스에 reps라는 멤버를 추가해서, queue에 들어갈 때마다 1씩 늘려주는 방식으로 이것을 구현했다. queue에 offer하는 경우는, tmp 자신의 자식 노드들이기 때문에 자신보다 한 단계 더 낮고, 이것은 한 번 더 이동한 것이라고 해석할 수 있다.
<br> 또 BFS 구현에서 달라진 점은, 위 문제와 똑같이 2차원 배열을 탐색해야했기 때문에 상하좌우를 모두 검사하지만 위 문제처럼 네 경우를 모두 if문으로 검사하지 않는 다는 점이다. dx, dy에 x, y방향으로 움직이는 네 가지 경우를 모두 저장해 놓고, for문을 활용해 코드의 중복을 줄이고 간결하게 작성할 수 있다. 
<br> 추가로, 위 예제처럼 pair 클래스에 reps를 추가해서 저장하지 않고, visited 배열을 int[][]로 선언해서 이동 거리를 저장하도록 할 수도 있다. (공간복잡도 면에서 더 불리할 것 같다.)

---
### 3. [백준 7576](https://www.acmicpc.net/problem/7576) - 토마토
![](https://velog.velcdn.com/images/bumstead/post/ec40a80a-1733-42df-ba38-1056fd355b2e/image.png)

상자의 모든 칸을 검사해야 하고, 토마토가 익는 최소 일수를 구해야한다는 부분에서 바로 BFS를 떠올릴 수 있어야한다. BFS 구현은 쉽고, 최소 일자를 구하기 위해 바로 위에서 이야기한 방법 (visted를 int[][]로 선언해 이동 거리 저장)을 활용해 풀면 될 것 같다.
<br> 다만, 위 문제와 다른 점은 검사를 시작하는 지점이 익은 토마토의 위치 (1)이어야 한다는 것이다. 모든 익은 토마토는 검사에서 0단계여야하므로 queue에 다 집어넣고 시작하면 될 것이다.
```java
package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7576_2 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static int n;
    static int m;
    static int nx;
    static int ny;
    static Queue<Pair> queue;

    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queue = new LinkedList<>();

        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[1]);
        m = Integer.parseInt(a[0]);

        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] b = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(b[j]);
                if (tmp == 1) queue.offer(new Pair(i,j));
                board[i][j] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 1) continue;
                BFS(i, j);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                if (board[i][j] > max) max = board[i][j];
            }
        }
        System.out.println(max-1);
    }

    private static void BFS(int x, int y) {

        visited[x][y] = true;
        queue.offer(new Pair(x, y));

        while (!queue.isEmpty()) {
            Pair tmp = queue.poll();

            for (int k = 0; k < 4; k++) {
                nx = tmp.getX() + dx[k];
                ny = tmp.getY() + dy[k];

                if (nx < 0 || ny < 0 || nx > n -1 || ny > m -1) continue;
                if (visited[nx][ny] || board[nx][ny] != 0) continue;

                board[nx][ny] = board[tmp.getX()][tmp.getY()] + 1;
                queue.offer(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}

```
코드가 길어져서 BFS 구현부는 따로 메서드로 선언해줬다.
<br> 설명한대로 익은 토마토로부터 이동할 때마다 board에 이동 거리 (날짜)를 저장했고 모든 검사가 끝난 뒤에는 board에서 최댓값을 출력하게 했다. 
<br> 또 검사가 끝난 뒤에도 익지 않은 토마토가 있다는 것은 불가능을 의미하므로, -1을 출력하도록 했다.

---
### 4. [백준 4179](https://www.acmicpc.net/problem/4179) - 불!
![](https://velog.velcdn.com/images/bumstead/post/7c9ae556-39bd-4aeb-9b43-16a813a11656/image.png)
지훈이와 불은 사방으로 퍼져나갈 수 있고, 최소 탈출 시간을 찾아야한다. 당연히 BFS다. 다만 지훈이와 불 두 경우에 대해서 검사해야할 것이다.
```java
package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



//시작부터 가장자리에있는 경우 생각 안해서 틀렸었ㅇ씀
public class BOJ4179 {
    static char[][] board;
    static boolean[][] visited;
    static Queue<Pair> fireQueue = new LinkedList<>();
    static Queue<Pair> jihoonQueue = new LinkedList<>();
    static int n;
    static int m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int nx;
    static int ny;
    static int cnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);
        visited = new boolean[n][m];
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            char[] b = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (b[j] == 'J') {
                    if (i == 0 || i == n-1 || j == 0 || j == m-1) {
                        System.out.println(1);
                        return;
                    }

                    jihoonQueue.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
                else if (b[j] == 'F') fireQueue.offer(new Pair(i, j));

                board[i][j] = b[j];
            }
        }

        Pair tmp;
        int threshold;
        while (true) {
            if (jihoonQueue.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            threshold = fireQueue.size();
            for (int i = 0; i < threshold; i++) {
                tmp = fireQueue.poll();
                //불의 움직임...
                for (int j = 0; j < 4; j++) {
                    nx = tmp.getX() + dx[j];
                    ny = tmp.getY() + dy[j];

                    if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) continue;
                    if (board[nx][ny] == 'F' || board[nx][ny] == '#') continue;

                    fireQueue.offer(new Pair(nx, ny));
                    board[nx][ny] = 'F';
                }
            }

            threshold = jihoonQueue.size();
            for (int i = 0; i < threshold; i++) {
                tmp = jihoonQueue.poll();

                //지후니의 움직임... 샤샤샥~ (닌자인듯ㅋㅋ)
                for (int j = 0; j < 4; j++) {
                    nx = tmp.getX() + dx[j];
                    ny = tmp.getY() + dy[j];

                    if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1) {
                        continue;
                    }
                    if (board[nx][ny] == 'F' || board[nx][ny] == '#' || visited[nx][ny]) {
                        continue;
                    }
                    if (nx == 0 || nx == n-1 || ny == 0 || ny == m-1) {
                        System.out.println(++cnt);
                        return;
                    }

                    jihoonQueue.offer(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            cnt++;
            showBoard();
        }
    }

    private static void showBoard(){
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("-------------------");
    }
}

```
위에서 해왔던 것이랑 비슷해서 설명할 것은 별로 없다. 불이 번지는 것이 지훈이의 이동보다 선행되어야한다는 것 정도만 주의하면 될 것 같다. (지훈이가 먼저 이동하면, 불이 번질 위치로 지훈이가 이동하는 경우가 생김) 
<br> 또 이번에는 이동 횟수를 저장하는 방법으로 threshold라는 변수를 이용했다. 각 계층이 다음 계층의 노드들을 모두 queue에 넣고 나면, queue.size()는 다음 계층의 원소 개수가 된다. 이런 방식으로 각 계층을 분리해서 반복문을 돌릴 수 있다. (반복문이 돌때마다 cnt++)
<br> 그리고 시작부터 지훈이가 가장자리에 있는 경우 예외처리를 안해서 한 번 틀렸다. 항상 극단적인 경우의 테스트케이스 예외처리를 꼼꼼히 해주도록 하자.......

---
### 5. [백준 1697](https://www.acmicpc.net/problem/1697) - 숨바꼭질
![](https://velog.velcdn.com/images/bumstead/post/9a58fc1a-f687-4d47-861d-36b6f2d4d7dd/image.png)
역시나 모든 경우의 수를 탐색하며 최소 탐색을 찾는 문제다. 다만 위의 문제들은 2차원 배열에서 사방으로 검사를 했던 반면에, 이번엔 1차원에서 이동 방식이 세 가지인 경우이다. 각 노드가 자식 노드를 세 개 가질 뿐 달라지는 점은 없다.
```java
package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int k = Integer.parseInt(a[1]);
        if (n==k) {
            System.out.println(0);
            return;
        }
        boolean[] visited = new boolean[100001];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        visited[n] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int threshold = queue.size();
            for (int i = 0; i < threshold; i++) {
                System.out.println(queue.size());
                int[] tmp = createArray(queue.poll());
                for (int x : tmp) {
                    if (x > 100000 || x < 0) continue;
                    if (x == k) {
                        System.out.println(++cnt);
                        return;
                    }
                    if (!visited[x]) {
                        queue.offer(x);
                        visited[x] = true;
                    }
                }
            }
            cnt++;
        }
    }
    private static int[] createArray(int a) {
        int[] result = {a - 1, a + 1, a * 2};
        return result;
    }
}

```
최대로 이동할 수 있는 범위가 100000이라는 점 말고는 신경쓸 부분이 없었다.

---
# 끝

BFS 알고리즘의 간단한 개념과 이를 활용한 기본적인 문제들을 풀어봤다. 내가 느끼기에 BFS를 활용해야하는 지를 결정하는 것은, **1. 퍼져나가는 듯한 검사 (영역의 검사 등) 2. 모든 경우의 수 검사 3. 최솟값 계산** 인 것 같다. 다음 두 개의 포스팅에서는 BFS를 활용한 문제들을 더 풀어볼 것이다.

---
> 연습문제 출처 : [encrypted-def github](https://github.com/encrypted-def/basic-algo-lecture/blob/master/workbook/0x09.md)
