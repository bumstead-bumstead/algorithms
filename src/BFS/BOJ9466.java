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
* https://bcp0109.tistory.com/32*/


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
        for (int i = 0; i < testCase; i++) {
            int studentN = Integer.parseInt(br.readLine());
            int[] students = new int[studentN];
            boolean[] made = new boolean[studentN];
            boolean[] visited = new boolean[studentN];

            int cnt = 0;

            String[] a = br.readLine().split(" ");

            for (int j = 0; j < studentN; j++) {
                students[j] = Integer.parseInt(a[j]) - 1;
            }

            for (int j = 0; j < studentN; j++) {
                System.out.println(j + "번째 원소 검사 ---------------------");
                if (made[j]) continue;
                Student tmp = new Student(j, students[j]);
                visited[tmp.num] = true;

                if (tmp.choice == tmp.num) {
                    made[j] = true;
                    continue;
                }

                while (true) {
                    tmp = new Student(tmp.choice, students[tmp.choice]);
                    visited[tmp.num] = true;

                    if (visited[tmp.choice]) {
                        while (!made[tmp.num]) {
                            made[tmp.num] = true;
                            tmp = new Student(tmp.choice, students[tmp.choice]);
                        }
                        break;
                    }
                }
            }

            System.out.println(Arrays.toString(made));
            for (int j = 0; j < studentN; j++) {
                if (!made[j]) cnt++;
            }
            System.out.println(cnt);

        }

    }
}
