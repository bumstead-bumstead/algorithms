package programmers;

import java.util.Arrays;

public class KeyAndLock {

    class Solution {
        public boolean solution(int[][] key, int[][] lock) {
            int m = key.length;

            for (int i = 0; i <4; i++) {
                key = rotate(key);

                //todo : key의 가장 왼쪽 위 원소와 대응되는 lock의 index = (j, k)
                // -m + 1 < (j, k) < n+1
                for (int j = -key.length+1; j < lock.length; j++) {
                    for (int k = -key.length+1; k < lock.length; k++) {
                        if (isCompatible(key, lock, j, k)) return true;
                        //Todo : 여기서 비교
                    }
                }
            }

            return false;
        }

        private int[][] rotate(int[][] board) {

            int[][] newBoard = new int[board.length][board.length];

            for (int i = 0; i < board.length; i++) { //col
                for (int j = board.length-1; j>=0;j--) { //row
                    newBoard[i][board.length - j - 1] = board[j][i];
                }
            }
            return newBoard;
        }

        //todo : lock에 대한 (key 가장 왼쪽 위 원소)의 상대적 위치= (dr, dc)
        // -m + 1 < (dr, dc) < n+1
        private boolean isCompatible(int[][] key, int[][] lock, int dr, int dc) {
            for (int i = 0; i < lock.length; i++) {
                for (int j = 0; j < lock.length; j++) {

                    if (i < dr || i >= dr + key.length
                            || j < dc || j >= dc + key.length) {
                        if (lock[i][j] == 0) return false;
                        continue;
                    }

                    if (lock[i][j] == key[i - dr][j - dc]) return false;
                }
            }

            return true;
        }


        private void showBoard(int[][] board) {
            for (int i =0; i < board.length; i++) {
                System.out.println(Arrays.toString(board[i]));
            }
        }

    }
}
