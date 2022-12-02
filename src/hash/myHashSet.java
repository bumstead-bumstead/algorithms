package hash;


//key가 String인 hash~
public class myHashSet {
    int M = 1000003; //원소 수
    int MX = 500005; //최대 입력 개수
    int[] head = new int[M]; //각 해시값의 첫번째 원소 -> "몇 번째로 삽입한 데이터인지"가 나오는 거~
    int[] next = new int[MX]; //각 해시값의 다음 원소 idx
    int[] prev = new int[MX]; //각 해시값의 이전 idx
    int[] values = new int[MX];
    int[] keys = new int[MX];
    int unused = 0;

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (hash * 1000 + key.charAt(i)) % M;
        }
        return hash;
    }

    public int find(String key) {
        
    }

}
