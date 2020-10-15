package bearmaps;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;


public class KDTreeTest {

    private static final int min_X = -1000, min_Y = -1000;
    private static final int max_X = 1000, max_Y = 1000;


    private static KDTree lectureTree() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 5);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree temp = new KDTree(List.of(p1,p2,p3,p4,p5,p6,p7));
        return temp;
    }

    @Test
    public void test(){
        KDTree testTree = lectureTree();
        //NaivePointSet nps = lectureTree();
        Point actual = testTree.nearest(0,7);
        Point expected = new Point (1,5);
        assertEquals(expected,actual);
    }

    private Point randomPoint(Random r){
//        double x = rTemp.nextDouble()*1000;
//        double y = rTemp.nextDouble()*1000;
//        return new Point(x,y);
        //Random rTemp = new Random(500);
        return new Point(randomX(r), randomY(r));
    }

    private List<Point> randomPoints(int N){
        List<Point> pointsTemp = new ArrayList<>();
        long c = 500;
        Random rTemp = new Random(c);
        c ++;
        for (int i = 0; i < N; i ++){
            pointsTemp.add(randomPoint(rTemp));
        }
        return pointsTemp;
    }

    private static double randomD(Random r, double rmin, double rmax){
        return rmin + (rmax - rmin) * r.nextDouble();
    }

    private static double randomX(Random r) {
        return randomD(r, min_X, max_X);
    }

    private static double randomY(Random r) {
        return randomD(r, min_Y, max_Y);
    }


    private static double distance(double x1, double x2, double y1, double y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    public static double distance(Point p1, Point p2) {
        return distance(p1.getX(), p2.getX(), p1.getY(), p2.getY());
    }

    private void testWithNPointsAndQQueries(int pointCount, int queryCount) {
        List<Point> points = randomPoints(pointCount);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);


        List<Point> queries = randomPoints(queryCount);
        for (Point p : queries) {
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(distance(p.getX(),expected.getX(),p.getY(),expected.getY()), distance(p.getX(),actual.getX(),p.getY(),actual.getY()), 0.000001);
        }
    }

    @Test
    public void testWith1000PointsAnd200Queries() {
        int pointCount = 1000;
        int queryCount = 200;
        testWithNPointsAndQQueries(pointCount, queryCount);
    }

    @Test
    public void testWith10000PointsAnd2000Queries() {
        int pointCount = 10000;
        int queryCount = 2000;
        testWithNPointsAndQQueries(pointCount, queryCount);
    }



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
    public void timeConstructKD() {
        List<Integer> Ns1 = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Point> randomPoints = randomPoints(100000);

        Stopwatch sw1 = new Stopwatch();
        for(int i = 31250; i<= 2000000; i = i + i){
            KDTree testKD1 = new KDTree(randomPoints(i));
            //List<Point> xPoints = randomPoints(i);


            //for(int m = 0; m<10000; m++){
                //new KDTree(randomPoints);
            // System.out.println(pRand);
            times.add(sw1.elapsedTime());
            Ns1.add(i);
            opCounts.add(i);
            }
        printTimingTable( Ns1, times,  opCounts);

        }


    @Test
    public void timeGetKD() {
        List<Integer> Ns1 = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Point> randomPoints = randomPoints(100000);
        long seed = 0;
        for(int i = 31250; i<= 1000000; i = i + i){
            KDTree testKD = new KDTree(randomPoints(i));
            //List<Point> xPoints = randomPoints(i);
            Random r = new Random(seed);
            seed+=1;

            Stopwatch sw1 = new Stopwatch();
            for(int m = 0; m<10000; m++){
                Point pRand = randomPoint(r);
                testKD.nearest(pRand.getX(), pRand.getY());
                //System.out.println(pRand);
            }
            times.add(sw1.elapsedTime());
            Ns1.add(i);
            opCounts.add(1000000);

        }

        printTimingTable( Ns1, times,  opCounts);

    }

    @Test
    public void timeGetNaive() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        List<Point> randomPoints = randomPoints(100000);
        Random r = new Random(500);
        for(int i = 125; i<= 1000; i = i + i){
            NaivePointSet ns = new NaivePointSet(randomPoints(i));
            //List<Point> xPoints = randomPoints(i);

            Stopwatch sw = new Stopwatch();
            for(int m = 0; m<1000000; m++){
                Point pRand = randomPoint(r);
                ns.nearest(pRand.getX(), pRand.getY());
            }
            times.add(sw.elapsedTime());
            Ns.add(i);
            opCounts.add(1000000);

        }

        printTimingTable( Ns, times,  opCounts);

    }







}
