package bearmaps.proj2d.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @source https://docs.google.com/presentation/d/1XcpPT_KWUbr25d07iHGHpJN3S5DKA4cB_D4Zs5USqlM/edit#slide=id.g528d808e96_0_1309 */
public class Trie  {

    private Node root;

    private class Node {
        private char ch;
        private boolean isKey;
        private Map <Character, Node> indexedMap;

        private Node(char c, boolean b) {
            ch = c;
            isKey = b;
            indexedMap = new HashMap<>();
        }
    }

    public Trie () {
        root = new Node(' ', false);
    }

    public boolean contains(String k) {
        if (k == null) {
            return false;
        }
        Node temp = root;
        for (int i = 0; i < k.length(); i++) {
            char x = k.charAt(i);
            if (temp.indexedMap.containsKey(x)) {
                temp = temp.indexedMap.get(x);
                if (i == k.length() - 1 && temp.isKey) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public void add(String k) {
        if (k == null) {
            return;
        }
        Node temp = root;
        for(int i = 0; i < k.length(); i++) {
            char x = k.charAt(i);
            if(!temp.indexedMap.containsKey(x)) {
                Node insert = new Node(x, false);
                temp.indexedMap.put(x, insert);
            }
            temp = temp.indexedMap.get(x);
        }

        temp.isKey = true;
    }

    public List<String> keysWithPrefix(String s) {
        Node temp = root;
        List<String> results = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (temp.indexedMap.containsKey(x)) {
                temp = temp.indexedMap.get(x);
            }
                return results;
        }

        if(temp.isKey) {
            results.add(s);
        }

        for(char c : temp.indexedMap.keySet()) {
            helperKeysWtihPrefix(s, results, temp.indexedMap.get(c));
        }
        return results;
    }

    public void helperKeysWtihPrefix(String s, List<String> x, Node n){
        if(n.isKey) {
            x.add(s + n.ch);
        }

        for(char c : n.indexedMap.keySet()) {
            helperKeysWtihPrefix(s + n.ch, x , n.indexedMap.get(c));
        }
    }


}
