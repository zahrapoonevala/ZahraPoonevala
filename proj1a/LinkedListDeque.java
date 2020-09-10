public class LinkedListDeque <T> {
    private int size;
    private Node sentinel;

    private class Node {
        private Node previous;
        private Node next;
        private T item;

        public Node(T i, Node n, Node p){
            item = i;
            next = n;
            previous = p;
        }

    }

        public void addFirst(T item){
            Node first = new Node(item,sentinel.next,sentinel);
            sentinel.next.previous = first;
            sentinel.next = sentinel.next.previous;
            size += 1;

        }

        public void addLast(T item){
            Node last = new Node(item,sentinel, sentinel.previous);
            sentinel.previous.next = last;
            sentinel.previous = sentinel.previous.next;
            size += 1;
        }

        public boolean isEmpty(){
           if(size == 0){
               return true;
           }
               return false;
        }

        public int size (){
            return size;
        }

        public void printDeque(){
            Node tempNode = sentinel;
            while (size > 0){
                tempNode = tempNode.next;
                System.out.print(tempNode.item + " ");
                size -= 1;
            }
            System.out.println();
        }

        public T removeFirst(){
            if(size == 0){
                return null;
            }
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            size -= 1;
            return sentinel.next.item;
        }

        public T removeLast(){
            if(size == 0){
                return null;
            }
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size -= 1;
            return sentinel.previous.item;
        }

        public T get(int index){
            Node tempNode = sentinel.next;
            int counter = 0;
            if(index > size){
                return null;
            }
            while (counter < index ){
                sentinel.next = sentinel.next.next;
                counter ++;
            }
            return sentinel.item;
        }

        public LinkedListDeque(){
            sentinel = new Node(null,null,null);
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
            size = 0;

        }

        public T getRecursive(int index){
            if(index == 0){
                return sentinel.next.item;
            }else{
                sentinel.next = sentinel.next.next;
                return getRecursive(index - 1);
            }

        }


}
