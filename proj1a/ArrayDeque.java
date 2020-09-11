import org.w3c.dom.Node;

public class ArrayDeque <T> {
    private int size;
    private T [] items;
    private int REFACTOR = 2;
    private int frontIndex;
    private int backIndex;
    private double usageRatio = 0;

    public ArrayDeque () {
        size = 0;
        items = (T[]) new Object[8];
        frontIndex = 0;
        backIndex = 1;

    }
    private int firstHelper (int i){
        if(i == 0){
            return items.length - 1;
        } else {
            return i - 1;
        }
    }

    private int backHelper (int i){
        if (i == items.length - 1){
            return 0;
        } else {
            return i + 1;
        }
    }

    private void reSize(int capacity) {
        if (size == 0){
            return;
        }
        T[] a = (T[]) new Object[capacity * 2];

        int firstIndex = backHelper(frontIndex);
        int lastIndex = firstHelper(backIndex);

        if (firstIndex > lastIndex) {
            System.arraycopy(items, firstIndex, a, 0, items.length - firstIndex );
            System.arraycopy(items, 0, a, items.length - firstIndex, lastIndex + 1);
        } else {
            System.arraycopy(items, firstIndex, a, 0, size);
        }
        items = a;
        frontIndex = items.length - 1;
        backIndex = size;
    }

    public void addFirst(T item){
        if(size == items.length){
            reSize(size * REFACTOR);
        }
        items[frontIndex] = item;
        frontIndex = firstHelper(frontIndex);
        size += 1;

    }

    public void addLast(T item){
        if(size == items.length){
            reSize(size * REFACTOR);
        }
        items[backIndex] = item;
        backIndex = backHelper(backIndex);
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = backHelper(frontIndex); i != backIndex; i=backHelper(i)){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if(size == 0){
            return null;
        }
        int tempFirst = backHelper(frontIndex);
        T first = items[tempFirst];
        items[tempFirst] = null;
        frontIndex = tempFirst;
        size -= 1;

        usageRatio = size/items.length;
        if (usageRatio < 0.25){
            reSize((int) (items.length / REFACTOR));
        }

        return first;
    }

    public T removeLast() {
        if(size == 0){
            return null;
        }
        int tempLast = firstHelper(backIndex);
        T back = items[tempLast];
        items[tempLast] = null;
        backIndex = tempLast;
        size -= 1;

        usageRatio = size/items.length;
        if (usageRatio < 0.25){
            reSize((int) (items.length / REFACTOR));
        }

        return back;
    }

    public T get(int index){
        int adjustedIndex = frontIndex + 1 + index;
        if(index >= size){
            return null;
        }
        if(adjustedIndex > items.length - 1){
            adjustedIndex -= items.length; // circular array
        }
        return items[adjustedIndex];

    }


}
