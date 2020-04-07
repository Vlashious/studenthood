import java.util.ArrayList;
import java.util.List;

public class HamiltonianCycleAlgorithm {
    private int V;
    int path[];
    private List<List<Integer>> adjMatrix = new ArrayList<List<Integer>>();
    private Graph graph;

    public HamiltonianCycleAlgorithm(Graph graph) {
        this.graph = graph;
        List<Node> nodes = graph.GetNodes();
        V = nodes.size();
        for(int i = 0; i < V; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < V; j++) {
                row.add(0);
            }
            adjMatrix.add(row);
        }
        for (Edge edge : graph.GetEdges()) {
            Node left = edge.left;
            Node right = edge.right;
            int leftIndex = nodes.indexOf(left);
            int rightIndex = nodes.indexOf(right);
            adjMatrix.get(rightIndex).set(leftIndex, 1);
        }
    }
  
    /* A utility function to check if the vertex v can be 
       added at index 'pos'in the Hamiltonian Cycle 
       constructed so far (stored in 'path[]') */
    boolean isSafe(int v, int path[], int pos) { 
        /* Check if this vertex is an adjacent vertex of 
           the previously added vertex. */
        if(adjMatrix.get(path[pos-1]).get(v) == 0) {
            return false; 
        }
  
        /* Check if the vertex has already been included. 
           This step can be optimized by creating an array 
           of size V */
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false; 
            }
        }
  
        return true; 
    } 
  
    /* A recursive utility function to solve hamiltonian 
       cycle problem */
    boolean hamCycleUtil(int path[], int pos) { 
        /* base case: If all vertices are included in 
           Hamiltonian Cycle */
        if (pos == V) { 
            // And if there is an edge from the last included 
            // vertex to the first vertex 
            if(adjMatrix.get(path[pos-1]).get(path[0]) == 1) {
                return true; 
            }
            else {
                return false;
            } 
        } 
  
        // Try different vertices as a next candidate in 
        // Hamiltonian Cycle. We don't try for 0 as we 
        // included 0 as starting point in hamCycle() 
        for (int v = 1; v < V; v++) { 
            /* Check if this vertex can be added to Hamiltonian 
               Cycle */
            if (isSafe(v, path, pos)) { 
                path[pos] = v; 
  
                /* recur to construct rest of the path */
                if (hamCycleUtil(path, pos + 1) == true) 
                    return true; 
  
                /* If adding vertex v doesn't lead to a solution, 
                   then remove it */
                path[pos] = -1; 
            } 
        } 
  
        /* If no vertex can be added to Hamiltonian Cycle 
           constructed so far, then return false */
        return false; 
    } 
  
    /* This function solves the Hamiltonian Cycle problem using 
       Backtracking. It mainly uses hamCycleUtil() to solve the 
       problem. It returns false if there is no Hamiltonian Cycle 
       possible, otherwise return true and prints the path. 
       Please note that there may be more than one solutions, 
       this function prints one of the feasible solutions. */
    int hamCycle() { 
        path = new int[V]; 
        for (int i = 0; i < V; i++) {
            path[i] = -1; 
        }
  
        /* Let us put vertex 0 as the first vertex in the path. 
           If there is a Hamiltonian Cycle, then the path can be 
           started from any point of the cycle as the graph is 
           undirected */
        path[0] = 0; 
        if (hamCycleUtil(path, 1) == false) { 
            System.out.println("\nSolution does not exist"); 
            return 0; 
        }
        for(int i = 0; i < path.length; i++) {
            graph.SelectNode(graph.GetNodes().get(i));
        }
        return 1; 
    }

    void printSolution(int path[]) { 
        System.out.println("Solution Exists: Following" + 
                           " is one Hamiltonian Cycle"); 
        for (int i = 0; i < V; i++) {
            System.out.print(" " + path[i] + " "); 
        }
  
        // Let us print the first vertex again to show the 
        // complete cycle 
        System.out.println(" " + path[0] + " "); 
    } 
}