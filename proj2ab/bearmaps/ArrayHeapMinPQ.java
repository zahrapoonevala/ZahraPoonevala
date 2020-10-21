package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>  {
//    private int size;
    private ArrayList<Node> minHeap;
    private HashMap<T, Integer> getItem; //need this for the changePriority func

    private class Node {
        T value;
        double keyPriority;

        Node(T V, double K) {
            value = V;
            keyPriority = K;
        }

    }

    public ArrayHeapMinPQ() {
//            size = 0;
        minHeap = new ArrayList<Node>();
        getItem = new HashMap<T, Integer>();
    }

    /** @source https://algs4.cs.princeton.edu/24pq/MinPQ.java.html */
    private void swap(int k, int i) {
        getItem.replace(minHeap.get(k).value, i);
        getItem.replace(minHeap.get(i).value, k);
        Node swap = minHeap.get(k);
        minHeap.set(k, minHeap.get(i));
        minHeap.set(i, swap);
    }

    /** @source cs61b 2020 ds6 lec21 heaps and pq pseudocode */
    private int parent(int k) {
        return (k - 1) / 2;
    }

    /** @source cs61b 2020 ds6 lec21 heaps and pq pseudocode */
    private int leftChild(int k) {
        return (k * 2) + 1;
    }

    /** @source cs61b 2020 ds6 lec21 heaps and pq pseudocode */
    private int rightChild(int k) {
        return (k * 2) + 1 + 1;
    }

    /** @source cs61b 2020 ds6 lec21 heaps and pq pseudocode */
    private void swim(int k) {
        int tempP = parent(k);
        if (minHeap.get(tempP).keyPriority > minHeap.get(k).keyPriority) {
            swap(k, tempP);
            swim(parent(k));
        }

    }


    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        } else {
            Node newNode = new Node(item, priority);
            minHeap.add(newNode);
            getItem.put(item, minHeap.size() - 1);
            swim(minHeap.size() - 1);

        }

    }


    @Override
    public boolean contains(T item) {
        return getItem.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (minHeap.size() == 0) {
            throw new NoSuchElementException();
        }
        T answer;
        answer = minHeap.get(0).value;
        return answer;

    }

//    private int sinkChildChoice(int k) {
//        int tempLeftChild = leftChild(k);
//        int tempRightChild = rightChild(k);
//        int swapChild;
//
//        if (minHeap.get(tempLeftChild).keyPriority <= minHeap.get(tempRightChild).keyPriority) {
//            swapChild = tempLeftChild;
//        } else {
//            swapChild = tempRightChild;
//        }
//        return swapChild;
//    }

    /** @source https://algs4.cs.princeton.edu/24pq/MinPQ.java.html */
    private void sink(int k) {
        int LC = leftChild(k);
        int RC = rightChild(k);
        int swapChild;
        while (LC < size()) {
            swapChild = LC;
            if (RC < size() && (minHeap.get(RC).keyPriority < minHeap.get(swapChild).keyPriority)) {
                swapChild = RC;
            }

            if (minHeap.get(k).keyPriority < minHeap.get(swapChild).keyPriority) {
                return;
            }
            swap(k, swapChild);
            sink(swapChild);

        }

    }


    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T result = this.getSmallest();
        getItem.remove(result);
        swap(0, minHeap.size() - 1);
        minHeap.remove(minHeap.size() - 1);
        sink(0);

        return result;

    }

    @Override
    public int size() {
        return minHeap.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int itemChange = getItem.get(item);
        double prevP = minHeap.get(itemChange).keyPriority;
        minHeap.get(itemChange).keyPriority = priority;

        if (prevP > priority) {
            swim(itemChange);
        }
        sink(itemChange);

    }



}
