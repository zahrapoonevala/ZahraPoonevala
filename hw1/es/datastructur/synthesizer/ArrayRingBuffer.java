package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayRingBufferIterator<T> implements Iterator<T> {

        private int counter;
        private int currentIndex;

        public ArrayRingBufferIterator() {
            counter = fillCount;
            currentIndex = first;
        }

        @Override
        public boolean hasNext(){
            return counter!= 0;
        }

        @Override
        public T next(){
            T temp = (T) rb[currentIndex];
            counter -= 1;
            currentIndex += 1;
            if(currentIndex == rb.length){
                currentIndex = 0;
            }

            return temp;
        }

    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (fillCount >= capacity()){
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;

        if (last == capacity()) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (fillCount == 0){
            throw new RuntimeException("Ring Buffer underflow");
        }
        T tempFirst = rb[first];
        fillCount -= 1;
        first += 1;
        if (first == capacity()) {
            first = 0;
        }

        return tempFirst;
}

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (fillCount == 0){
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator (){
        return new ArrayRingBufferIterator<>();
    }

    @Override
    public boolean equals(Object o){
        ArrayRingBuffer tempCompare = (ArrayRingBuffer) o;
        Iterator x1 = this.iterator();
        Iterator x2 = tempCompare.iterator();

        if(this.fillCount() != tempCompare.fillCount()){
            return false;
        }

        while(x1.hasNext()){
            if (!x1.next().equals(x2.next())) {
                return false;
            }
        }

        return true;
    }


    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
