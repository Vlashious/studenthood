import java.util.Iterator;
import java.util.LinkedList;

public class CheckIfTreeAlgorithm {
    private Graph graph;
    private int V;
    private LinkedList<Integer> adj[];

    public CheckIfTreeAlgorithm(Graph graph) {
        this.graph = graph;
        V = graph.GetNodes().size();
        adj = new LinkedList[V];
        for(int i = 0; i < V; ++i) {
            adj[i] = new LinkedList();
        }
        Convert();
    }

    private void Convert() {
        for (Edge edge : graph.GetEdges()) {
            Node left = edge.left;
            Node right = edge.right;
            int leftIndex = graph.GetNodes().indexOf(left);
            int rightIndex = graph.GetNodes().indexOf(right);
            adj[leftIndex].add(rightIndex);
            //adj[rightIndex].add(leftIndex);
        }
    }

    private Boolean isCyclicUtil(int v, Boolean visited[], int parent) {
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj[v].iterator();
        while(it.hasNext()) {
            i = it.next();
            if(!visited[i]) {
                if(isCyclicUtil(i, visited, v)) {
                    return true;
                }
            }
            else if(i != parent) {
                return true;
            }
        }
        return false;
    }

    public Boolean isTree() {
        Boolean visited[] = new Boolean[V];
        for(int i = 0; i < V; i++) {
            visited[i] = false;
        }
        if(isCyclicUtil(0, visited, -1)) {
            return false;
        }
        for(int u = 0; u < V; u++) {
            if(!visited[u]) {
                return false;
            }
        }
        return true;
    }
}