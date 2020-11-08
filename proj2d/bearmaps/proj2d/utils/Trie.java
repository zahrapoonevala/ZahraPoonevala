package bearmaps.proj2d.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    private Node findStart (String k) {
        Node temp = root;
        for (int i = 0; i < k.length(); i++) {
            char x = k.charAt(i);
            if(!root.indexedMap.containsKey(x)){
                return null;
            }
            temp = temp.indexedMap.get(x);
        }
        return temp;
    }

    public boolean contains(String k) {
        boolean containsN;
        if (k == null || k.length() < 1) {
            throw new IllegalArgumentException();
        }
        Node key = findStart(k);
        if(key != null && key.isKey) {
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
        for(int i = 0, y = k.length(); i < y; i++) {
            char x = k.charAt(i);
            if(!temp.indexedMap.containsKey(x)) {
                temp.indexedMap.put(x,  new Node(false));
            }
            temp = temp.indexedMap.get(x);
        }

        temp.isKey = true;
    }

    public List<String> keysWithPrefix(String s) {
        Node temp = findStart(s);
        List<String> results = new ArrayList<>();

        colHelp(s, results, temp);

        return results;

//        Node temp = findStart(s);
//        List<String> results = new ArrayList<>();

//        if (temp.isKey) {
//            results.add(s);
//        }
//
//        if(temp == null) {
//            return results;
//        }
//
//        for(char i: temp.indexedMap.keySet()) {
//            colHelp(s + i, results, temp.indexedMap.get(s));
//        }
//        return results;
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
            colHelp(s + c, x , n.indexedMap.get(c));
        }
    }


}
