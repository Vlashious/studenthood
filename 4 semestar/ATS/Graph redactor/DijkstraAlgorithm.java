import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithm {
    private int V;
    private List<Integer> maxDistance = new ArrayList<Integer>();
    private List<Integer> minDistance = new ArrayList<Integer>();

    private List<List<Integer>> adjMatrix = new ArrayList<List<Integer>>();

    public DijkstraAlgorithm(Graph graph) {
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
    
    private int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        int max = Integer.MIN_VALUE;
        for(int v = 0; v < V; v++) {
            if(sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
            if(sptSet[v] == false && dist[v] >= max) {
                max = dist[v];
            }
        }
        return min_index;
    }

    void dijkstra() {
        for(int src = 0; src < V; src++) {
            int dist[] = new int[V];

            Boolean sptSet[] = new Boolean[V];

            for(int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                sptSet[i] = false;
            }

            dist[src] = 0;

            for(int count = 0; count < V - 1; count++) {
                int u = minDistance(dist, sptSet);
                sptSet[u] = true;
                for(int v = 0; v < V; v++) {
                    if(!sptSet[v] && adjMatrix.get(u).get(v) != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + adjMatrix.get(u).get(v) < dist[v]) {
                        dist[v] = dist[u] + adjMatrix.get(u).get(v);
                    }
                }
            }
            maxDistance.add(getMax(dist));
            minDistance.add(getMin(dist));
        }
    }

    private int getMax(int[] array) {
        int maxValue = array[0];
        for(int i = 0; i < array.length; i++) {
            if(array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return maxValue;
    }

    private int getMax(List<Integer> array) {
        int maxValue = array.get(0);
        for(int i = 0; i < array.size(); i++) {
            if(array.get(i) > maxValue) {
                maxValue = array.get(i);
            }
        }
        return maxValue;
    }

    public int getRadius() {
        return getMin(maxDistance);
    }

    public int getDiameter() {
        return getMax(maxDistance);
    }

    private int getMin(int[] array) {
        int minValue = array[0];
        for(int i = 0; i < array.length; i++) {
            if(array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    private int getMin(List<Integer> array) {
        int minValue = array.get(0);
        for(int i = 0; i < array.size(); i++) {
            if(array.get(i) < minValue) {
                minValue = array.get(i);
            }
        }
        return minValue;
    }

    public int getCenterIndex() {
        List<Integer> array = maxDistance;
        int minValue = array.get(0);
        int index = 0;
        for(int i = 0; i < array.size(); i++) {
            if(array.get(i) < minValue) {
                minValue = array.get(i);
                index = i;
            }
        }
        return index;
    }
}