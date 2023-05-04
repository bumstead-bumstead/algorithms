package programmers;

public class Fatigue {
    class Solution {
        static boolean[] visited;
        static int[][] dungeons2;
        static int maximum = 0;
        public int solution(int k, int[][] dungeons) {
            dungeons2 = dungeons;
            visited = new boolean[dungeons.length];

            searchAllTheNumberOfCase(0, k);

            return maximum;
        }

        public static void searchAllTheNumberOfCase(int depth, int tmpFatigue) {
            maximum = Math.max(maximum, depth);
            for (int i = 0; i < dungeons2.length; i++) {
                int leastRequiredFatigue = dungeons2[i][0];
                int consumes = dungeons2[i][1];

                if (visited[i] || tmpFatigue < leastRequiredFatigue) continue;
                visited[i] = true;
                searchAllTheNumberOfCase(depth+1, tmpFatigue - consumes);
                visited[i] = false;
            }


        }
    }
}
