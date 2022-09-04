package simulation;

import java.util.Arrays;
import java.util.LinkedList;

public class test {
    static int[][] test;
    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>();
        a.offer(1);
        a.offer(2);
        a.offer(3);

    }
    static void showboard(int[][] board) {
        for (int j = 0; j < 5; j++) {
            System.out.println(Arrays.toString(board[j]));
        }
        System.out.println("--------------------------------");

    }
    static void bingbing() {
        int[][] newThang = new int[5][5];
        int cnt = 0;

        for (int j = 0; j < 5; j++) {
            for (int i = 4; i >= 0; i--) {
                newThang[cnt / 5][cnt % 5] = test[i][j];
                cnt++;
            }
        }
        test = newThang;
    }
}
