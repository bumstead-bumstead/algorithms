package simulation;

import java.util.Arrays;

public class test {

    static boolean[][] tmpSticker;
    static int[][] board;
    static int n = 4;

    public static void main(String[] args) {
        tmpSticker = new boolean[][]{{true, true, true, true, true}, {false, false, false, true, false}};
        board = new int[][]{{8,4,4,16},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
//        rotate(board);rotate(board);rotate(board);
        showBoard(4);
        right(board);
//        System.out.println("-----------");
//        showBoard(3);
        System.out.println("-----------");
//        left(board);
        showBoard(4);
    }

    private static void showBoard(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    private static void rotate(int[][] old) {
        int y = old[0].length;
        int x = old.length;
        int[][] newThang = new int[y][x];
        int cnt = 0;

        for (int i = 0; i < y; i++) { //열에 대한 검사
            for (int j = x - 1; j >= 0; j--) { //행에 대한 검사
//                System.out.println("(" + j + ", " + i + ") to (" + (cnt/x) + ", " + (cnt%x) + ")");
                newThang[cnt / x][cnt % x] = old[j][i];
                cnt++;
            }
        }
        board = newThang;
    }
    static void up(int[][] board) {
        for (int j = 0; j < n; j++) { //col
            int prev = 0; //이전 숫자의 idx 저장
            for (int i = 1; i < n; i++) { // row
                if (board[i][j] == 0) continue;
                if (board[i][j] == board[prev][j]) {
                    board[prev][j] = 2*board[prev][j];
                    board[i][j] = 0;
                }
                //다른 숫자를 만났을 때
                else {
                    if (board[prev][j] == 0) {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[prev][j] = tmp;
                    } else{
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[prev+1][j] = tmp;
                        prev = prev+1;
                    }

                }
            }
        }
    }
    static void down(int[][] board) {
        for (int j = 0; j < n; j++) { //col
            int prev = n-1; //이전 숫자의 idx 저장
            for (int i = n-2; i >= 0; i--) { // row
                if (board[i][j] == 0) continue;
                if (board[i][j] == board[prev][j]) {
                    board[prev][j] = 2*board[prev][j];
                    board[i][j] = 0;
                }
                //다른 숫자를 만났을 때
                else {
                    if (board[prev][j] == 0) {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[prev][j] = tmp;
                    } else{
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[prev-1][j] = tmp;
                        prev = prev-1;
                    }
                }
            }
        }
    }
    static void right(int[][] board) {
        boolean prevChecked = false;
        for (int i = 0; i < n; i++) { //col
            int prev = n - 1; //이전 숫자의 idx 저장
            for (int j = n - 2; j >= 0; j--) { // row
                if (board[i][j] == 0) {
                    prevChecked = false;
                    continue;
                }
                if (board[i][j] == board[i][prev] && !prevChecked) {
                    prevChecked = true;
                    board[i][prev] = 2 * board[i][prev];
                    board[i][j] = 0;
                }
                //다른 숫자를 만났을 때
                else {
                    prevChecked = false;
                    if (board[i][prev] == 0) {
                        System.out.println(prev + ", " + j);
                        System.out.println("oo");
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[i][prev] = tmp;
                    } else {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[i][prev - 1] = tmp;
                        prev = prev - 1;
                    }
                }
            }
        }
    }
    static void left(int[][] board) {
        for (int i = 0; i < n; i++) { //col
            int prev = 0; //이전 숫자의 idx 저장
            for (int j = 1; j < n; j++) { // row
                if (board[i][j] == 0) continue;
                if (board[i][j] == board[i][prev]) {
                    board[i][prev] = 2*board[i][prev];
                    board[i][j] = 0;
                }
                //다른 숫자를 만났을 때
                else {
                    if (board[prev][j] == 0) {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[i][prev] = tmp;
                    } else {
                        int tmp = board[i][j];
                        board[i][j] = 0;
                        board[i][prev+1] = tmp;
                        prev = prev+1;
                    }
                }
            }
        }
    }
}
