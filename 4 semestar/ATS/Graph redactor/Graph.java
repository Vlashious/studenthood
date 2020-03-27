import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Graph implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Node> nodes;
    private List<Edge> NORientedEdges;
    private List<Edge> OREdges;
    private String name = "";

    private List<Node> selectedNodes;
    private List<Edge> selectedEdges;

    public Graph() {
        nodes = new ArrayList<Node>();
        NORientedEdges = new ArrayList<Edge>();
        OREdges = new ArrayList<Edge>();
        selectedNodes = new ArrayList<Node>();
        selectedEdges = new ArrayList<Edge>();
        name = "New Graph";
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetName() {
        return name;
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

    public void UnselectAllEdges() {
        selectedEdges.clear();
    }

    public boolean isNodesEmpty() {
        return nodes.isEmpty();
    }

    public boolean isEdgesEmpty() {
        return NORientedEdges.isEmpty() & OREdges.isEmpty();
    }

    public boolean isSelectedNodesEmpty() {
        return selectedNodes.isEmpty();
    }

    public boolean isSelectedEdgesEmpty() {
        return selectedEdges.isEmpty();
    }

    public List<Node> GetNodes() {
        return nodes;
    }

    public List<Edge> GetEdges() {
        List<Edge> edges = new ArrayList<Edge>();
        edges.addAll(NORientedEdges);
        edges.addAll(OREdges);
        return edges;
    }

    public List<Edge> GetNOREdges() {
        return NORientedEdges;
    }

    public List<Edge> GetOREdges() {
        return OREdges;
    }

    public List<Node> GetSelectedNodes() {
        return selectedNodes;
    }

    public List<Edge> GetSelectedEdges() {
        return selectedEdges;
    }

    public void AddNode() {
        Random random = new Random();
        Node node = new Node();
        node.x = random.nextInt(Display.getCurrent().getBounds().width);
        node.y = random.nextInt(Display.getCurrent().getBounds().height);
        node.r = random.nextInt(255);
        node.g = random.nextInt(255);
        node.b = random.nextInt(255);
        nodes.add(node);
    }

    public void AddNode(int x, int y) {
        Random random = new Random();
        Node node = new Node();
        node.x = random.nextInt(x);
        node.y = random.nextInt(y);
        node.r = random.nextInt(255);
        node.g = random.nextInt(255);
        node.b = random.nextInt(255);
        nodes.add(node);
    }

    public void AddNode(Node node) {
        nodes.add(node);
    }

    public void RemoveNode(Node node) {
        nodes.remove(node);
    }

    public void AddNOREdge() {
        if(selectedNodes.size() == 2) {
            Random random = new Random();
            Edge edge = new Edge();
            edge.left = selectedNodes.get(0);
            edge.right = selectedNodes.get(1);
            edge.r = random.nextInt(255);
            edge.g = random.nextInt(255);
            edge.b = random.nextInt(255);
            NORientedEdges.add(edge);
        }
    }

    public void AddOREdge() {
        if(selectedNodes.size() == 2) {
            Random random = new Random();
            Edge edge = new Edge();
            edge.left = selectedNodes.get(0);
            edge.right = selectedNodes.get(1);
            edge.r = random.nextInt(255);
            edge.g = random.nextInt(255);
            edge.b = random.nextInt(255);
            OREdges.add(edge);
        }
    }

}