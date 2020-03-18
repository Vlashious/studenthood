import java.util.ArrayList;

import java.util.List;

public class GraphController {
    private Graph graph;
    List<Node> visitedNodes = new ArrayList<Node>();
    List<Node> selectedNodes = new ArrayList<Node>();

    public GraphController(Graph graph) {
        this.graph = graph;
    }

    public void AddNode() {
        Node node = new Node();
        graph.nodes.add(node);
    }

    public boolean isSelectedNodesEmpty() {
        return selectedNodes.isEmpty();
    }

    public void AddVisitedNode(Node node) {
        visitedNodes.add(node);
    }

    public void AddSelectedNode(Node node) {
        selectedNodes.add(node);
    }

    public void NewEdge() {
        Edge edge = new Edge();
        if(!selectedNodes.isEmpty()) {
            if(selectedNodes.size() == 2) {
                edge.nodeLeft = selectedNodes.get(0);
                edge.nodeRight = selectedNodes.get(1);
                graph.edges.add(edge);
            }
            else if(selectedNodes.size() > 2) {
                for (int i = 0; i < selectedNodes.size() - 1; i++) {
                    Edge newEdge = new Edge();
                    newEdge.nodeLeft = selectedNodes.get(i);
                    newEdge.nodeRight = selectedNodes.get(i + 1);
                    graph.edges.add(newEdge);
                }
            }
        }
    }

    public void SetNodeID(String newID) {
        for (Node node : selectedNodes) {
            node.Id = newID;
        }
    }

    public boolean AreConnected() {
        if(selectedNodes.size() == 2) {
            Edge t = new Edge();
            t.nodeLeft = selectedNodes.get(0);
            t.nodeRight = selectedNodes.get(1);
            for (Edge edge : graph.edges) {
                if(t.nodeLeft == edge.nodeLeft && t.nodeRight == edge.nodeRight || t.nodeLeft == edge.nodeRight && t.nodeRight == t.nodeLeft) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void SetEdgeWeight(int newWeight) {
        Edge t = new Edge();
        t.nodeLeft = selectedNodes.get(0);
        t.nodeRight = selectedNodes.get(1);
        for (Edge edge : graph.edges) {
            if(t.nodeLeft == edge.nodeLeft && t.nodeRight == edge.nodeRight || t.nodeLeft == edge.nodeRight && t.nodeRight == t.nodeLeft) {
                edge.weight = newWeight;
            }
        }
    }

    public void DeleteNode() {
        for (Node node : selectedNodes) {
            graph.nodes.remove(node);
            graph.deleteAdjacentEdges(node);
        }
        selectedNodes.clear();
    }

    public void DeleteEdge() {
        if(selectedNodes.size() == 2) {
            Edge t = new Edge();
            t.nodeLeft = selectedNodes.get(0);
            t.nodeRight = selectedNodes.get(1);
            for (Edge edge : graph.edges) {
                if((edge.nodeLeft == t.nodeLeft && edge.nodeRight == t.nodeRight) || (edge.nodeLeft == t.nodeRight && edge.nodeRight == t.nodeLeft)) {
                    graph.edges.remove(edge);
                    break;
                }
            }
        }
        selectedNodes.clear();
    }
}