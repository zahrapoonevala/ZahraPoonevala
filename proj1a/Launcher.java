import java.util.Deque;

public class Launcher {
        public static void main (String[] args){
            ArrayDeque<Integer> x = new ArrayDeque<>();
            x.addFirst(0);
            x.addLast(1);
            x.addLast(2);
            System.out.println(x.removeFirst());
        }

}
