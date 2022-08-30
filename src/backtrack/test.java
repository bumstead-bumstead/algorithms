package backtrack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
    int x;
    int y;

    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class test {



        static String[][] map;
        static boolean[] visited;
        static int cnt = 0;
        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            map = new String[5][5];
            for(int i=0; i<5; i++){
                String[] temp = br.readLine().split("");
                for(int j=0; j<5; j++){
                    map[i][j] = temp[j];
                }
            }
            visited = new boolean[25];
            // 25개중 7개를 선택하는 조합
            comb(0, 7);
            System.out.println(cnt);
        }

        private static void comb(int start, int r){
            if(r == 0){
                int num = 0;
                int temp = 0;
                int x = 0;
                int y = 0;
                int[][] map2 = new int[5][5];	// 선택한 자리
                for(int i=0; i<25; i++){
                    // row와 column으로 변환
                    int row = i/5;
                    int col = i % 5;
                    if(visited[i]){
                        map2[row][col] = 1;	// 자리 선택
                        if(temp == 0){
                            x = row;
                            y = col;
                        }

                        // 이다솜파 몇명이 선택되었는지 세기
                        if(map[row][col].equals("S"))
                            num++;
                        temp++;	// 7명 모두 골랐다면 빠른 탈출
                    }
                    if(temp == 7)
                        break;
                }

                // 이다솜파 4명이상 선택되었다면 BFS로 연결되어 있는지 탐색
                if(num >= 4){
                    bfs(x,y,map2);
                }

                return;
            }

            for(int i=start; i<25; i++){
                if(!visited[i]){
                    visited[i] = true;
                    comb(i+1, r-1);
                    visited[i] = false;
                }
            }
        }

        private static void bfs(int a, int b, int[][] arr){
            Queue<Pos> q = new LinkedList<>();
            boolean[][] visited = new boolean[5][5];
            int[] xdir = {-1,1,0,0};
            int[] ydir = {0,0,-1,1};

            q.offer(new Pos(a,b));
            visited[a][b] = true;
            int num = 1;

            while(!q.isEmpty()){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                for(int i=0; i<4; i++){
                    int dx = x + xdir[i];
                    int dy = y + ydir[i];
                    // 유효한 위치 && 선택된 자리 && 방문하지 않은 자리
                    if(isValidPosition(dx, dy) && arr[dx][dy] == 1 && !visited[dx][dy]){
                        q.offer(new Pos(dx, dy));
                        visited[dx][dy] = true;
                        num++;
                    }
                }
            }

            // 모두 연결 되어있다면 num == 7
            if(num == 7)
                cnt++;
        }
        private static boolean isValidPosition(int x, int y){
            if(x < 0 || y < 0 || x >= 5 || y >= 5) return false;
            return true;
        }
}
