package simulation;

/*
* 1. board 왼쪽 위부터 쭉 검사
* 2. 빈칸 만나면 스티커 크기 (r,c)에 대해서 스티커가 있는 부분이 비어있는지 검사
* 2-1. 그렇다면, 붙이고 다음 스티커에 대해서 반복
* 2-2. 그렇지 않다면, 계속 검사
* 3. 끝까지 못찾으면, 스티커 돌린 뒤 처음부터 검사 (
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ18808 {
    static boolean[][] tmpSticker;
    static boolean[][] laptop;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        laptop = new boolean[n][m];
        int nSticker = Integer.parseInt(st.nextToken());
        int cnt = 0;

        for (int i = 0; i < nSticker; i++) {
            //스티커 초기 설정
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tmpSticker = new boolean[x][y];
            for (int j = 0; j < x; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < y; k++) {
                    if (st.nextToken().equals("1")) tmpSticker[j][k] = true;
                }
            }
//            showBoard();

            //스티커 붙일 수 있는 지 검사 후 붙이기
            a : for (int l = 0; l < 4; l++){
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        if (isAttachable(j, k)) {
                            cnt += attach(j, k);
                            break a;
                        }
                    }
                }

//                System.out.println("---------rotate----------");
                rotate(x, y);
                int tmp = x;
                x = y;
                y = tmp;

            }
        }

        System.out.println(cnt);
    }

    private static boolean isAttachable(int row, int col) {
        for (int i = 0; i < tmpSticker.length; i++) {
            for (int j = 0; j < tmpSticker[0].length; j++) {
                if (row+i >= laptop.length || col+j >= laptop[0].length) return false;

                if (laptop[row+i][col+j] && tmpSticker[i][j]) return false;
            }
        }
        return true;
    }

    private static int attach(int row, int col) {
        int answer = 0;
        for (int i = 0; i < tmpSticker.length; i++) {
            for (int j = 0; j < tmpSticker[0].length; j++) {
                if (tmpSticker[i][j]) {
                    laptop[row+i][col+j] = true;
                    answer++;
                }
            }
        }
        return answer;
    }

    private static void rotate(int x, int y) {
        boolean[][] newThang = new boolean[y][x];
        int cnt = 0;

        for (int i = 0; i < y; i++) { //열에 대한 검사
            for (int j = x-1; j >= 0; j--) { //행에 대한 검사
//                System.out.println("(" + j + ", " + i + ") to (" + (cnt/x) + ", " + (cnt%x) + ")");
                newThang[cnt / x][cnt % x] = tmpSticker[j][i];
                cnt++;
            }
        }

//        showBoard();
        tmpSticker = newThang;
        int tmp = x;
        x = y;
        y = tmp;

//        showBoard();
    }

    private static void showBoard() {
        for (int i = 0; i < tmpSticker.length; i++) {
            System.out.println(Arrays.toString(tmpSticker[i]));
        }
    }
}


