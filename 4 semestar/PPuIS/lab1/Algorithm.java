import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.widgets.Display;

public class Algorithm implements Runnable {
    private Graph graph;
    private GraphController graphController;
    private int time = 0;
    final int NIL = -1;
    private LinkedList<Integer> adj[];
    private int V;
    
    public Algorithm(Graph graph, GraphController graphController) {
        this.graph = graph;
        this.graphController = graphController;
    }
    
    public void StartAlgorithm() {
        AdjacencyListGraph adjacencyListGraph = new AdjacencyListGraph(graph.nodes.size());
        for (Edge edge : graph.edges) {
            Node fromNode = edge.nodeLeft;
            Node toNode = edge.nodeRight;
            int fromIndex = graph.nodes.indexOf(fromNode);
            int toIndex = graph.nodes.indexOf(toNode);
            adjacencyListGraph.addEdge(fromIndex, toIndex);
        }
        V = adjacencyListGraph.V;
        adj = adjacencyListGraph.adj;
        Thread runner;
        runner = new Thread(this, "Algorithm");
        runner.start();
    }
    
    @Override
    public void run() {
        boolean visited[] = new boolean[V]; 
        int disc[] = new int[V]; 
        int low[] = new int[V]; 
        int parent[] = new int[V]; 
        boolean ap[] = new boolean[V];
  
        for (int i = 0; i < V; i++) {
            parent[i] = NIL; 
            visited[i] = false; 
            ap[i] = false; 
        } 
  
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                APUtil(i, visited, disc, low, parent, ap);
            }
        }
  
        Display.getDefault().syncExec(new Runnable(){
            
            @Override
            public void run() {
                for (int i = 0; i < V; i++) {
                    if (ap[i] == true) {
                        graphController.selectedNodes.add(graph.nodes.get(i));
                    }
                }
                graphController.visitedNodes.clear(); 
            }
        });
 
        
    }
    
    void APUtil(int u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]) {

        int children = 0;

        visited[u] = true;
        Display.getDefault().syncExec(new Runnable(){
    
            @Override
            public void run() {
                graphController.visitedNodes.add(graph.nodes.get(u));
            }
        });

        disc[u] = low[u] = ++time;

        Iterator<Integer> i = adj[u].iterator();
        while (i.hasNext()) {
            int v = i.next(); // v is current adjacent of u
            
            Display.getDefault().syncExec(new Runnable(){
    
                @Override
                public void run() {
                    graphController.selectedNodes.add(graph.nodes.get(u));
                    graphController.selectedNodes.add(graph.nodes.get(v));
                }
            });

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            graphController.selectedNodes.clear();
            if (!visited[v]) {
                children++; 
                parent[v] = u; 
                APUtil(v, visited, disc, low, parent, ap);
                low[u]  = Math.min(low[u], low[v]);
                if (parent[u] == NIL && children > 1) {
                    ap[u] = true;
                }
                if (parent[u] != NIL && low[v] >= disc[u]) {
                    ap[u] = true;
                }
            } 
  
            else if (v != parent[u]) {
                low[u]  = Math.min(low[u], disc[v]); 
            }
        } 
    } 
  
    void AP() {
         }
}