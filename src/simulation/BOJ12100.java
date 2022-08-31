package simulation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
* 첫 풀이는 끝에 숫자가 없을 때 끝으로 옮기는 경우를 처리하지 못했음. prev 기본값을 0으로 했기때무닝다 -> 0번째 원소가 무조건 0이 아니라고 정해버린것
* 두번째풀이는 한연산에 두번더하기
* */
public class BOJ12100 {
    static int[][] board;
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

        int[] direction = new int[4];
        int answer = 0;
        for (int i = 0; i < 1024; i++) {
            int[][] tmpBoard = board;
            fourJinsu(direction, i);
//            System.out.println(Arrays.toString(direction));

            for (int x : direction) {
                switch (x) {
                    case 0 : up(tmpBoard); break;
                    case 1 : right(tmpBoard); break;
                    case 2 : down(tmpBoard); break;
                    case 3 : left(tmpBoard); break;
                }
            }

            answer = maxNum(tmpBoard) > answer ? maxNum(tmpBoard) : answer;
        }
        System.out.println(answer);
    }

    private static void fourJinsu(int[] direction, int n) {
        for (int i = 0; i < 4; i++) {
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


    //상
    static void up(int[][] tmpBoard) {
        boolean prevChecked = false;
        for (int j = 0; j < n; j++) { //col
            int prev = 0; //이전 숫자의 idx 저장
            for (int i = 1; i < n; i++) { // row
                if (tmpBoard[i][j] == 0){
                    prevChecked = false;
                    continue;
                }
                if (tmpBoard[i][j] == tmpBoard[prev][j] && !prevChecked) {
                    prevChecked = true;
                    tmpBoard[prev][j] = 2*tmpBoard[prev][j];
                    tmpBoard[i][j] = 0;
                }
                //다른 숫자를 만났을 때
                else {
                    prevChecked = false;
                    if (tmpBoard[prev][j] == 0) {
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[prev][j] = tmp;
                    } else{
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[prev+1][j] = tmp;
                        prev = prev+1;
                    }

                }
            }
        }
    }
    static void down(int[][] tmpBoard) {
        boolean prevChecked = false;

        for (int j = 0; j < n; j++) { //col
            int prev = n-1; //이전 숫자의 idx 저장
            for (int i = n-2; i >= 0; i--) { // row
                if (tmpBoard[i][j] == 0) {
                    prevChecked = false;
                    continue;
                }
                if (tmpBoard[i][j] == tmpBoard[prev][j] && !prevChecked) {
                    prevChecked = true;
                    tmpBoard[prev][j] = 2*tmpBoard[prev][j];
                    tmpBoard[i][j] = 0;
                }
                //다른 숫자를 만났을 때

                else {
                    prevChecked = false;
                    if (tmpBoard[prev][j] == 0) {
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[prev][j] = tmp;
                    } else{
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[prev-1][j] = tmp;
                        prev = prev-1;
                    }
                }
            }
        }
    }
    static void right(int[][] tmpBoard) {
        boolean prevChecked = false;

        for (int i = 0; i < n; i++) { //col
            int prev = n-1; //이전 숫자의 idx 저장
            for (int j = n-2; j >= 0; j--) { // row
                if (tmpBoard[i][j] == 0) {
                    prevChecked = false;
                    continue;
                }
                if (tmpBoard[i][j] == tmpBoard[i][prev] && !prevChecked) {
                    prevChecked = true;
                    tmpBoard[i][prev] = 2*tmpBoard[i][prev];
                    tmpBoard[i][j] = 0;
                }
                //다른 숫자를 만났을 때
                else {
                    prevChecked = false;
                    if (tmpBoard[prev][j] == 0) {
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[i][prev] = tmp;
                    } else {
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[i][prev-1] = tmp;
                        prev = prev-1;
                    }
                }
            }
        }
    }
    static void left(int[][] tmpBoard) {
        boolean prevChecked = false;

        for (int i = 0; i < n; i++) { //col
            int prev = 0; //이전 숫자의 idx 저장
            for (int j = 1; j < n; j++) { // row
                if (tmpBoard[i][j] == 0) {
                    prevChecked = false;
                    continue;
                }
                if (tmpBoard[i][j] == tmpBoard[i][prev]) {
                    prevChecked = true;
                    tmpBoard[i][prev] = 2*tmpBoard[i][prev];
                    tmpBoard[i][j] = 0;
                }
                //다른 숫자를 만났을 때
                else {
                    prevChecked = false;
                    if (tmpBoard[prev][j] == 0) {
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[i][prev] = tmp;
                    } else {
                        int tmp = tmpBoard[i][j];
                        tmpBoard[i][j] = 0;
                        tmpBoard[i][prev+1] = tmp;
                        prev = prev+1;
                    }

                }
            }
        }
    }
}
