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
        public LinkedListDeque(){
            sentinel = new Node(null,null,null);
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
            size = 0;

        }

        public void addFirst(T item){
            Node first = new Node(item,sentinel.next,sentinel);
            sentinel.next.previous = first;
            sentinel.next = sentinel.next.previous; //might have to change to first
            size += 1;

        }

        public void addLast(T item){
            Node last = new Node(item,sentinel, sentinel.previous);
            sentinel.previous.next = last;
            sentinel.previous = sentinel.previous.next; //might have to change to last
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
            T temp_first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            size -= 1;
            return temp_first;
        }

        public T removeLast(){
            if(size == 0){
                return null;
            }
            T temp_last = sentinel.previous.item;
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size -= 1;
            return temp_last ;
        }

        public T get(int index){
            Node tempNode = sentinel.next;
            int counter = 0;
            if(index >= size){
                return null;
            }
            while (counter < index){
                tempNode = tempNode.next;
                counter ++;
            }
            return tempNode.item;
        }

        public T getRecursive(int index){
            return getRecursiveHelp(index, sentinel.next);
         }

        public T getRecursiveHelp(int index, Node tempNode){
            if(index == 0){
                return tempNode.item;
            }else{
                tempNode = tempNode.next;
                return getRecursiveHelp(index - 1,tempNode);
            }

        }

//        public T getRecursive(int index) {
//            if (index == 0) {
//                return sentinel.next.item;
//            } else {
//                sentinel.next = sentinel.next.next;
//                return getRecursive(index - 1);
//        }
//
//    }




    }
