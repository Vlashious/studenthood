import java.util.*;

public class ConvertToTreeAlgorithm {
    private Graph graph;

    public ConvertToTreeAlgorithm(Graph graph) {
        this.graph = graph;
        Convert();
    }

    private void Convert() {
        List<Node> unusedNodes = new ArrayList<Node>();
        unusedNodes.addAll(graph.GetNodes());
        List<Node> usedNodes = new ArrayList<Node>();

        graph.RemoveAllEdges();

        for(int i = 0; i < graph.GetNodes().size(); i++) {
            Node tNode = graph.GetNodes().get(i);
            unusedNodes.remove(tNode);
            usedNodes.add(tNode);
            for(int j = 0; j < 2; j++) {
                try {
                    if(!usedNodes.contains(unusedNodes.get(j))) {
                        graph.AddOREdge(tNode, unusedNodes.get(j));
                        usedNodes.add(unusedNodes.get(j));
                        unusedNodes.remove(j);
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
    }
}