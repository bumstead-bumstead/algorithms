package sort;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//정렬을 활용하는 방법 - O(nlongn)
public class BOJ11652_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));

        int maxRep = -1;
        long maxNumber = Long.MIN_VALUE;
        int tmpRep = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i-1] == arr[i]) {
//                System.out.println(arr[i] + " 반복");
                tmpRep++;
            } else {
                if (maxRep < tmpRep) {
//                    System.out.println("maxRep = " + maxRep);
//                    System.out.println("tmpRep = " + tmpRep);
                    maxNumber = arr[i-1];
                    maxRep = tmpRep;
                }
                tmpRep = 1;
            }
        }

        if (tmpRep > maxRep) {
//            System.out.println(tmpRep);
//            System.out.println(maxRep);
            maxNumber = arr[n-1];
        }

        System.out.println(maxNumber);

    }
}
