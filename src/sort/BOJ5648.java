package sort;


import java.io.*;
import java.util.*;

//radix sort 활용
public class BOJ5648 {
    public static void main(String[] args) throws IOException {
//        int[] a = new int[]{1601, 90100, 13009, 802, 301, 210};
        Scanner sc = new Scanner(System.in);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(sc.next());
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
//            if (!st.hasMoreTokens()) {
//                st = new StringTokenizer(br.readLine());
//            }
            arr[i] = reverseNum(sc.next());
        }
//        reverseRadix(arr);
        Arrays.sort(arr);
        for (long x : arr) {
            bw.write(x + " ");
        }
        bw.close();
    }

    private static long reverseNum(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length -1; i >= 0 ; i--) {
            sb.append(chars[i]);
        }
        return Long.parseLong(sb.toString());
    }

    private static void reverseRadix(long[] arr) {
        List<Long>[] list = new List[10];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        int n = 10;

        for (int i = 0; i < 13; i++) {
            for (long x : arr) {
//                System.out.println("n = " + n);
//                System.out.println("x = " + x);
//                list[(x - x % (n/10))% n].add(x);
                list[(int) x % n / (n / 10)].add(x);
            }

            int cnt = 0;

            for (List<Long> x : list) {
                for (long y : x) {
                    arr[cnt++] = y;
                }
                x.clear();
            }
            n = n*10;
        }
    }

}
