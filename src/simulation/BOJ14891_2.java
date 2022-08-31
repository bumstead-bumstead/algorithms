package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//극이 다르면 돌고 같으면 안돈다..
// -> 돌고나서가 아니라 돌기 전에 같은지 다른지를 말하는거임!!!!!!!!!!!!
public class BOJ14891_2 {
    static LinkedList<Integer>[] gears = new LinkedList[5];
    static int[] dx = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i < 5; i++) {
            char[] tmp = br.readLine().toCharArray();
            gears[i] = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                gears[i].offer(tmp[j] - '0');
            }
        }

        int n = Integer.parseInt(br.readLine());
        Queue<GearSecond> willBeRotate = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            willBeRotate.offer(new GearSecond(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            boolean[] visited = new boolean[4];
            visited[willBeRotate.peek().idx-1] = true;
//            System.out.println("-----------------------");
            while (!willBeRotate.isEmpty()) {

                GearSecond tmpGear = willBeRotate.poll();
//                System.out.println(tmpGear.idx + "번 톱니바퀴 " + tmpGear.dir + "방향으로 돌아간다잉");
//                System.out.println("돌기 전 : " + gears[tmpGear.idx]);

//                System.out.println("돌고 나서  : " + gears[tmpGear.idx]);

                for (int j = 0; j < 2; j++) {
                    int tmpIdx = tmpGear.idx + dx[j];

                    if (tmpIdx < 1 || tmpIdx > 4 || visited[tmpIdx-1]) continue;

                    if (dx[j] == 1) {
                        if (gears[tmpGear.idx].get(2) != gears[tmpIdx].get(6)) {
                            willBeRotate.offer(new GearSecond(tmpIdx, (-1)* tmpGear.dir));
                            visited[tmpIdx - 1] = true;
                        }
                    } else {
                        if (gears[tmpGear.idx].get(6) != gears[tmpIdx].get(2)) {
                            willBeRotate.offer(new GearSecond(tmpIdx, (-1)* tmpGear.dir));
                            visited[tmpIdx-1] = true;
                        }
                    }
                }
                rotate(gears[tmpGear.idx], tmpGear.dir);
            }
//            for (int k = 1; k < 5; k++) {
//                System.out.println(gears[k]);
//            }
        }
//        for (int i = 1; i < 5; i++) {
//            System.out.println(gears[i]);
//        }
        int answer = 0;
        answer += gears[1].peek() == 0 ? 0 : 1;
        answer += gears[2].peek() == 0 ? 0 : 2;
        answer += gears[3].peek() == 0 ? 0 : 4;
        answer += gears[4].peek() == 0 ? 0 : 8;

        System.out.println(answer);

    }

    private static void rotate(LinkedList<Integer> gear, int dir) {
        if (dir == -1) { //반시계 방향이면, 앞에가 맨뒤로
//            System.out.println("반시곙~");
            gear.offer(gear.poll());
        } else { //시계방향이면, 맨뒤가 맨앞으로
//            System.out.println("시곙~");
            gear.offerFirst(gear.pollLast());
        }
    }
}
