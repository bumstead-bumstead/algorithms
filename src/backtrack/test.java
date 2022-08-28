package backtrack;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] a = {1, 2};
        int[] b = {1, 2};
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(Arrays.equals(a, b));
    }
}
