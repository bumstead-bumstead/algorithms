package dynamic;


import java.util.Arrays;
import java.util.Scanner;

/*
* 1~26
* d[i] = i자리까지의 수를 해석하는 경우의 수
* d[1] = 1;
* d[k] = d[k-1] + (d[k-2] (d[k-2] d[k-1]이 26보다 작을 때))
* */
public class BOJ2011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str = sc.nextLine().toCharArray();
        char[] arr = new char[str.length+1];
        for (int i = 1; i < str.length + 1; i++) {
            arr[i] = str[i-1];
        }

        int[] d = new int[arr.length];
        d[0] = 1;
        d[1] = 1;
        if (arr[1] == '0') {
            System.out.println(0);
            return;
        }

        for (int i = 2; i < arr.length; i++) {
            int tmp1 = arr[i] - '0'; //1의 자리
            int tmp2 = arr[i-1] - '0'; //10의 자리

            if (tmp1 == 0 && tmp2 == 0) {
                System.out.println(0);
                return;
            }

            if (tmp1 != 0) d[i] += d[i - 1];
            if (tmp2 != 0 && tmp2*10 + tmp1 <= 26) d[i] += d[i - 2];

            d[i] %= 1000000;
        }

        System.out.println(d[arr.length-1]);
    }
}
// 잘못된 암호 : 1. 시작이 0인 경우, 2. 0이 두번 연속되는 경우