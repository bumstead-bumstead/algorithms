package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ12100_2 {
    static int[][] board;
    static int[][] tmpBoard;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] direction = new int[5];
        int answer = 0;
        for (int i = 0; i < 1024; i++) { // 0 1 1 1 1 : 4 + 16 + 64 + 256
            tmpBoard = new int[n][n];

            for (int j = 0; j < n; j++) {
                tmpBoard[j] = Arrays.copyOf(board[j], n);
            }

            fourJinsu(direction, i);
//            System.out.println(Arrays.toString(direction));

            for (int x : direction) {
                switch (x) {
                    case 0 : up2(tmpBoard);
                        break;
                    case 1 : right2(tmpBoard);
                        break;
                    case 2 : down2(tmpBoard);
                        break;
                    case 3 : left2(tmpBoard); break;
                }

//                if (i == 340) {
//                    System.out.println(Arrays.toString(direction));
//                    showBoard();
//                    System.out.println("---------------------");
//                }
            }

            answer = maxNum(tmpBoard) > answer ? maxNum(tmpBoard) : answer;
        }
        System.out.println(answer);
    }

    private static void showBoard() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(tmpBoard[i]));
        }
    }

    private static void fourJinsu(int[] direction, int n) {
        for (int i = 0; i < 5; i++) {
            direction[i] = n % 4;
            n = n / 4;
        }
    }

    private static int maxNum(int[][] tmpBoard) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tmpBoard[i][j] > answer) answer = tmpBoard[i][j];
            }
        }
        return answer;
    }


    //왼쪽으로 쌔리는경우
    static void left2(int[][] tmpBoard) {
        /*
        * 스택에 쌓으면서 같은숫자면 pop(), 방어막 하나 넣어놓음
        * */
        for (int i = 0; i < n; i++) {
            LinkedList<Integer> deque = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if (tmpBoard[i][j] == 0) continue;
                if (!deque.isEmpty() && deque.peekLast() == tmpBoard[i][j]) {
                    deque.offer(deque.pollLast()*2);
                    deque.offer(-1);
                } else {
                    deque.offer(tmpBoard[i][j]);
                }
                tmpBoard[i][j] = 0;
            }

            int cnt = 0;
            while (!deque.isEmpty()) {
                int tmp = deque.poll();
                if (tmp != -1) tmpBoard[i][cnt++] = tmp;
            }
        }
    }

    static void right2(int[][] tmpBoard) {
        /*
         * 스택에 쌓으면서 같은숫자면 pop(), 방어막 하나 넣어놓음
         * */
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            deque.clear();
            for (int j = n-1; j >= 0; j--) {
                if (tmpBoard[i][j] == 0) continue;
                if (!deque.isEmpty() && deque.peekLast() == tmpBoard[i][j]) {
                    deque.offer(deque.pollLast()*2);
                    deque.offer(-1);
                } else {
                    deque.offer(tmpBoard[i][j]);
                }
                tmpBoard[i][j] = 0;

            }
            int cnt = n-1;
            while (!deque.isEmpty()) {
                int tmp = deque.poll();
                if (tmp != -1) tmpBoard[i][cnt--] = tmp;
            }
        }
    }
    static void up2(int[][] tmpBoard) {
        /*
         * 스택에 쌓으면서 같은숫자면 pop(), 방어막 하나 넣어놓음
         * */
        LinkedList<Integer> deque = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (tmpBoard[i][j] == 0) continue;

                if (!deque.isEmpty() && deque.peekLast() == tmpBoard[i][j]) {
                    deque.offer(deque.pollLast()*2);
                    deque.offer(-1);
                } else {
                    deque.offer(tmpBoard[i][j]);
                }
                tmpBoard[i][j] = 0;
            }
            int cnt = 0;
            while (!deque.isEmpty()) {
                int tmp = deque.poll();
                if (tmp != -1) tmpBoard[cnt++][j] = tmp;
            }
        }
    }

    static void down2(int[][] tmpBoard) {
        /*
         * 스택에 쌓으면서 같은숫자면 pop(), 방어막 하나 넣어놓음
         * */
        LinkedList<Integer> deque = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            for (int i = n-1; i >= 0; i--) {
                if (tmpBoard[i][j] == 0) continue;
                if (!deque.isEmpty() && deque.peekLast() == tmpBoard[i][j]) {
                    deque.offer(deque.pollLast()*2);
                    deque.offer(-1);
                } else {
                    deque.offer(tmpBoard[i][j]);
                }
                tmpBoard[i][j] = 0;
            }
            int cnt = n-1;
            while (!deque.isEmpty()) {
                int tmp = deque.poll();
                if (tmp != -1) tmpBoard[cnt--][j] = tmp;
            }
        }
    }




    //------------------------------trash---------------------------------
    //상
//    static void up(int[][] tmpBoard) {
//        boolean prevChecked = false;
//        for (int j = 0; j < n; j++) { //col
//            int prev = 0; //이전 숫자의 idx 저장
//            for (int i = 1; i < n; i++) { // row
//                if (tmpBoard[i][j] == 0){
//                    prevChecked = false;
//                    continue;
//                }
//                if (tmpBoard[i][j] == tmpBoard[prev][j] && !prevChecked) {
//                    prevChecked = true;
//                    tmpBoard[prev][j] = 2*tmpBoard[prev][j];
//                    tmpBoard[i][j] = 0;
//                }
//                //다른 숫자를 만났을 때
//                else {
//                    prevChecked = false;
//                    if (tmpBoard[prev][j] == 0) {
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[prev][j] = tmp;
//                    } else{
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[prev+1][j] = tmp;
//                        prev = prev+1;
//                    }
//                }
//            }
//        }
//    }
//    static void down(int[][] tmpBoard) {
//        boolean prevChecked = false;
//
//        for (int j = 0; j < n; j++) { //col
//            int prev = n-1; //이전 숫자의 idx 저장
//            for (int i = n-2; i >= 0; i--) { // row
//                if (tmpBoard[i][j] == 0) {
//                    prevChecked = false;
//                    continue;
//                }
//                if (tmpBoard[i][j] == tmpBoard[prev][j] && !prevChecked) {
//                    prevChecked = true;
//                    tmpBoard[prev][j] = 2*tmpBoard[prev][j];
//                    tmpBoard[i][j] = 0;
//                }
//                //다른 숫자를 만났을 때
//
//                else {
//                    prevChecked = false;
//                    if (tmpBoard[prev][j] == 0) {
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[prev][j] = tmp;
//                    } else{
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[prev-1][j] = tmp;
//                        prev = prev-1;
//                    }
//                }
//            }
//        }
//    }
//    static void right(int[][] tmpBoard) {
//        boolean prevChecked = false;
//
//        for (int i = 0; i < n; i++) { //col
//            int prev = n-1; //이전 숫자의 idx 저장
//            for (int j = n-2; j >= 0; j--) { // row
//                if (tmpBoard[i][j] == 0) {
//                    prevChecked = false;
//                    continue;
//                }
//                if (tmpBoard[i][j] == tmpBoard[i][prev] && !prevChecked) {
//                    prevChecked = true;
//                    tmpBoard[i][prev] = 2*tmpBoard[i][prev];
//                    tmpBoard[i][j] = 0;
//                }
//                //다른 숫자를 만났을 때
//                else {
//                    prevChecked = false;
//                    if (tmpBoard[prev][j] == 0) {
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[i][prev] = tmp;
//                    } else {
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[i][prev-1] = tmp;
//                        prev = prev-1;
//                    }
//                }
//            }
//        }
//    }
//    static void left(int[][] tmpBoard) {
//        boolean prevChecked = false;
//
//        for (int i = 0; i < n; i++) { //col
//            int prev = 0; //이전 숫자의 idx 저장
//            for (int j = 1; j < n; j++) { // row
//                if (tmpBoard[i][j] == 0) {
//                    prevChecked = false;
//                    continue;
//                }
//                if (tmpBoard[i][j] == tmpBoard[i][prev]) {
//                    prevChecked = true;
//                    tmpBoard[i][prev] = 2*tmpBoard[i][prev];
//                    tmpBoard[i][j] = 0;
//                }
//                //다른 숫자를 만났을 때
//                else {
//                    prevChecked = false;
//                    if (tmpBoard[prev][j] == 0) {
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[i][prev] = tmp;
//                    } else {
//                        int tmp = tmpBoard[i][j];
//                        tmpBoard[i][j] = 0;
//                        tmpBoard[i][prev+1] = tmp;
//                        prev = prev+1;
//                    }
//
//                }
//            }
//        }
//    }

}
