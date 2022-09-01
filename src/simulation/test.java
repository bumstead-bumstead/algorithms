package simulation;

import java.util.Arrays;

public class test {
    static int[][] test;
    public static void main(String[] args) {
         test = new int[][]{{1, 1, 1, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 1}};
        showboard(test);
        bingbing();
        showboard(test);
        bingbing();
        showboard(test);
        bingbing();
        showboard(test);
        bingbing();



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
