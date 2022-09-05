package sort;

public class Quick {
    private static void sort(int[] arr, int r, int l) {
        if (r >= l) return;

        int mid = partition(arr, r, l);
        sort(arr, r, mid - 1);
        sort(arr, mid, l);
    }

    private static int partition(int[] arr, int r, int l) {
        int p = arr[(r + l) / 2];
        while (r <= l) {
            while (arr[r] < p) r++;
            while (arr[l] > p) l--;
            if (r <= l) {
                swap(arr, r, l);
                r++;
                l--;
            }
        }
        return r;
    }

    private static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
