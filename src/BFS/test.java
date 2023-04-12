package BFS;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4};

        a = Arrays.stream(a).map(x -> 2*x).toArray();

        System.out.println(Arrays.toString(a));

    }
}
