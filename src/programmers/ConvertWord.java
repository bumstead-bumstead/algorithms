package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class ConvertWord {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }

    private int bfs(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);
        int cnt = 0;

        while(!queue.isEmpty()) {
            int reps = queue.size();


            for (int j = 0; j < reps; j++) {
                String tmpWord = queue.poll();

                for(int i=0; i<words.length; i++) {
                    if (tmpWord.equals(target)) return cnt;
                    if (visited[i] || !isConvertible(words[i], tmpWord)) continue;

                    queue.offer(words[i]);
                    visited[i] = true;
                }
            }
            cnt++;
        }
        return 0;
    }


    private boolean isConvertible(String s1, String s2) {
        int cnt = 0;
        for(int i=0; i<s1.length(); i++){
            if (s1.charAt(i) == s2.charAt(i)) cnt++;
        }

        if (cnt == s1.length() - 1) return true;
        else return false;
    }
}