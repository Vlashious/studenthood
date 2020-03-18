import java.io.Serializable;
import java.util.ArrayList;

public class Graph implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static Graph graph;

    ArrayList<Node> nodes = new ArrayList<Node>(); // Nodes of the graph.
    ArrayList<Edge> edges = new ArrayList<Edge>(); // Edges of the graph.

    public void deleteAdjacentEdges(Node node) { // Remove all adjacent edges of the node.
        ArrayList<Edge> connectedEdges = new ArrayList<Edge>();
        for (Edge edge : edges) {
            if(edge.nodeLeft == node || edge.nodeRight == node) {
                connectedEdges.add(edge);
            }
        }
        for (Edge edge : connectedEdges) {
            edges.remove(edge);
        }
    }
    public static Graph getInstance() { // Create a singleton.
        if(graph == null) {
            graph = new Graph();
        }
        //System.out.println(graph);
        return graph;
    }
}