import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.widgets.Display;

public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;
    private String name;

    private List<Node> selectedNodes;
    private List<Edge> selectedEdges;

    public Graph() {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        selectedNodes = new ArrayList<Node>();
        selectedEdges = new ArrayList<Edge>();
    }

    public void SelectNode(Node node) {
        selectedNodes.add(node);
    }

    public void UnselectAllNodes() {
        selectedNodes.clear();
    }

    public void SelectEdge(Edge edge) {
        selectedEdges.add(edge);
    }

    public String GetName() {
        return name;
    }

    public boolean isNodesEmpty() {
        return nodes.isEmpty();
    }

    public boolean isEdgesEmpty() {
        return edges.isEmpty();
    }

    public boolean isSelectedNodesEmpty() {
        return selectedNodes.isEmpty();
    }

    public List<Node> GetNodes() {
        return nodes;
    }

    public List<Node> GetSelectedNodes() {
        return selectedNodes;
    }

    public void AddNode() {
        Random random = new Random();
        Node node = new Node();
        node.x = random.nextInt(Display.getCurrent().getBounds().width);
        node.y = random.nextInt(Display.getCurrent().getBounds().height);
        nodes.add(node);
    }

    public void AddNode(int x, int y) {
        Node node = new Node();
        node.x = x;
        node.y = y;
        nodes.add(node);
    }

    public void AddNode(Node node) {
        nodes.add(node);
    }
}