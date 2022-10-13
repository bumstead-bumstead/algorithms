# 처음
지난 포스팅에 이어서 구현 문제들을 추가적으로 더 풀었다.

# 중간
### [1. 백준 11559 - Puyo Puyo](https://www.acmicpc.net/problem/11559)
![](https://velog.velcdn.com/images/bumstead/post/c17f23d6-591b-4ba5-a837-989c6b01999e/image.png) 캐주얼 게임 뿌요뿌요를 구현하는 문제였다. 이전 포스팅의 문제처럼 많은 경우를 확인할 필요도 없는 간단한 문제다.
<br> **1. 네 개 이상 인접한 뿌요를 없애기 (BFS), 2. 비어있는 공간을 위의 뿌요들로 채우기** - 더이상 1에서 없어지는 뿌요가 없을 때까지 이 두 가지의 과정을 계속해서 반복하면 된다. 그리고 이 과정이 반복된 횟수가 바로 연쇄의 수가 될 것이다.

```java
package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11559 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] board = new char[12][6];
    static int chainCnt = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            char[] a = br.readLine().toCharArray();
            for (int j = 0; j < 6; j++) {
                board[i][j] = a[j];
            }
        }
        boolean chain = true;
        while (chain) {
            visited = new boolean[12][6];

            chain = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] != '.' || visited[i][j]) {
                        if (puyoBFS(i, j)) chain = true;
                    }
                }
            }
            System.out.println("-------pop-------");
            showBoard();
            System.out.println("-----pulldown-----");
            pulldown();
            showBoard();

            if (chain) chainCnt++;
        }

        System.out.println(chainCnt);
    }

    private static boolean puyoBFS(int x, int y) {
//        System.out.println(x + ", " + y + "---------------------------");
        Queue<Coordinate> queue = new LinkedList<>();
        List<Coordinate> puyos = new ArrayList<>();
        char target = board[x][y];
        int cnt = 0;
        queue.offer(new Coordinate(x, y));
        puyos.add(new Coordinate(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
//            System.out.println("돈다돌아!!!");
            Coordinate tmp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx < 0 || ny < 0 || nx > 11 || ny > 5 || visited[nx][ny]) continue;

                if (board[nx][ny] == target) {
                    visited[nx][ny] = true;
                    queue.offer(new Coordinate(nx, ny));
                    cnt++;
                    puyos.add(new Coordinate(nx, ny));
                }
            }
        }
        if (cnt >= 3) {
            for (Coordinate puyo : puyos) {
                board[puyo.x][puyo.y] = '.';
            }
            return true;
        }
        return false;
    }

    private static void pulldown() {
        for (int j = 0; j < 6; j++) {
            int prevPuyo = -1;
            for (int i = 11; i >= 0; i--) {
                if (board[i][j] != '.') {
                    if (prevPuyo == -1) {
                        char tmp = board[i][j];
                        board[i][j] = '.';
                        board[11][j] = tmp;
                        prevPuyo = 11;
                    } else {
                        char tmp = board[i][j];
                        board[i][j] = '.';
                        board[--prevPuyo][j] = tmp;
                    }
                }
            }
        }
    }

    private static void showBoard() {
        for (int i = 0; i < 12; i++) {
            System.out.println(Arrays.toString(board[i]));

        }
    }
}

```
현재 board를 모두 순회하며 모든 idx에 대해서 BFS로 인접한 뿌요가 4개 이상인지 확인해서 터뜨린다. 터진 경우가 있기만 하면 연쇄가 일어난 것이므로, Cnt를 더해준다. 모든 순회가 끝나고 나면, pulldown() 함수가 비어있는 칸을 위의 뿌요로 채워준다. 간단한 BFS를 활용하면 되는 문제기 때문에 함수들의 로직 설명은 생략하겠다.

---
### [2. 백준 14891 - 톱니바퀴](https://www.acmicpc.net/problem/14891)
![](https://velog.velcdn.com/images/bumstead/post/01abcea3-48be-4171-80be-6a9cf8cb9b9c/image.png)![](https://velog.velcdn.com/images/bumstead/post/ec0fbfe0-d064-41a7-b457-f3f15708773a/image.png) 문제를 잘못 이해해서 한 시간 동안 삽질만 했던 문제였다. 톱니바퀴가 **돌고 나서의 자석의 극에 따라 인접한 톱니바퀴가 이동하는 것**이라고 이해했는데, 그냥 **도는 당시의 극이 다를 경우 딸려가는 것**이었다. 
<br> 또, 한 번의 시행에서 이미 회전한 톱니바퀴는 다시 돌지 않는다는 것도 나중에서야 알았다... 저번 포스팅에서 말했던, 구현 문제는 문제 이해가 가장!!! 중요하다는 것을 다시 깨닫게 해준 문제였다.
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class GearSecond {
    int idx;
    int dir;

    public GearSecond(int idx, int dir) {
        this.idx = idx;
        this.dir = dir;
    }
}

public class Main {
    static LinkedList<Integer>[] gears = new LinkedList[5];
    static int[] dx = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i < 5; i++) {
            char[] tmp = br.readLine().toCharArray();
            gears[i] = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                gears[i].offer(tmp[j] - '0');
            }
        }

        int n = Integer.parseInt(br.readLine());
        Queue<GearSecond> willBeRotate = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            willBeRotate.offer(new GearSecond(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            boolean[] visited = new boolean[4];
            visited[willBeRotate.peek().idx-1] = true;
            while (!willBeRotate.isEmpty()) {

                GearSecond tmpGear = willBeRotate.poll();
                for (int j = 0; j < 2; j++) {
                    int tmpIdx = tmpGear.idx + dx[j];

                    if (tmpIdx < 1 || tmpIdx > 4 || visited[tmpIdx-1]) continue;

                    if (dx[j] == 1) {
                        if (gears[tmpGear.idx].get(2) != gears[tmpIdx].get(6)) {
                            willBeRotate.offer(new GearSecond(tmpIdx, (-1)* tmpGear.dir));
                            visited[tmpIdx - 1] = true;
                        }
                    } else {
                        if (gears[tmpGear.idx].get(6) != gears[tmpIdx].get(2)) {
                            willBeRotate.offer(new GearSecond(tmpIdx, (-1)* tmpGear.dir));
                            visited[tmpIdx-1] = true;
                        }
                    }
                }
                rotate(gears[tmpGear.idx], tmpGear.dir);
            }
        }
        int answer = 0;
        answer += gears[1].peek() == 0 ? 0 : 1;
        answer += gears[2].peek() == 0 ? 0 : 2;
        answer += gears[3].peek() == 0 ? 0 : 4;
        answer += gears[4].peek() == 0 ? 0 : 8;

        System.out.println(answer);

    }

    private static void rotate(LinkedList<Integer> gear, int dir) {
        if (dir == -1) { //반시계 방향이면, 앞에가 맨뒤로
            gear.offer(gear.poll());
        } else { //시계방향이면, 맨뒤가 맨앞으로
            gear.offerFirst(gear.pollLast());
        }
    }
}

```
LinkedList[]인 gears는 각 톱니바퀴의 현재 상태를 저장하는 배열이다. deque로 톱니를 돌리는 행위를 표현했다. 그리고 gearSecond 클래스는 어떤 톱니바퀴가 어떤 방향으로 회전할 지를 저장하는 객체다. 
<br> 가장 먼저 회전할 톱니를 willbeRotate라는 queue에 저장하고, 인접한 톱니들의 회전 여부에 따라 queue에 offer한다. 
<br> 입력받은 회전을 모두 반영한 뒤에, gears의 모든 요소에 가장 앞에 있는 극 (12시 방향) 에 따라 점수를 더해 출력한다.

---
### [3. 백준 14499 - 주사위 굴리기](https://www.acmicpc.net/problem/14499)
![](https://velog.velcdn.com/images/bumstead/post/c0545a8e-2187-420b-a82b-8e039b1bd0db/image.png) 주사위의 현재 상태를 어떻게 저장할 것인지가 관건인 문제였다.
<br> 나는 우선 가장 아래에 있는 수를 저장하는 변수를 따로 만들고, 3X3 배열을 만들어 가장 위와 옆면에 있는 숫자를 저장했다. 그래서 주사위를 굴릴 때마다, 해당 방향의 수를 주사위 밑면 변수에 저장하고, 굴러간 반대 방향에 기존의 밑에 있었던 숫자를 집어넣어 밀어넣는 식으로 구현했다. 말로 이해하기는 어려우니 코드를 확인해보자.
```java
package simulation;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 가는 곳이 0이면 전염시키고, 0이 아니면 주사위가 전염되고 칸은 0됨
*  3*3 배열과, 정수 변수 하나로 주사위 표현???
* */

public class BOJ14499 {
    static int[][] board;
    static int[][] dice = new int[3][3];
    static int bottom = 0;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dice[i], 0);
        }


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Coordinate tmpPos = new Coordinate(x, y);

        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());

            //현재 위치 옮겨주기
            tmpPos.x += dx[dir];
            tmpPos.y += dy[dir];

            if (tmpPos.x < 0 || tmpPos.y < 0 || tmpPos.x >= n || tmpPos.y >= m)  {
                tmpPos.x -= dx[dir];
                tmpPos.y -= dy[dir];
                continue;
            }

            //이번 이동에 밑으로 갈 위치 저장
            int nextBottom = dice[1 + dx[dir]][1 + dy[dir]];

            //굴러가고 나서의 주사위 모양으로 바꿔줌
            roll(dir);
            bw.write(dice[1][1] + "");
            bw.newLine();
            if (board[tmpPos.x][tmpPos.y] == 0) board[tmpPos.x][tmpPos.y] = nextBottom;
            else {
                nextBottom = board[tmpPos.x][tmpPos.y];
                board[tmpPos.x][tmpPos.y] = 0;
            }

            bottom = nextBottom;

        }
        bw.close();
    }

    private static void roll(int dir) {
        dice[1 + dx[dir]][1 + dy[dir]] = dice[1][1];
        dice[1][1] = dice[1 - dx[dir]][1 - dy[dir]];
        dice[1 - dx[dir]][1 - dy[dir]] = bottom;
    }
}

```
dice[1][1]이 주사위의 가장 윗면을 의미하고, [1][1]로부터 동서남북에 해당하는 면을 저장했다. 그리고 1, 2, 3, 4의 입력이 각각 동 서 북 남을 의미하므로, dx, dy 배열의 입력받는 인덱스가 해당하는 방향을 가리키도록 설정했다.
<br> 그리고 주사위를 굴리는 입력이 들어오면, 현재 위치를 가리키는 tmpPos 변수에 반영하고, 이동할 수 있는 위치라면 주사위를 굴린다. (roll() 함수) 이 과정 역시도 dx, dy 배열의 인덱스를 이용해서 밀어내도록 했다.
<br> 해당하는 위치의 숫자와 주사위 밑면의 숫자가 변경되는 과정의 구현은 간단하기 때문에 생략한다.

---

### [4. 백준 13335 - 트럭](https://www.acmicpc.net/problem/13335)
![](https://velog.velcdn.com/images/bumstead/post/5b0ba277-7c03-407e-9b32-3c7026bbf96f/image.png) 이 문제는 구현이라기 보다 자료구조 queue의 연습문제라고 봐야할 것 같다. 최대 하중을 매번 신경써가면서 queue에 offer, poll해주면 된다.
<br> 처음에는 한 번의 반복마다 한 번의 전진이 일어나도록 했는데, 이렇게 하면 시간복잡도가 엄청나게 커졌다. 그래서 다리의 최대하중 때문에 queue에 트럭이 들어오지 못하는 경우에는, 가장 앞쪽의 트럭이 빠져나갈 때까지의 시간을 한 번에 더해줬다.
```java
package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Truck {
    int weight;
    int left;

    public Truck(int weight, int left) {
        this.weight = weight;
        this.left = left;
    }

    @Override
    public String toString() {
        return "weight = " + weight + ", left = " + left;
    }
}

public class BOJ13335 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //트럭 수
        int w = Integer.parseInt(st.nextToken()); //다리 길이
        int L = Integer.parseInt(st.nextToken()); //최대 하중
        int[] trucks = new int[n];
        Queue<Truck> queue = new LinkedList<>();
        int tmpWeight = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
        queue.offer(new Truck(trucks[0], w));
        tmpWeight += trucks[0];

        int totalTime = 1;

        for (int i = 1; i < n; i++) {
//            System.out.println(queue);
//            System.out.println(totalTime);

            if (tmpWeight + trucks[i] > L) {
                while (tmpWeight + trucks[i] > L) {
                    int tmpLeft = queue.peek().left;
                    for (Truck truck : queue) {
                        truck.left -= tmpLeft;
                    }

                    totalTime += tmpLeft;
                    tmpWeight -= queue.poll().weight;
                }
            } else {
                for (Truck truck : queue) truck.left--;
                totalTime++;
                if (!queue.isEmpty() && queue.peek().left == 0) tmpWeight -= queue.poll().weight;
            }
            queue.offer(new Truck(trucks[i], w));
            tmpWeight += trucks[i];

        }

        while (!queue.isEmpty()) {
            int tmpLeft = queue.peek().left;
            for (Truck truck : queue) truck.left -= tmpLeft;

            totalTime += tmpLeft;
            queue.poll();
        }

        System.out.println(totalTime);
    }
}

```
Truck 객체는 트럭의 무게와 남은 거리를 멤버로 갖는다. 로직은 다음과 같다

0. 1을 모든 트럭에 대해서 반복
1. queue 안의 트럭 무게의 합과 다음 차례인 트럭의 무게를 더한 값이 L보다 큰 지 확인한다.
1-1. 그렇다면,queue 가장 앞쪽의 트럭의 남은 거리만큼 모든 트럭을 이동시킨다. (트럭을 poll())
1-2. 그렇지 않다면, 모든 트럭을 앞으로 진행시키고 queue에 새 트럭을 집어넣는다. 
2. 모든 트럭을 검사한 후에 (queue에 집어넣은 후에), queue에 남아 있는 트럭을 모두 내보낸다.
3. 소모된 시간을 반환한다.

---
### [5. 백준 16985 - Maaaaaaaaaze](https://www.acmicpc.net/problem/16985)
![](https://velog.velcdn.com/images/bumstead/post/083ea063-9370-4aed-a31b-b1f69a2d760b/image.png)![](https://velog.velcdn.com/images/bumstead/post/fa3fdbc0-c91d-476c-ad45-07bbc1cd9a56/image.png) 이 문제 역시 문제를 꼼꼼히 보지 않아서 초반에 삽질을 많이 했다. 판들을 네 방향 모두 회전하는 경우 (4^5)에 대해서만 계산하고, 판을 쌓는 순서 (5!)를 바꿀 수 있다는 것을 고려하지 않았었다. 
<br> 판을 돌리는 함수 구현과, BFS로 경로를 검사하는 로직 정도가 관건인 문제였다. 귀찮고 조금 복잡하지만 구현의 아이디어가 어렵지는 않았다.

1. 모든 판의 방향과, 쌓는 순서에 대해서 검사한다.
2. BFS로 해당 3차원 배열이 탈출할 수 있는 미로인지 확인한다. (0,0,0과 4,4,4가 이어져 있는지)
2-1. 그렇다면, 최소 이동 거리를 반환한다. (bfs의 depth)
2-2. 그렇지 않다면, -1을 반환한다.
```java
package simulation;


/*
* 1. 판떼기 돌리기조합 만드르서 (4진수)
* 2. bfs로 최단거리
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Coordinate3{
    int x;
    int y;
    int z;

    public Coordinate3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Coordinate3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
public class BOJ16985 {
    static boolean[][][] board = new boolean[5][5][5];
    static boolean[][][] visited;
    static boolean[][][] tmpBoard;
    static int[] dx = {1, 0, 0, -1, 0, 0};
    static int[] dy = {0, 1, 0, 0, -1, 0};
    static int[] dz = {0, 0, 1, 0, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        backTrack(0);
//        for (int i = 0; i < 120; i++) {
//            System.out.println(Arrays.toString(cases2[i]));
//        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    board[i][j][k] = st.nextToken().equals("1") ? true : false; //true가 갈 수 있는 곳
                }
            }
        }
        int[] cases = new int[5];

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < 1024; i++) {
            for (int l = 0; l < 120; l++) {
                int[] tmpStack = cases2[l];
                //board 가져오기
                tmpBoard = new boolean[5][5][5];
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        tmpBoard[tmpStack[j]][k] = Arrays.copyOf(board[j][k], 5);
                    }
                }

//                if (l == 3 && i ==0) {
//                    System.out.println(Arrays.toString(tmpStack));
//                    showboard();
//                }

                fourJinsu(cases, i);

                //돌리기~
                for (int j = 0; j < 5; j++) { //모든 층에대해서
                    for (int k = 0; k < cases[j]; k++) { //돌리기 반복 횟수
                        bingbing(j);
                    }
                }
                if (!tmpBoard[0][0][0] || !tmpBoard[4][4][4]) continue;
                int tmpDist = bfs(tmpBoard);
                if (tmpDist == 12) {
                    showboard();
                }
//                if (tmpDist != -1 ) System.out.println(tmpDist);

                if (tmpDist == -1) continue;
                answer = answer <= tmpDist ? answer : tmpDist;
            }

        }
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static int[] arr = new int[5];
    static int[][] cases2 = new int[120][5];
    static int backCnt = 0;
    static boolean[] visited2 = new boolean[5];
    private static void backTrack(int depth) {
        if (depth == 5) {
            cases2[backCnt++] = Arrays.copyOf(arr, 5);
        }

        for (int i = 0; i < 5; i++) {
            if (visited2[i]) continue;

            visited2[i] = true;
            arr[depth] = i;
            backTrack(depth+1);
            visited2[i] = false;
        }
    }

    private static void showboard() {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                System.out.println(Arrays.toString(tmpBoard[j][k]));
            }
            System.out.println("--------------------------------");
        }
    }

    private static void fourJinsu(int[] cases, int n) {
        for (int i = 0; i < 5; i++) {
            cases[i] = n % 4;
            n = n / 4;
        }
    }

    private static void bingbing(int n) {
        boolean[][] newThang = new boolean[5][5];
        int cnt = 0;

        for (int j = 0; j < 5; j++) {
            for (int i = 4; i >= 0; i--) {
                newThang[cnt / 5][cnt % 5] = tmpBoard[n][i][j];
                cnt++;
            }
        }
        tmpBoard[n] = newThang;
    }

    private static int bfs(boolean[][][] board) {
        visited = new boolean[5][5][5];
        Queue<Coordinate3> queue = new LinkedList<>();
        queue.offer(new Coordinate3(0, 0, 0));
        visited[0][0][0] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Coordinate3 tmp = queue.poll();

                for (int j = 0; j < 6; j++) {
                    int tmpx = tmp.x + dx[j];
                    int tmpy = tmp.y + dy[j];
                    int tmpz = tmp.z + dz[j];

                    if(tmpx < 0 || tmpy < 0 || tmpz <0||tmpx>4||tmpy>4||tmpz>4) continue;
                    if (visited[tmpz][tmpx][tmpy] || !board[tmpz][tmpx][tmpy]) continue;

                    if (tmpx == 4 && tmpy == 4 && tmpz == 4) return ++cnt;

                    queue.offer(new Coordinate3(tmpx, tmpy, tmpz));
                    visited[tmpz][tmpx][tmpy] = true;
                }
            }
            cnt++;
        }
        return -1;
    }
}

```
binbing()은 판을 돌리는 메서드, backTrack()과 fourJinsu()는 각각 판을 쌓는 순서와 판을 돌리는 경우의 수를 구하는 메서드이다. 
<br> 모든 경우에 대해서 판을 돌리고 쌓은 뒤에, bfs로 해당 미로의 최단 경로를 찾는 순서로 코드를 짰다. 로직은 간단하지만, 3차원 배열을 계속해서 수정하는 과정이 필요해서 코드가 매우 길어졌다.

---

# 끝
이것으로 시뮬레이션 연습문제를 추가적으로 풀어봤다. 구현 문제 자체는 엄청나게 많지만 유형에 익숙해지고 특징을 파악하는 정도가 목표였기 때문에, 다 해서 아홉 문제 정도만 풀어봤다. 

> 연습문제 출처 : [encrypted-def github](https://github.com/encrypted-def/basic-algo-lecture/blob/master/workbook/0x0D.md)
