import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {

    @Test
    public void testOuputs()  {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> test1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            int randomInsert = StdRandom.uniform(10);
            //System.out.println(randomInsert);
            double numberBetweenZeroAndOne = StdRandom.uniform();
            //System.out.println(numberBetweenZeroAndOne);
            if (numberBetweenZeroAndOne < 0.5) {
                test.addLast(randomInsert);
                test1.addLast(randomInsert);
                System.out.printf("addLast(%d)%n", randomInsert);
            } else {
                test.addFirst(randomInsert);
                test1.addFirst(randomInsert);
                System.out.printf("addFirst(%d)%n", randomInsert);
            }
        }

        //System.out.print("a" + addFirstCounter);
        //System.out.println("b" + addLastCounter);

        while(!(test.isEmpty() && test1.isEmpty())){
            double numberBetweenZeroAndOne1 = StdRandom.uniform();
            //System.out.println(numberBetweenZeroAndOne1);
            Integer x;
            Integer y;
            if (numberBetweenZeroAndOne1 < 0.5) {
                 x = test.removeFirst();
                 y = test1.removeFirst();
                System.out.printf("removeFirst()%n");
            } else {
                 x = test.removeLast();
                 y = test1.removeLast();
                System.out.printf("removeLast()%n");
            }
            assertEquals(x,y);

        }

        assertEquals(test.isEmpty(), test1.isEmpty());

    }

}


