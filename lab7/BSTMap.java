import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;


        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;

        }

    }

    private BSTNode root;
    private int size;

    @Override
    public V get(K key) {
        return getHelp(key, root);
    }

        private V getHelp(K key, BSTNode tree){
            if(tree == null){
                return null;
            }
            int temp = tree.key.compareTo(key);
            if (temp > 0) {
                return getHelp(key, tree.right);
            } else if (temp < 0) {
                return getHelp(key, tree.left);
            } else {
                return tree.value;
            }

        }


    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(root == null){
            root = new BSTNode(key, value);
            size = 1;
        } else {
            putHelp(key, root, value);
        }
    }

    private BSTNode putHelp(K key, BSTNode tree, V value) {
        if (tree == null) {
            return null;
        }
        int temp = tree.key.compareTo(key);
        if (temp > 0) {
            if (tree.right != null) {
                return putHelp(key, tree.right, value);
            } else {
                tree.right = new BSTNode(key, value);
                size++;
            }

        } else if (temp < 0) {
            if (tree.left != null) {
                return putHelp(key, tree.left, value);
            } else {
                tree.left = new BSTNode(key, value);
                size++;
            }
        } else {
            tree.value = value;
        }

        return tree;
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }










}
