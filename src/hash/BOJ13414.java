package hash;


import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
* v
* */
public class BOJ13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Set<String > set = new LinkedHashSet<>();

        for (int i = 0; i < l; i++) {
            String temp = br.readLine();
            if (set.contains(temp)) set.remove(temp);
            set.add(temp);
        }

        Iterator<String> iterator = set.iterator();

//        System.out.println(set);
        for (int i = 0; i < k; i++) {
            if (!iterator.hasNext()) break;
            bw.write(iterator.next());
            bw.newLine();
        }
        bw.close();
    }
}
