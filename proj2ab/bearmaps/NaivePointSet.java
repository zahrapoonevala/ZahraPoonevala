package bearmaps;

import java.util.ArrayList;
import java.util.List;

/** @source cs61b fall 2020 Josh Hug Walkthrough video */
public class NaivePointSet implements PointSet {
    private List<Point> allPoints;

    public NaivePointSet(List<Point> points) {
        allPoints = new ArrayList<>();
        for (Point p: points) {
            allPoints.add(p);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point best = allPoints.get(0);
        Point current = new Point(x, y);
        for (Point p: allPoints) {
            double currentD = Point.distance(p, current);
            if (currentD < Point.distance(best, current)) {
                best = p;
            }
        }
        return best;
    }

    public static void main(String [] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
