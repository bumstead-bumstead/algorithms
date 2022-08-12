package prts;

import java.io.*;
import java.util.Arrays;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] arr = br.readLine().split(" ");

        int n = Integer.parseInt(arr[0]);
        int x = Integer.parseInt(arr[1]);

        arr = br.readLine().split(" ");

        for (String tmp : arr) {
            if (Integer.parseInt(tmp) < x) {
                bw.write(tmp + " ");
            }
        }
        bw.close();
    }
}
