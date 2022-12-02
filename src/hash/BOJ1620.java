package hash;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashMap<String , Integer> map = new HashMap<>();
        String[] arr = new String[n + 1];

        for (int i = 1; i < n + 1; i++) {
            String tmp = br.readLine();
            map.put(tmp, i);
            arr[i] = tmp;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < k; i++) {
            String tmp = br.readLine();
            if (tmp.charAt(0) >= '0' && tmp.charAt(0) <= '9') {
                bw.write(arr[Integer.parseInt(tmp)]);
            } else {
                bw.write(map.get(tmp)+"");
            }
            bw.newLine();
        }
        bw.close();

    }
}
