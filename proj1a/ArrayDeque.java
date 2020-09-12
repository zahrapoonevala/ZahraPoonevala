public class ArrayDeque<T> {
    private int size;
    private T [] items;
    private int REFACTOR = 2;
    private int frontIndex;
    private int backIndex;
    private double usageRatio = 0.0;

    /* Creates an empty Array Linked List. */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        frontIndex = 0;
        backIndex = 1;

    }

    /* Finds the index for the next item to be added to the front, treated as a circular array.
     * * @param index of current front.
     * */
    private int firstHelper(int i) {
        if (i == 0) {
            return items.length - 1;
        } else {
            return i - 1;
        }
    }

    /* Finds the index for the next item to be added to the back, treated as a circular array.
     * * @param index of current back.
     * */
    private int backHelper(int i) {
        if (i == items.length - 1) {
            return 0;
        } else {
            return i + 1;
        }
    }

    /* resizes the array depending on usage.
     * * @param desired size of new array.
     * */
    private void reSize(int capacity) {

        T[] a = (T[]) new Object[capacity];

        int tempCurrent = backHelper(frontIndex);
        for (int i = 0; i < size; i++) {
            a[i] = items[tempCurrent];
            tempCurrent = backHelper(tempCurrent);
        }

        items = a;
        frontIndex = capacity - 1;
        backIndex = size;
    }

    /* Adds an item to the front of the array.
     * * @param item to be added.
     * */
    public void addFirst(T item) {
        if (size == items.length) {
            reSize(size * REFACTOR);
        }
        items[frontIndex] = item;
        frontIndex = firstHelper(frontIndex);
        size += 1;

    }

    /* Adds an item to the back of the array.
     * * @param item to be added.
     * */
    public void addLast(T item) {
        if (size == items.length) {
            reSize(size * REFACTOR);
        }
        items[backIndex] = item;
        backIndex = backHelper(backIndex);
        size += 1;
    }

    /* Checks if it is empty. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* Returns the size. */
    public int size() {
        return size;
    }

    /* Prints all the elements. */
    public void printDeque() {
        for (int i = backHelper(frontIndex); i != backIndex; i = backHelper(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /* Removes the first item and adjusts the array correctly. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int tempFirst = backHelper(frontIndex);
        T first = items[tempFirst];
        items[tempFirst] = null;
        frontIndex = tempFirst;
        size -= 1;

        usageRatio = (float) size / items.length;
        if (usageRatio < 0.25 && items.length > 16) {
            reSize(items.length / REFACTOR);
        }

        return first;
    }

    /* Removes the last item and adjusts the array correctly. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int tempLast = firstHelper(backIndex);
        T back = items[tempLast];
        items[tempLast] = null;
        backIndex = tempLast;
        size -= 1;

        usageRatio = (float) size / items.length;
        if (usageRatio < 0.25 && items.length > 16) {
            reSize(items.length / REFACTOR);
        }
        return back;
    }

    /* Iterative way to get the item at a specified index.
     * @param index of desired item.
     **/
    public T get(int index) {
        int adjustedIndex = frontIndex + 1 + index;
        if (index >= size) {
            return null;
        }
        if (adjustedIndex > items.length - 1) {
            adjustedIndex -= items.length; // circular array
        }
        return items[adjustedIndex];

    }


}
