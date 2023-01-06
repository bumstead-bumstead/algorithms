package binarySearchTree;


import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class BOJ21939 {
    static TreeSet<Problem> recommends;
    static HashMap<Integer, Integer> problems = new HashMap<>(); //<번호, 난이도>

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        recommends = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
//            System.out.println(p + "," + l);
            recommends.add(new Problem(p, l));
            problems.put(p, l);
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            String[] tmpCommand = br.readLine().split(" ");
            switch (tmpCommand[0]) {
                case "add" :
                    int p = Integer.parseInt(tmpCommand[1]);
                    int l = Integer.parseInt(tmpCommand[2]);
                    recommends.add(new Problem(p, l));
                    problems.put(p, l);
                    break;
                case "recommend" :
                    if (tmpCommand[1].equals("1")) {
                        bw.write(""+recommends.last().number);
                    } else {
                        bw.write(""+recommends.first().number);
                    }
                    bw.newLine();
                    break;
                case "solved" :
                    p = Integer.parseInt(tmpCommand[1]);
                    recommends.remove(new Problem(p, problems.get(p)));
                    break;
            }
        }

        bw.close();
    }
}

class Problem implements Comparable<Problem> {
    int difficulty;
    int number;

    public Problem(int number, int difficulty) {
        this.difficulty = difficulty;
        this.number = number;
    }

    @Override
    public int compareTo(Problem problem) {
        if (problem.difficulty == this.difficulty) {
            return this.number - problem.number;
        }
        return this.difficulty - problem.difficulty;
    }

    @Override
    public boolean equals(Object object) {
        Problem problem = (Problem) object;
        return problem.number == this.number;
    }

    @Override
    public String toString() {
        return "[" + number + ", " + difficulty + "]";
    }
}