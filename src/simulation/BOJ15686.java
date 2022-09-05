package simulation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Coordinate {
    int x;
    int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;

    }

}
public class BOJ15686 {
    static List<Coordinate> houses = new ArrayList<>();
    static List<Coordinate> chickens = new ArrayList<>();
    static boolean[] survived;
    static int[][] board;
    static int n;
    static int m;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) houses.add(new Coordinate(i, j));
                else if (tmp == 2) chickens.add(new Coordinate(i, j));
                board[i][j] = tmp;
            }
        }

        survived = new boolean[chickens.size()];

        chickenComb(0,0);
        System.out.println(answer);
    }

    private static void chickenComb(int depth, int start) {
        if (depth == m) {
            int tmpMin = chickenDistance();
            answer = answer < tmpMin ? answer : tmpMin;
//            System.out.println(Arrays.toString(survived));
        }

        for (int i = start; i < survived.length; i++) {
            survived[i] = true;
            chickenComb(depth+1, i+1);
            survived[i] = false;
        }
    }

    private static int chickenDistance() {
        int distance = 0;
        for (Coordinate house : houses) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < chickens.size(); i++) {
                if (!survived[i]) continue;

                int tmpDist = Math.abs(house.x - chickens.get(i).x) + Math.abs(house.y - chickens.get(i).y);
                minDist = minDist < tmpDist ? minDist : tmpDist;
            }
            distance += minDist;
        }

        return distance;
    }
}
