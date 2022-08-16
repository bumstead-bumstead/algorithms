package linkedList;

import java.io.*;
import java.util.*;


public class BOJ5397 {
    public static void main(String[] args) throws IOException {

        //linkedList와 listIterator 활용한 방법, 시간초과
//        int testCaseNum = Integer.parseInt(br.readLine());
//        String[] testCase = new String[testCaseNum];
//
//        for (int i = 0; i < testCaseNum; i++) {
//            testCase[i] = br.readLine();
//        }
//
//        List<Character> tmpPassword;
//        ListIterator<Character> tmpIterator;
//        String answer;
//
//        for (int i = 0; i < testCase.length; i++) {
//            tmpPassword = new ArrayList<>();
//            tmpIterator = tmpPassword.listIterator();
//            answer = "";
//            for (char x : testCase[i].toCharArray()) {
//                if (x == '<') {
//                    if (tmpIterator.hasPrevious()) tmpIterator.previous();
//                } else if (x == '>') {
//                    if (tmpIterator.hasNext()) tmpIterator.next();
//                } else if (x == '-') {
//                    if (tmpIterator.hasPrevious()) {
//                        tmpIterator.previous();
//                        tmpIterator.remove();
//                    }
//                } else {
//                    tmpIterator.add(x);
//                }
//            }
//
//            for (Character x : tmpPassword) {
//                answer += x;
//            }
//            bw.write(answer);
//            bw.newLine();
//        }


        //스택 활용한 방법
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseNum = Integer.parseInt(br.readLine());
        String[] testCase = new String[testCaseNum];

        for (int i = 0; i < testCaseNum; i++) {
            testCase[i] = br.readLine();
        }


        Stack<Character> leftOfCursor = new Stack<>();
        Stack<Character> rigthOfCursor = new Stack<>();

        String answer;

        for (int i = 0; i < testCase.length; i++) {
            answer = "";
            for (char x : testCase[i].toCharArray()) {
                if (x == '<') {
                    if (!leftOfCursor.isEmpty()) {
                        rigthOfCursor.push(leftOfCursor.pop());
                    }
                } else if (x == '>') {
                    if (!rigthOfCursor.isEmpty()){
                        leftOfCursor.push(rigthOfCursor.pop());
                    }
                } else if (x == '-') {
                    if (!leftOfCursor.isEmpty()) {
                        leftOfCursor.pop();
                    }
                } else leftOfCursor.push(x);
            }


            StringBuilder pwd = new StringBuilder();

            while (!leftOfCursor.isEmpty())  {
                rigthOfCursor.push(leftOfCursor.pop());
            }
            while (!rigthOfCursor.isEmpty()) {
                pwd.append(rigthOfCursor.pop());
            }

            System.out.println(pwd);
        }
    }
}
