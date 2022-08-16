package linkedList;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BOJ1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        List<Character> result = new LinkedList<>();
        for (char x : str.toCharArray()) {
            result.add(x);
        }
//        int cursor = str.length();
        ListIterator<Character> cursor = result.listIterator();
        while (cursor.hasNext()) cursor.next();

        int commandNum = Integer.parseInt(br.readLine());
        String[] coms = new String[commandNum];

        char tmpCom;
        char tmpChar;
        for (int i = 0; i < commandNum; i++) {
            coms[i] = br.readLine();
        }
        for (String curCom : coms) {
            System.out.println("현재 문자열 : " + result.toString());
            System.out.println("현재 커서 위치 : " + cursor);
            System.out.println("현재 명령 : " + curCom);

            tmpCom = curCom.charAt(0);
            if (tmpCom == 'L') {
                if (cursor.hasPrevious()) cursor.previous();
            } else if (tmpCom == 'D') {
                if (cursor.hasNext()) cursor.next();
            } else if (tmpCom == 'B') {
                if (!cursor.hasPrevious()) continue;
                cursor.previous();
                cursor.remove();
            } else {
                tmpChar = curCom.charAt(2);
                cursor.add(tmpChar);

            }
            System.out.println("-----------------------");
        }

        Iterator<Character> iterator = result.iterator();

        while (iterator.hasNext()) {
            bw.write(iterator.next());
        }

        bw.close();
    }
}
