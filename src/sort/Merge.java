package sort;

import java.util.Arrays;

public class Merge {
    static int[] test;
    public static void main(String[] args) {
//        int[] a = {-9, 1, 6, 8, 12};
//        int[] b = {-7, 7, 13, 15};
//        int pa  = 0;
//        int pb = 0;
//
//        int[] c = new int[a.length + b.length];
//
//        for (int i = 0; i < c.length; i++) {
//            if (pa >= a.length) c[pb + pa] = b[pb++];
//            else if (pb >= b.length) c[pb + pa] = a[pa++];
//            else if (a[pa] > b[pb]) {
//                c[pb + pa] = b[pb++];
//            }
//            else {
//                c[pb + pa] = a[pa++];
//            }
//        }

        test = new int[]{-1, 1, 5, -3, -15, 15, 35, 12,13,17,0};
        mergeSort(0, test.length);

        System.out.println(Arrays.toString(test));
    }

    //en-st==2 일 때 st~mid, mid~en이 각각 하나씩 언소를 가진당

    //st~mid, mid~en이 각각 정렬되어있을 때 증렬~
    private static void merge(int st, int en) {
        int mid = (st + en) / 2;
        int pf = 0;
        int pb = 0;

        int[] front = Arrays.copyOfRange(test, st, mid);
        int[] back = Arrays.copyOfRange(test, mid, en);

        for (int i = 0; i < en - st; i++) {
            if (pf >= front.length) test[st + pf + pb] = back[pb++];
            else if (pb >= back.length) test[st + pf + pb] = front[pf++];
            else if (front[pf] < back[pb]) test[st + pf + pb] = front[pf++];
            else test[st + pf + pb] = back[pb++];
        }
    }

    private static void mergeSort(int st, int en) {
        if (en - st == 1) return;
        int mid = (st + en) / 2;

        mergeSort(st, mid);
        mergeSort(mid, en);
        merge(st, en);
    }
}
