package bearmaps.proj2d.utils;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/** @source https://docs.google.com/presentation/d/1XcpPT_KWUbr25d07iHGHpJN3S5DKA4cB_D4Zs5USqlM/edit#slide=id.g528d808e96_0_1309 */
public class Trie  {

    private Node root;

    private class Node {
        //private char ch;
        private boolean isKey;
        private HashMap <Character, Node> indexedMap;

        Node(boolean b) {
            isKey = b;
            indexedMap = new HashMap<>();
        }
    }

    public Trie () {
        root = new Node(false);
    }


    public boolean contains(String k) {
        boolean containsN;
        if (k == null || k.length() < 1) {
            throw new IllegalArgumentException();
        }
        Node hold = root;
        for (int i = 0; i < k.length(); i++) {
            char c = k.charAt(i);
            if (!hold.indexedMap.containsKey(c)) {
                return false;
            }
            hold = hold.indexedMap.get(c);
        }

        //Node key = find(k);
        if(hold != null && hold.isKey) {
            containsN = true;
        } else {
            containsN = false;
        }

        return containsN;
    }

    public void add(String k) {
        if (k == null || k.length() < 1) {
            return;
        }
        Node temp = root;
        for(int i = 0; i < k.length(); i++) {
            char x = k.charAt(i);
            if(!temp.indexedMap.containsKey(x)) {
                Node insert = new Node(false);
                temp.indexedMap.put(x, insert );
            }
            temp = temp.indexedMap.get(x);
        }

        temp.isKey = true;

    }

    /** @source https://docs.google.com/presentation/d/1XcpPT_KWUbr25d07iHGHpJN3S5DKA4cB_D4Zs5USqlM/edit#slide=id.g528d808e96_0_3759 */
    private void colHelp(String s, List<String> x, Node n){
        if (n == null) {
            return;
        }
        if(n.isKey) {
            x.add(s);
        }

        for(char c : n.indexedMap.keySet()) {
            colHelp(s+c , x , n.indexedMap.get(c));
        }
    }
/** @source https://algs4.cs.princeton.edu/52trie/TrieST.java.html */
    public List<String> keysWithPrefix(String s) {
        List<String> results = new ArrayList<>();
        Node hold = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!hold.indexedMap.containsKey(c)) {
                return results;
            }
            hold = hold.indexedMap.get(c);
        }

//        for(char i: temp.indexedMap.keySet()) {
//            colHelp(s + i, results, temp.indexedMap.get(s));
//        }

        colHelp(s, results, hold);

        return results;
    }
//
//        Node temp = find(s);
//        List<String> results = new ArrayList<>();
//
//        if (temp.isKey) {
//            results.add(s);
//        }
//
//        if(temp == null) {
//            return results;
//        }
//

//        return results;

    public static void main (String [] args) {
        Trie x = new Trie();
        x.add("Happy");
        x.add("Ha");
        x.add("lkl");
        x.add("Hap");
        System.out.println(x.contains("Happy"));
        System.out.println(x.keysWithPrefix("Ha"));
    }




}
