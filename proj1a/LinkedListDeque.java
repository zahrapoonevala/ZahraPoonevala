public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    private class Node {
        private Node previous;
        private Node next;
        private T item;

        Node(T i, Node n, Node p) {
            item = i;
            next = n;
            previous = p;
        }

    }

    /* Creates an empty Linked List. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;

    }

    /* Adds an item to the front of the Linked List.
    * @param item to be added.
    * */
    public void addFirst(T item) {
        Node first = new Node(item,sentinel.next,sentinel);
        sentinel.next.previous = first;
        sentinel.next = sentinel.next.previous;
        size += 1;

    }

    /* Adds an item to the end of the Linked List.
     * @param item to be added.
     * */
    public void addLast(T item) {
        Node last = new Node(item, sentinel, sentinel.previous);
        sentinel.previous.next = last;
        sentinel.previous = sentinel.previous.next; //might have to change to last
        size += 1;
    }

    /* Checks if the Linked List is empty. */
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    /* Returns the size */
    public int size() {
        return size;
    }

    /* Prints out all the items in the Linked List, separated by a space. */
    public void printDeque() {
        Node tempNode = sentinel;
        while (size > 0) {
            tempNode = tempNode.next;
            System.out.print(tempNode.item + " ");
            size -= 1;
        }
        System.out.println();
    }

    /* Removes the first item and accordingly readjusts the Linked List. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T tempFirst = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size -= 1;
        return tempFirst;
    }

    /* Removes the last item and accordingly readjusts the Linked List. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T tempLast = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size -= 1;
        return tempLast;
    }

    /* Iterative way to get the item at a specified index.
    * @param index of desired item.
    **/
    public T get(int index) {
        Node tempNode = sentinel.next;
        int counter = 0;
        if(index >= size) {
            return null;
        }
        while (counter < index) {
            tempNode = tempNode.next;
            counter++;
        }
        return tempNode.item;
    }

    /* Recursive way to get the item at a specified index.
    * * @param index of desired item.
    * */
    public T getRecursive(int index) {
        return getRecursiveHelp(index, sentinel.next);
    }

    /* Recursive helper to adjust both the index and Linked List for the next call.
     * * @param index of desired item, Linked List.
     * */
    private T getRecursiveHelp(int index, Node tempNode){
        if(index == 0) {
            return tempNode.item;
        } else {
            tempNode = tempNode.next;
            return getRecursiveHelp(index - 1, tempNode);
        }
    }


}
