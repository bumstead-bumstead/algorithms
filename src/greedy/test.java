package greedy;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0, 2);
        list.add(list.size(), 1);
        System.out.println(list.get(list.size()-1));
    }
}
