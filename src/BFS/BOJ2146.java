package BFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//1. 덩어리 bfs 하면서 덩어리별로 가장자리 idx 모두 저장
// 가장자리들끼리 비굥~
// 놀랍게도 시간초과 안뜸
public class BOJ2146 {
    static int[][] board;
    static int n;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static List<List> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String[] a = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(a[j]);
            }
        }

        bfs();
        for (List x : list) {
            System.out.println(x);
        }

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < list.size()-1; i++) {
            List<Pair> list1 = list.get(i);
            for (int j = i+1; j < list.size(); j++) {
//                System.out.println(i + "번째 리스트랑 " + j + "번째 리스트 비교");
                List<Pair> list2 = list.get(j);

                for (Pair pair1 : list1) {
                    for (Pair pair2 : list2) {
//                        System.out.println(pair1 + "," + pair2 + " 비교");
//                        System.out.println(Math.abs(pair1.getX() - pair2.getX()) + Math.abs(pair1.getY() - pair2.getY()) - 1);
                        int distance = Math.abs(pair1.getX() - pair2.getX()) + Math.abs(pair1.getY() - pair2.getY()) - 1;
                        if (distance < minDistance) minDistance = distance;
                    }
                }
            }
        }
        System.out.println(minDistance);
    }


    private static void bfs() {
        list = new ArrayList<>();

        Queue<Pair> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 || visited[i][j]) continue;

                queue.offer(new Pair(i, j));
                visited[i][j] = true;
                List<Pair> tmpList = new ArrayList<>();
                while (!queue.isEmpty()){
                    Pair tmp = queue.poll();
                    boolean appended = false;

                    for (int k = 0; k < 4; k++) {

                        int nx = tmp.getX() + dx[k];
                        int ny = tmp.getY() + dy[k];

                        if (nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || visited[nx][ny]) continue;
                        if (board[nx][ny] == 0) {
                            if (appended) continue;
                            appended = true;
                            tmpList.add(tmp);
                            continue;
                        }

                        queue.offer(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                list.add(tmpList);
            }
        }
    }
}
