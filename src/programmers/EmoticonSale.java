package programmers;

public class EmoticonSale {

    /*
    1. 모든 emoticions 원소에 대해 모든 할인율 적용 경우의 수 계산
     -> 각 idx 별로 할인율 나타내는 배열 만들기
    2. 이 각각의 경우의 수에 대해서, 구매 비용, 플러스 가입 수 계산
    */
    static int[] discount = new int[]{0, 10, 20, 30, 40};
    static int[][] staticUsers;
    // static int[] discountList;
    static int[] staticEmoticons;
    static int sells = 0;
    static int join = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        staticUsers = users;
        staticEmoticons = emoticons;
        discount(0, new int[emoticons.length]);
        return new int[]{join, sells};
    }

    public static void discount(int depth, int[] discountList) {
        if (depth == discountList.length) {
            int[] joinAndSells = getNumberOfJoinAndSells(discountList);
            int tmpJoin = joinAndSells[0];
            int tmpSells = joinAndSells[1];
            if (tmpJoin > join) {
                join = tmpJoin;
                sells = tmpSells;
            } else if (tmpJoin == join) sells = Math.max(sells, tmpSells);

            return;
        }

        for (int i = 0; i < 50; i += 10) {
            discountList[depth] = i;
            discount(depth + 1, discountList);
        }

    }

    public static int[] getNumberOfJoinAndSells(int[] discountList) {
        int join = 0;
        int sell = 0;
        for (int[] user : staticUsers) {
            int tempSum = 0;
            for (int i = 0; i < discountList.length; i++) {
                if (discountList[i] < user[0]) continue;
                tempSum += staticEmoticons[i] * (100 - discountList[i]) / 100;
            }
            if (tempSum >= user[1]) join++;
            else sell += tempSum;
        }
        return new int[]{join, sell};
    }
}

