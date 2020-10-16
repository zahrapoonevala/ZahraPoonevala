import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** @source cs61b fall 2020 lab 8 staff solutions */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private ArrayList<Node> [] buckets;
    private int size;
    private double loadFactor;
    private HashSet<K> keys;

    private static final int default_size = 16;
    private static final double default_load = 0.75;

    private class Node {
        K key;
        V value;

        Node(K k, V v){
            key = k;
            value = v;
        }


    }
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = (ArrayList<Node>[]) new ArrayList[initialSize];
        size = 0;
        loadFactor = maxLoad;
        keys = new HashSet<K>();
    }

    public MyHashMap(int initialSize) {
        this(initialSize, default_load);
    }

    public MyHashMap() {
        this(default_size, default_load);
    }

    private int findBucket(K key, int numBuckets) {
        return Math.floorMod(key.hashCode(), numBuckets);
    }
    private int findBucket(K key) {
        return findBucket(key, buckets.length);
    }

    private void rebucket(int targetSize) {
        ArrayList<Node>[] newBuckets = (ArrayList<Node>[]) new ArrayList[targetSize];
        for (K key: keys) {
            int index = findBucket(key, newBuckets.length);
            if (newBuckets[index] == null) {
                newBuckets[index] = new ArrayList<>();
            }
            newBuckets[index].add(getNode(key));
        }
        buckets = newBuckets;
    }
    @Override
    public void clear() {
        buckets = (ArrayList<Node>[]) new ArrayList[default_size];
        size = 0;
        keys = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {

        return keySet().contains(key);
    }


    @Override
    public V get(K key) {
        Node answer = getNode(key);
        if (answer != null){
            return answer.value;
        }
        return null;
    }

    private Node getNode(K key) {
        int i = findBucket(key);
        ArrayList<Node> bucketLists = buckets[i];
        if(bucketLists != null) {
            for (Node n : bucketLists) {
                if (n.key.equals(key)) {
                    return n;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }

        if (((double) size) / buckets.length > loadFactor) {
            rebucket(buckets.length * 2);
        }

        size ++;
        keys.add(key);

        int i = findBucket(key);
        ArrayList<Node> bucketList = buckets[i];

        if(bucketList == null) {
            bucketList = new ArrayList<>();
            buckets[i] = bucketList;
        }
        bucketList.add(new Node(key,value));
    }

    @Override
    public Set<K> keySet() {
        return keys;
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
        return keys.iterator();
    }
}
