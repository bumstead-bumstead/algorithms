package hash;


import java.util.Arrays;

//key가 String인 hash~
public class myHashSet {
    int M = 1000003; //원소 수
    int MX = 500005; //최대 입력 개수
    int[] head = new int[M]; //각 해시값의 첫번째 원소 -> "몇 번째로 삽입한 데이터인지"가 나오는 거~
    int[] next = new int[MX]; //각 해시값의 다음 원소 idx
    int[] prev = new int[MX]; //각 해시값의 이전 idx
    int[] values = new int[MX];
    String[] keys = new String[MX];
    int unused = 0;

    public myHashSet() {
        Arrays.fill(head, -1);
        Arrays.fill(next, -1);
        Arrays.fill(prev, -1);
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (hash * 1000 + key.charAt(i)) % M;
        }
        return hash;
    }

    public int find(String key) {
        int h = hash(key);
        if (head[h] == -1) return -1;

        int tmp = head[h];
        if (keys[tmp].equals(key)) return tmp;

        while (next[tmp] != -1) {
            if (keys[next[tmp]].equals(key)) {
                return tmp;
            }
            tmp = next[tmp];
        }

        return -1;
    }

    public void insert(String key, int value) {
        int idx = find(key);
        
        //값이 존재할 때 대체
        if (idx != -1) {
            return;
        }
        
        //없을 때 추가`
    }

}
