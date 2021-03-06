import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.eclipse.swt.widgets.Display;

public class Graph implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Node> nodes;
    private List<Edge> NORientedEdges;
    private List<Edge> OREdges;

    private List<Edge> loopEdges;

    private String name = "";

    private List<Node> selectedNodes;

    public Graph() {
        nodes = new ArrayList<Node>();
        NORientedEdges = new ArrayList<Edge>();
        OREdges = new ArrayList<Edge>();

        loopEdges = new ArrayList<Edge>();

        selectedNodes = new ArrayList<Node>();
        name = "NewGraph";
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

    public boolean isNodesEmpty() {
        return nodes.isEmpty();
    }

    public boolean isEdgesEmpty() {
        return NORientedEdges.isEmpty() & OREdges.isEmpty() & loopEdges.isEmpty();
    }

    public boolean isSelectedNodesEmpty() {
        return selectedNodes.isEmpty();
    }

    public boolean nodesConnected(Node left, Node right) {
        for (Edge edge : NORientedEdges) {
            if(edge.left == left && edge.right == right || edge.left == right && edge.right == left) {
                return true;
            }
        }
        for (Edge edge : OREdges) {
            if(edge.left == left && edge.right == right) {
                return true;
            }
        }
        for (Edge edge : loopEdges) {
            if(edge.left == left && edge.right == right) {
                return true;
            }
        }
        return false;
    }

    public List<Node> GetNodes() {
        return nodes;
    }

    public List<Edge> GetEdges() {
        List<Edge> edges = new ArrayList<Edge>();
        edges.addAll(NORientedEdges);
        edges.addAll(OREdges);
        edges.addAll(loopEdges);
        return edges;
    }

    public List<Edge> GetNOREdges() {
        return NORientedEdges;
    }

    public List<Edge> GetOREdges() {
        return OREdges;
    }

    public List<Edge> GetLoopEdges() {
        return loopEdges;
    }

    public int GetEdgesNum() {
        return OREdges.size() + loopEdges.size() + NORientedEdges.size() / 2;
    }

    public void SetEdgeColor(int r, int g, int b) {
        if(selectedNodes.size() == 1) {
            Node n = selectedNodes.get(0);
            for (Edge edge : loopEdges) {
                if(edge.left == n) {
                    edge.r = r;
                    edge.g = g;
                    edge.b = b;
                }
            }
        }
        if(selectedNodes.size() == 2) {
            Node n1 = selectedNodes.get(0);
            Node n2 = selectedNodes.get(1);
            for (int i = 0; i < NORientedEdges.size(); i++) {
                if(NORientedEdges.get(i).left == n1 && NORientedEdges.get(i).right == n2 || NORientedEdges.get(i).left == n2 && NORientedEdges.get(i).right == n1) {
                    NORientedEdges.get(i).r = r;
                    NORientedEdges.get(i).g = g;
                    NORientedEdges.get(i).b = b;
                }
            }
            for (int i = 0; i < OREdges.size(); i++) {
                if(OREdges.get(i).left == n1 && OREdges.get(i).right == n2 || OREdges.get(i).left == n2 && OREdges.get(i).right == n1) {
                    OREdges.get(i).r = r;
                    OREdges.get(i).g = g;
                    OREdges.get(i).b = b;
                }
            }
        }
    }

    public Edge GetEdge() {
        if(selectedNodes.size() == 1) {
            Node n = selectedNodes.get(0);
            for (Edge edge : loopEdges) {
                if(edge.left == n) {
                    return edge;
                }
            }
        }
        if(selectedNodes.size() == 2) {
            Node n1 = selectedNodes.get(0);
            Node n2 = selectedNodes.get(1);
            for (int i = 0; i < NORientedEdges.size(); i++) {
                if(NORientedEdges.get(i).left == n1 && NORientedEdges.get(i).right == n2 || NORientedEdges.get(i).left == n2 && NORientedEdges.get(i).right == n1) {
                    return NORientedEdges.get(i);
                }
            }
            for (int i = 0; i < OREdges.size(); i++) {
                if(OREdges.get(i).left == n1 && OREdges.get(i).right == n2 || OREdges.get(i).left == n2 && OREdges.get(i).right == n1) {
                    return OREdges.get(i);
                }
            }
        }

        return null;
    }

    public List<Node> GetSelectedNodes() {
        return selectedNodes;
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
        for (int i = 0; i < NORientedEdges.size(); i++) {
            if(NORientedEdges.get(i).left == node || NORientedEdges.get(i).right == node) {
                NORientedEdges.remove(i);
                i--;
            }
        }
        for (int i = 0; i < OREdges.size(); i++) {
            if(OREdges.get(i).left == node || OREdges.get(i).right == node) {
                OREdges.remove(i);
                i--;
            }
        }
    }

    public void RemoveEdge() {
        if(selectedNodes.size() == 2) {
            Node n1 = selectedNodes.get(0);
            Node n2 = selectedNodes.get(1);
            for (int i = 0; i < NORientedEdges.size(); i++) {
                if(NORientedEdges.get(i).left == n1 && NORientedEdges.get(i).right == n2) {
                    NORientedEdges.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < NORientedEdges.size(); i++) {
                if (NORientedEdges.get(i).left == n2 && NORientedEdges.get(i).right == n1) {
                    NORientedEdges.remove(i);
                    i--;
                }
            }
            for (int i = 0; i < OREdges.size(); i++) {
                if(OREdges.get(i).left == n1 && OREdges.get(i).right == n2 || OREdges.get(i).left == n2 && OREdges.get(i).right == n1) {
                    OREdges.remove(i);
                    i--;
                }
            }
        }
    }

    public void RemoveAllEdges() {
        NORientedEdges.clear();
        OREdges.clear();
        loopEdges.clear();
    }

    public void AddNOREdge() {

        if(selectedNodes.size() == 1) {
            Edge edge = new Edge();
            edge.left = selectedNodes.get(0);
            edge.right = edge.left;
            loopEdges.add(edge);
            return;
        }

        if(selectedNodes.size() == 2) {
            Random random = new Random();
            Edge edge = new Edge();
            Edge edge1 = new Edge();
            edge.left = selectedNodes.get(0);
            edge.right = selectedNodes.get(1);
            edge.r = random.nextInt(255);
            edge.g = random.nextInt(255);
            edge.b = random.nextInt(255);
            edge1.left = selectedNodes.get(1);
            edge1.right = selectedNodes.get(0);
            edge1.r = edge.r;
            edge1.g = edge.g;
            edge1.b = edge.b;
            NORientedEdges.add(edge);
            NORientedEdges.add(edge1);
        }
    }

    public void AddNOREdge(Node left, Node right) {
        Edge edge = new Edge();
        edge.left = left;
        edge.right = right;
        Edge edge1 = new Edge();
        edge1.left = right;
        edge1.right = left;
        NORientedEdges.add(edge);
        //NORientedEdges.add(edge1);
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

    public void AddOREdge(Node left, Node right) {
        Edge edge = new Edge();
        edge.left = left;
        edge.right = right;
        OREdges.add(edge);
    }

    public int GetNodeDegree(Node node) {
        int degree = 0;
        for (int i = 0; i < NORientedEdges.size(); i++) {
            if(NORientedEdges.get(i).left == node) {
                degree++;
            }
        }
        for (int i = 0; i < OREdges.size(); i++) {
            if(OREdges.get(i).left == node) {
                degree++;
            }
        }
        for (int i = 0; i < loopEdges.size(); i++) {
            if(loopEdges.get(i).left == node) {
                degree += 2;
            }
        }

        return degree;
    }

    public int GetNodeDegree() {
        int degree = 0;
        if(selectedNodes.size() > 0) {
            Node node = selectedNodes.get(0);
            for (int i = 0; i < NORientedEdges.size(); i++) {
                if(NORientedEdges.get(i).left == node) {
                    degree++;
                }
            }
            for (int i = 0; i < OREdges.size(); i++) {
                if(OREdges.get(i).left == node) {
                    degree++;
                }
            }
            for (int i = 0; i < loopEdges.size(); i++) {
                if(loopEdges.get(i).left == node) {
                    degree += 2;
                }
            }
        }

        return degree;
    }

    public int GetGraphDegree() {
        int graphDegree = 0;
        for (Node node : nodes) {
            graphDegree += GetNodeDegree(node);
        }

        return graphDegree;
    }

    public String CheckIfTree() {
        CheckIfTreeAlgorithm algorithm = new CheckIfTreeAlgorithm(this);
        if(algorithm.isTree()) {
            return "This is a Tree!";
        }
        else {
            return "This is not a Tree!";
        }
    }

}