package binarySearchTree;

import com.sun.source.tree.Tree;

import java.util.TreeSet;

public class test {
    public static void main(String[] args) {
        TreeSet<Integer> bags = new TreeSet<>();
        bags.add(1);
        bags.add(1);
        bags.add(1);
        System.out.println(bags);
        bags.remove(1);
        System.out.println(bags);
    }
}
