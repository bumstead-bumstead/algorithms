package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 1. (자기 번호, 상대 번호) pair 형태로 모든 학생에 대해서 검사 - Sn
* 2. 학생이 지목한 학생을 다시 queue에 저장 -> 반복
* 3. 반복 중에 Sn이 다시 나오면, 지나왔던 애들 모두 visited에 true, break
* 3-1. Sn이 안나오고 while문 빠져나오면, cnt++, visited에 true
* */


//dfs할때 다시풀자!!!!!!
class Student {
    int num;
    int choice;

    public Student(int num, int choice) {
        this.choice = choice;
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        Student student = (Student) o;
        return num == student.num && choice == student.choice;
    }

}

public class BOJ9466 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        long start = System.currentTimeMillis();
        for (int i = 0; i < testCase; i++) {
            int studentN = Integer.parseInt(br.readLine());
            int[] students = new int[studentN];
            boolean[] made = new boolean[studentN];
            int cnt = 0;

            String[] a = br.readLine().split(" ");

            for (int j = 0; j < studentN; j++) {
                students[j] = Integer.parseInt(a[j]) - 1;
            }
            System.out.println(Arrays.toString(students));

            a : for (int j = 0; j < studentN; j++) {
                if (made[j]) continue;
                if (j == students[j]) continue;
                System.out.println(j + " 학생 검사 시작");
                Queue<Student> queue = new LinkedList<>();
                List<Integer> tmpList = new ArrayList<>();
                tmpList.add(j); //검사하는 놈
                queue.offer(new Student(students[j], students[students[j]])); //검사하는 놈이 지목한놈

                while (!queue.isEmpty()) {
                    Student tmp = queue.poll();

//                    if (tmp.choice == tmp.num) {
//                        made[tmp.num] = true;
//                        break;
//                    }
                    if (made[tmp.choice]) break;

                    tmpList.add(tmp.num);

                    //시간복잡도.....
//                    if (tmpList.size() > studentN) break;
                    int idx = tmpList.indexOf(tmp.choice);

                    if (idx != -1) {
                        System.out.println("made : " + tmpList);
                        for (int k = idx; k < tmpList.size(); k++) {
                            made[tmpList.get(k)] = true;
                        }
                        if (idx != 0) cnt++;
                        continue a;
                    }


                    queue.offer(new Student(tmp.choice, students[tmp.choice]));
                }
                cnt++;
            }
            System.out.println(cnt);
            System.out.println("-------------------------");
        }
        long end = System.currentTimeMillis();
        System.out.println("시간 : " + (end-start));
    }
}
