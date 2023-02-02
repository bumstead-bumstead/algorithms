package binarySearchTree;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;


//전체 TreeSet과, 알고리즘 분류 별로 저장한 HashMap<Integer, TreeSet> 따로 유지?? ( + 문제 전부 유지하는 HashMap<문제번호, problem2>
public class BOJ21944 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        TreeSet<Problem2> problemTree = new TreeSet<>();
        HashMap<Integer, TreeSet<Problem2>> classifiedProblem = new HashMap<>();
        HashMap<Integer, Problem2> problems = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            Problem2 tmpProblem = new Problem2(p, l, g);

            problemTree.add(tmpProblem);
            if (classifiedProblem.get(g) == null) classifiedProblem.put(g, new TreeSet<>());
            classifiedProblem.get(g).add(tmpProblem);

            problems.put(p, tmpProblem);
        }

        int M = Integer.parseInt(br.readLine());
        Problem2 pr;

        for (int i = 0; i < M; i++) {
            String[] tmpCommand = br.readLine().split(" ");
            switch (tmpCommand[0]) {
                case "recommend" :
                    if (tmpCommand[2].equals("1")) bw.write(classifiedProblem.get(Integer.parseInt(tmpCommand[1])).last().number + "");
                    else bw.write(classifiedProblem.get(Integer.parseInt(tmpCommand[1])).first().number + "");
                    bw.newLine();
                    break;
                case "recommend2" :
                    if (tmpCommand[1].equals("1")) bw.write(problemTree.last().number + "");
                    else bw.write(problemTree.first().number + "");
                    bw.newLine();
                    break;
                case "recommend3" : //틀림
                    if (tmpCommand[1].equals("1")) {
                        pr = problemTree.higher(new Problem2(0, Integer.parseInt(tmpCommand[2]), 0));
                        if (pr != null) bw.write(pr.number + "");
                        else bw.write("-1");
                    } else {
                        pr = problemTree.lower(new Problem2(0, Integer.parseInt(tmpCommand[2]), 0));
                        if (pr != null) bw.write(pr.number + "");
                        else bw.write("-1");
                    }
                    bw.newLine();
                    break;
                case "add" :
                    pr = new Problem2(Integer.parseInt(tmpCommand[1]), Integer.parseInt(tmpCommand[2]), Integer.parseInt(tmpCommand[3]));
                    problemTree.add(pr);
                    if (classifiedProblem.get(pr.division) == null) classifiedProblem.put(pr.division, new TreeSet<>());
                    classifiedProblem.get(pr.division).add(pr);
                    problems.put(pr.number, pr);
                    break;
                case "solved" :
                    pr = problems.remove(Integer.parseInt(tmpCommand[1]));
                    problemTree.remove(pr);
                    classifiedProblem.get(pr.division).remove(pr);
                    break;
            }
        }
        bw.close();
    }
}

class Problem2 implements Comparable<Problem2> {
    public int number;
    public int difficulty;
    public int division;

    public Problem2(int number, int difficulty, int division) {
        this.number = number;
        this.difficulty = difficulty;
        this.division = division;
    }

    @Override
    public int compareTo(Problem2 problem) {
        if (problem.difficulty == this.difficulty) return this.number - problem.number;
        return this.difficulty - problem.difficulty;
    }
}
