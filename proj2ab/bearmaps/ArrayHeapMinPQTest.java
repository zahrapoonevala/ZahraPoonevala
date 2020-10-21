package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import org.junit.Test;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {

    @Test

    public void testAddFunction() {
        ArrayHeapMinPQ<String> testAdd = new ArrayHeapMinPQ<>();
        testAdd.add("A", 3);
        testAdd.add("B", 1);
        testAdd.add("C", 2);
        testAdd.add("D", 6);
        testAdd.add("E", 10);
        assertEquals(5, testAdd.size());
    }

    @Test
    public void testGetSmallestFunction() {
        ArrayHeapMinPQ<Integer> test2 = new ArrayHeapMinPQ<>();
        test2.add(10, 3);
        test2.add(9, 1);
        test2.add(8, 2);
        test2.add(7, 6);
        test2.add(6, 10);
        assertEquals((Integer) 9, test2.getSmallest());
    }

    @Test
    public void testRemoveSmallestFunction() {
        ArrayHeapMinPQ<Integer> test3 = new ArrayHeapMinPQ<>();
        test3.add(6, 100);
        test3.add(7, 101);
        test3.add(8, 103);
        test3.add(11, 108);
        test3.add(9, 1108);
        assertEquals((Integer) 6, test3.removeSmallest());
        assertEquals(4, test3.size());
        assertEquals((Integer) 7, test3.removeSmallest());
        assertEquals(3, test3.size());
    }

    @Test
    public void testChangePriorityFunction() {
        ArrayHeapMinPQ<Integer> test4 = new ArrayHeapMinPQ<>();
        test4.add(5, 3);
        test4.add(6, 2);
        test4.add(7, 1);
        test4.add(8, 5);
        test4.add(12, 10);
        assertEquals(5, test4.size());
        assertEquals((Integer) 7, test4.getSmallest());
        test4.changePriority(7, 4);
        assertEquals((Integer) 6, test4.getSmallest());

    }

    @Test
    public void testCombinedFunctions() {
        ArrayHeapMinPQ<Integer> test5 = new ArrayHeapMinPQ<>();
        test5.add(5, 3);
        test5.add(6, 2);
        test5.add(7, 1);
        test5.add(20, 0.1);
        test5.add(30, 0.001);
        test5.add(40, 0.5);
        test5.add(500, 1000);
        test5.add(9, 100);
        test5.add(12, 20);
        assertEquals(9, test5.size());
        assertFalse(test5.contains(1));
        assertTrue(test5.contains(30));
        assertEquals((Integer) 30, test5.removeSmallest());
        assertFalse(test5.contains(30));

        test5.changePriority(20, 1.1);

        assertEquals((Integer) 40, test5.getSmallest());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testException1() {
        ArrayHeapMinPQ<Integer> test7 = new ArrayHeapMinPQ<>();
        test7.add(1, 5);
        test7.add(1, 4);

    }

    @Test(expected = NoSuchElementException.class)
    public void testException2() {
        ArrayHeapMinPQ<Integer> test8 = new ArrayHeapMinPQ<>();
        test8.add(1, 5);
        test8.add(2, 4);
        test8.removeSmallest();
        test8.removeSmallest();
        test8.getSmallest();

    }

    @Test(expected = NoSuchElementException.class)
    public void testException3() {
        ArrayHeapMinPQ<Integer> test9 = new ArrayHeapMinPQ<>();
        test9.add(1, 5);
        test9.add(2, 4);
        test9.add(3, 8);
        test9.changePriority(4, 5);

    }


    /**
     * @source cs61b fall 2020 Lab 5
     */
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }


    @Test
    public void timeTestAdd() {
        List<Integer> Ns1 = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        Random temp = new Random(10);
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();

        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i <= 1000000; i = i + 1) {
            test.add(i, temp.nextInt());

            times.add(sw1.elapsedTime());
            Ns1.add(i);
            opCounts.add(i);
        }
        printTimingTable(Ns1, times, opCounts);

    }

    @Test
    public void timeTestRemoveSmallest() {
        List<Integer> Ns2 = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        Random temp = new Random(10);
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();

        for (int i = 0; i <= 1000000; i = i + 1) {
            test.add(i, temp.nextInt());
        }
            Stopwatch sw1 = new Stopwatch();
            for (int j = 0; j <= 1000; j = j + 1) {
                test.removeSmallest();

                times.add(sw1.elapsedTime());
                Ns2.add(j);
                opCounts.add(j);
            }
            printTimingTable(Ns2, times, opCounts);

        }


    }


