package sort;

import java.io.*;
import java.util.Arrays;


//+stable sort!!!!
public class BOJ2751 {
    static int[] test;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());


        test = new int[n];

        for (int i = 0; i < n; i++) {
            test[i] = Integer.parseInt(br.readLine());
        }


        mergeSort(0, test.length);

        for (int x : test) {
            bw.write(x +"");
            bw.newLine();
        }
        bw.close();
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
            else if (front[pf] < back[pb]) test[st + pf + pb] = front[pf++]; //이렇게 하면 stable Sort가 아니게 된다. 같을 때도 front를 먼저 보내줘야댐!!
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
