package sort;


import java.io.*;

//counting sort 밤마느레 펄~ - 대상이되는 수의 범위가 적을 때 범위크기의 배열을 만들어서 다넣음 -> 배열 단원에서 활용한 개념 그대로 이용
//comparison, non comparison 정렬의 차이 확인
public class BOJ15688 {
    static int n;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        countingSort(arr);
        bw.close();
    }

    private static void countingSort(int[] arr) throws IOException {
        int[] nNums = new int[2000001];

        for (int i = 0; i < n; i++) {
            nNums[arr[i]+1000000]++;
        }

        for (int i = 0; i < nNums.length; i++) {
            for (int j = 0; j < nNums[i]; j++) {
                bw.write((i - 1000000 + ""));
                bw.newLine();
            }
        }
    }
}
