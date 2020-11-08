package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.streetmap.StreetMapGraph;
import bearmaps.proj2c.streetmap.Node;
import bearmaps.proj2d.utils.Trie;
import edu.princeton.cs.algs4.TrieSET;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */


public class AugmentedStreetMapGraph extends StreetMapGraph {

    private List<Point> listPoints = new ArrayList<>();
    private Map<Point, Node> pToN = new HashMap<>();
    private Trie trieNames = new Trie();
    private Map<String, List<Node>> names = new HashMap<>();


    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        for (Node i : nodes) {
            if(i.name() != null) {
                String clean = cleanString(i.name());
                trieNames.add(clean);
                if (!names.containsKey(clean)){
                    names.put(clean, new LinkedList<>());
                }
                names.get(clean).add(i);
            }

            if (neighbors(i.id()).size() > 0) { //Check for neighbors
                Point newPoint = new Point(i.lon(), i.lat());
                listPoints.add(newPoint);
                pToN.put(newPoint, i);

            }

        }


        }




    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        PointSet p = new WeirdPointSet(listPoints);
        Point shortest =  p.nearest(lon, lat);
        return pToN.get(shortest).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        String cleanedPrefix = cleanString(prefix);
       List<String> cleaned = trieNames.keysWithPrefix(cleanedPrefix);
       List<String> answer = new LinkedList<>();
       for (String i : cleaned) {
            for (Node n : names.get(i)){
                if (!answer.contains(n.name())){
                    answer.add(n.name());
                }

            }
       }
       return answer;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String cleanedParam = cleanString(locationName);
        List<Node> listNodes = names.get(cleanedParam);
        List<Map<String, Object>> answer = new LinkedList<>();

        for (Node y : listNodes) {
            Map<String, Object> hold = new HashMap<>();
            hold.put("lat" , y.lat());
            hold.put("lon" , y.lon());
            hold.put("name" , y.name());
            hold.put("id" , y.id());
            answer.add(hold);
        }
        return answer;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
