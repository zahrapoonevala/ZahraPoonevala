package bearmaps.proj2c;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome finalOutcome;
    private double weight;
    private double finalWeight;
    private List<Vertex> answers;
    private double time; //time elapsed
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    private int counter;


    /** @source https://fa20.datastructur.es/materials/proj/proj2c/proj2c#the-a-algorithm */
    public AStarSolver(AStarGraph<Vertex> G, Vertex start, Vertex goal, double timeout) {
        Stopwatch sw = new Stopwatch();
        if (G == null) { //Check for unsolvable
            time = sw.elapsedTime();
            finalOutcome = SolverOutcome.UNSOLVABLE;
        }

        finalOutcome = SolverOutcome.SOLVED;
        //Pseudocode from spec
        DoubleMapPQ<Vertex> pQ = new DoubleMapPQ<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        double priority = G.estimatedDistanceToGoal(start, goal);
        distTo.put(start, 0.0);
        edgeTo.put(start, start);
        pQ.add(start, priority);
        while (pQ.size() != 0) {
            Vertex smallest = pQ.removeSmallest();
            if (smallest.equals(goal)) {
                time = sw.elapsedTime();
                break;
            }

            if (sw.elapsedTime() > timeout) {
                finalOutcome = SolverOutcome.TIMEOUT;
                time = sw.elapsedTime();
                break;
            }

            /** @source https://fa20.datastructur.es/materials/proj/proj2c/proj2c#the-a-algorithm */
            List<WeightedEdge<Vertex>> edges = G.neighbors(smallest);
            for (WeightedEdge<Vertex> e : edges) {
                Vertex q = e.to();
                Vertex p = smallest; // p = smallest
                weight = e.weight();
                if (distTo.get(q) == null || distTo.get(q) > distTo.get(p) + weight) {
                    distTo.put(q, distTo.get(p) + weight);
                    edgeTo.put(q, smallest);
                    double heuristic = G.estimatedDistanceToGoal(q, goal);
                    if (pQ.contains(q)) {
                        pQ.changePriority(q, distTo.get(p) + weight + heuristic);
                    } else {
                        pQ.add(q, distTo.get(p) + weight + heuristic);
                    }

                }

            }
            counter++;


        }
        answers = new LinkedList<>();
        if (!(finalOutcome == SolverOutcome.TIMEOUT || finalOutcome == SolverOutcome.UNSOLVABLE)) {
            if (distTo.get(goal) == null) {
                finalOutcome = SolverOutcome.UNSOLVABLE;
            } else {
                finalWeight = distTo.get(goal);
                Vertex i = goal;
                answers.add(0, goal);
                while (!i.equals(start)) {
                    i = edgeTo.get(i);
                    answers.add(0, i);
                }
            }

            time = sw.elapsedTime();

        }




    }

    @Override
    public SolverOutcome outcome() {
        return finalOutcome;
    }

    @Override
    public List<Vertex> solution() {
        return answers;
    }

    @Override
    public double solutionWeight() {
        if (finalOutcome == SolverOutcome.TIMEOUT || finalOutcome == SolverOutcome.UNSOLVABLE) {
            return 0.0;
        }
        return finalWeight;
    }

    @Override
    public int numStatesExplored() {
        return counter;
    }

    @Override
    public double explorationTime() {
        return time;
    }
}
