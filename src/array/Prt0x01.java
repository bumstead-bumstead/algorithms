package array;

//배열로 시간복잡도를 줄이는 예제...
public class Prt0x01{
    public int func2(int[] arr, int N) {
        int[] idxArr = new int[101];

        for (int x : arr) {
            idxArr[x]++;
        }
        for (int x : arr) {
            if (idxArr[100-x] == 1) return 1;
        }
        return 0;

    }
}
