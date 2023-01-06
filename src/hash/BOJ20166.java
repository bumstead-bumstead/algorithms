package hash;

//해시로 어떻게 풀라는겨
/*
* 1. 목표로 하는 문자열의 시작하는 문자인 타일에 대해서 반복
* 2. 상하좌우를 검사해서 목표로 하는 문자열의 각 위치의 문자와 일치하면, queue에 append
* 3.
* */

import java.io.*;
import java.util.*;

public class BOJ20166 {

    private static int[] dx = new int[]{1, 0, -1, 0, 1, -1, 1, -1};
    private static int[] dy = new int[]{0, 1, 0, -1, 1, 1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[][] tiles = new int[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                tiles[i][j] = tmp.charAt(j);
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        Map<String, Integer> set = new HashMap();

        for (int i = 0; i < k; i++) {
            String tmp = br.readLine();
            if (set.containsKey(tmp)) {
                bw.write("" + set.get(tmp));
                bw.newLine();
                answer = 0;
                continue;
            }
            for (Pair pair : findStart(tiles, tmp.charAt(0))) {
                queue.clear();
                queue.add(pair);
                for (int j = 1; j < tmp.length(); j++) {
                    int queueSize = queue.size();
                    for (int o = 0; o < queueSize; o++) {
//                        System.out.println(queue.size());
                        Pair tmpPair = queue.poll();
                        for (int l = 0; l < 8; l++) {
                            int rowIdx = (tmpPair.row + dx[l] + n) % n;
                            int colIdx = (tmpPair.col + dy[l] + m) % m;

                            if (tiles[rowIdx][colIdx] != tmp.charAt(j)) {
                                continue;
                            }
                            queue.add(new Pair(rowIdx, colIdx));
                        }
                    }
                }
                answer += queue.size();
            }

            bw.write("" + answer);
            set.put(tmp, answer);
            bw.newLine();
            answer = 0;
        }

        bw.close();
    }

    private static List<Pair> findStart(int[][] tiles, char startChar) {
        List<Pair> result = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (startChar == tiles[i][j]) result.add(new Pair(i, j));
            }
        }

        return result;
    }


}

class Pair {
    int row;
    int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "[" + row + ", " + col + "]";
    }
}