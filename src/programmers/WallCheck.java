package programmers;

import java.util.ArrayList;
import java.util.List;

public class WallCheck {
    int[] weak;
    int[] checkedList;
    int[] dist;
    int answer = Integer.MAX_VALUE;
    int n;

    public int solution(int n, int[] weak, int[] dist) {
        this.weak = weak;
        this.dist = dist;
        this.n = n;
        checkedList = new int[weak.length];

        DFS(0);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void DFS(int depth) {
        if (hasAllChecked()) {
            answer = Math.min(answer, depth);
            return;
        }
        if (depth == dist.length || depth >= answer) return;

        int tmpDist = dist[dist.length - depth - 1];

        if (tmpDist >= n) {
            answer = Math.min(answer, depth + 1);
            return;
        }
        //Todo : weak[j]부터 tmpDist 만큼 검사하고 (checked 채우고) 다음 DFS
        for (int i = 0; i < weak.length; i++) {
            if (checkedList[i] == 1) continue;

            List<Integer> changedIdx = check(tmpDist, i);
            DFS(depth + 1);
            uncheck(changedIdx);
        }
    }

    private void uncheck(List<Integer> changedIdx) {
        for (Integer idx : changedIdx) {
            checkedList[idx] = 0;
        }
    }

    private List<Integer> check(int tmpDist, int weakIdx) {
        List<Integer> changedIdx = new ArrayList<>();
        int end = (weak[weakIdx] + tmpDist) % n; //검사할 마지막 position

        if (end < weak[weakIdx]) { //weakIdx 이후 인덱스 모두 true, 0부터 end까지 모두 true
            for (int i = weakIdx; i < weak.length; i++) {
                if (checkedList[i] == 1) continue;
                changedIdx.add(i);
                checkedList[i] = 1;
            }
            for (int i = 0; i < weak.length; i++) {
                if (weak[i] > end) break;
                if (checkedList[i] == 1) continue;
                changedIdx.add(i);
                checkedList[i] = 1;
            }
        } else { //tmpDist부터 end까지 모두 true
            for (int i = weakIdx; i < weak.length; i++) {

                if (weak[i] > end) break;
                if (checkedList[i] == 1) continue;
                changedIdx.add(i);

                checkedList[i] = 1;
            }
        }
        return changedIdx;
    }


    private boolean hasAllChecked() {
        for (int checked : checkedList) {
            if (checked == 0) return false;
        }
        return true;
    }

}
