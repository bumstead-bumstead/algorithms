package programmers;

import java.util.*;

public class Journey {
    static Map<String, List<Integer>> map; //각 출발지 별 티켓의 index
    static boolean[] visited;
    static List<String> answerList;
    static List<String> tmpRoute;

    public static String[] solution(String[][] tickets) {
        map = new HashMap<>();
        visited = new boolean[tickets.length];
        answerList = new ArrayList<>();
        tmpRoute = new ArrayList<>();

        for (int i=0; i<tickets.length; i++) {
            String[] ticket = tickets[i];

             if (!map.containsKey(ticket[1])) {
                 map.put(ticket[1], new ArrayList<>());
             }

            if (!map.containsKey(ticket[0])) {
                List<Integer> tmpList = new ArrayList<>();
                tmpList.add(i);
                map.put(ticket[0], tmpList);
            } else {
                map.get(ticket[0]).add(i);
            }
        }

        tmpRoute.add("ICN");
        dfs("ICN", tickets, 1);
        return answerList.toArray(new String[answerList.size()]);
    }

    private static void dfs(String tmpLocation, String[][] tickets, int depth) {
        if (depth == tickets.length+1) {
            answerList = compareRoutes(tmpRoute);
        }


        for (Integer next : map.get(tmpLocation)) {
            if (visited[next]) continue;

            visited[next] = true;
            tmpRoute.add(tickets[next][1]);
            dfs(tickets[next][1], tickets, depth + 1);
            tmpRoute.remove(tmpRoute.size() - 1);
            visited[next] = false;
        }
    }

    private static List<String> compareRoutes(List<String> tmpRoute) {
        if (answerList.size() == 0) return new ArrayList<>(tmpRoute);

        for (int i = 0; i < tmpRoute.size(); i++) {
            if (tmpRoute.get(i).compareTo(answerList.get(i)) == 0) continue;

            if (tmpRoute.get(i).compareTo(answerList.get(i)) > 0) {
                return answerList;

            }
            else return new ArrayList<>(tmpRoute);
        }

        return answerList;
    }

    public static void main(String[] args) {
        String[][] a = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[][] b = new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(Arrays.toString(solution(b)));
    }
}
