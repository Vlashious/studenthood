import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.widgets.Display;

public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;
    private String name;

    public Graph() {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public boolean isNodesEmpty() {
        return nodes.isEmpty();
    }

    public boolean isEdgesEmpty() {
        return edges.isEmpty();
    }

    public List<Node> GetNodes() {
        return nodes;
    }

    public void AddNode() {
        Random random = new Random();
        Node node = new Node();
        node.x = random.nextInt(Display.getCurrent().getBounds().width);
        node.y = random.nextInt(Display.getCurrent().getBounds().height);
        nodes.add(node);
    }

    public void AddNode(int x, int y) {
        Random random = new Random();
        Node node = new Node();
        node.x = x;
        node.y = y;
        nodes.add(node);
    }

    public void AddNode(Node node) {
        nodes.add(node);
    }
}