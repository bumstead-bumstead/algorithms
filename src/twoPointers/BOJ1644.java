package twoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(0);
            return;
        }
        if (n == 2) {
            System.out.println(1);
            return;
        }
        
        
        //이거 절반으로 쌔리면 왜안되는지 나는 당췌 모르ㅔㄱㅆ다
        boolean[] isNotSosu = new boolean[n + 1];
        isNotSosu[0] = true;
        isNotSosu[1] = true;
        for (int i = 2; i < isNotSosu.length; i++) {
            if (isNotSosu[i]) continue;
            for (int j = i * 2; j < isNotSosu.length; j += i) {
                isNotSosu[j] = true;
            }
        }

        List<Integer> Sosus = new ArrayList<>();

        for (int i = 2; i < isNotSosu.length; i++) {
            if (isNotSosu[i]) continue;
            Sosus.add(i);
        }

        int left = 0;
        int right = 0;
        int answer = 0;
        int tmpSum = 2;

        while (right < Sosus.size() - 1) {
            if (tmpSum == n) {
                answer++;
                tmpSum += Sosus.get(++right);
                tmpSum -= Sosus.get(left++);
            } else if (tmpSum < n) tmpSum += Sosus.get(++right);
            else {
                tmpSum -= Sosus.get(left++);
                if (left > right) break;
            }
        }

        while (tmpSum >= n || left <= right) {
            if (tmpSum == n) {
                answer++;
                break;
            }
            tmpSum -= Sosus.get(left++);
        }

        System.out.println(answer);
    }

    private static boolean isSosu(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
