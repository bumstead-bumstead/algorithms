package sihum;

public class lexicorgrapical {
    public static void main(String[] args) {
        recurs(5);
    }

    private static void recurs(int n) {
        System.out.println(n);
        if (n == 0) {
            return;
        }
        recurs(n - 1);
    }

    private static void partialSum(int start, int n, int total) {

    }
}
