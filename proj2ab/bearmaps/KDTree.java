package bearmaps;

import java.util.List;

/** @source cs61b fall 2020 Josh Hug Walkthrough video */
public class KDTree {
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;
    private Node root;

    private class Node{
        private Point temp;
        private boolean subspaces;
        private Node left;
        private Node right;

        private Node(Point p, boolean subspaces){
            temp = p;
            this.subspaces = subspaces;
            c = 0;
        }

    }

    public KDTree(List<Point> points){
        for(Point p: points){
            root = insert(p,root,HORIZONTAL);
        }
    }

    private Node insert(Point p, Node n, boolean subspaces){
        if(n == null){
            return new Node(p, subspaces);
        }
        if(p.equals(n.temp)){
            return n;
        }
        int temp = compareHelp(p, n.temp,subspaces);
        if(temp >= 0){
            n.right = insert(p, n.right, !subspaces);
        } else if (temp < 0) {
            n.left = insert(p, n.left, !subspaces);
        }

        return n;
    }


    private int compareHelp(Point one, Point two, boolean subspaces){
        if(subspaces == HORIZONTAL){
            return Double.compare(one.getX(), two.getX());
        }
         return Double.compare(one.getY(), two.getY());
    }

    /** @source cs61b 2019 ds6 lec22 kd trees pseudocode */
    public Point nearest(double x, double y){
        c = 0;
        Node tempNode = nearestHelper(root,new Point(x,y),root);
        System.out.println(c);
        return new Point(tempNode.temp.getX(), tempNode.temp.getY());
    }

    private static int c = 0;
    private Node nearestHelper(Node n, Point goal, Node best){
        if(n == null){
            c = c+1;
            return best;

        }

        if(Point.distance(n.temp, goal) < Point.distance(best.temp,goal)){
            best = n;
        }
        Node goodSide;
        Node badSide;
        int temp = compareHelp(goal, n.temp, n.subspaces);
        if(temp < 0){
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;

        }
        best = nearestHelper(goodSide,goal,best);

        if (useful(n, goal, best)){
            best = nearestHelper(badSide, goal, best);
        }

        return best;
    }

    private boolean useful(Node n, Point goal, Node best) {
        double bestPossible = Point.distance(goal, best.temp);
        Point tempBest = new Point(goal.getX(), n.temp.getY());
        Point tempBest1 = new Point(n.temp.getX(), goal.getY());
        if (n.subspaces) {
            if (Point.distance(tempBest, goal) < bestPossible) {
                return true;
            }
        } else {
            if (Point.distance(tempBest1, goal) < bestPossible) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kdT = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    }



}
