package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//class Pair {
//    int x;
//    int y;
//
//    public Pair(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    @Override
//    public String toString() {
//        return "Pair{" +
//                "x=" + x +
//                ", y=" + y +
//                '}';
//    }
//}


//2중 for문으로 싹다 검사하면서 List에 좌표 정보 (pair)를 저장하는 방식 -> 시간, 메모리 모두 초과
//같은행에는 어차피 두개 존재할 수 없기 떄문에, 행별로 하나씩 arr에 저장하는 방식. list와 pair를 쓸 필요도 없고 탐색 시간도 훨씬적다
public class BOJ9663 {
    static int[] tmpQueens;
    static int n;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tmpQueens = new int[n];

        nQueen2(0);
        System.out.println(cnt);
    }


    private static void nQueen2(int k) {
        if (k == n) {
            cnt++;
            return;
        }

        a : for (int j = 0; j < n; j++) {
            //list -> arr 로 바꾸고, 어차피 행마다 검사하니까 Pair -> int로 바꿈
            //prohibited 검사할 때는, 값이 같은지와, 값의 차가 인덱스의 차와 같은지 확인

            for (int i = 0; i < k; i++) {
                if (tmpQueens[i] == j || Math.abs(tmpQueens[i] - j) == k - i) continue a;
            }

            tmpQueens[k] = j;
            nQueen2(k + 1);

//            for (Pair old : tmpQueens) if (prohibited(old, k, j)) continue a;
//            tmpQueens.add(new Pair(k, j));
//            nQueen2(k + 1);
//            tmpQueens.remove(tmpQueens.size() - 1);
        }
    }

//    private static boolean prohibited(Pair old, int x, int y) {
//        if (old.x == x || old.y == y || Math.abs(old.x - x) == Math.abs(old.y - y)) {
//            return true;
//        }
//        return false;
//    }
}
