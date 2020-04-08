import java.util.ArrayList;
import java.util.List;

public class DecartProductAlgorithm {
    private Graph graph1;
    private Graph graph2;

    private List<List<Integer>> adjMatrixA = new ArrayList<List<Integer>>();
    private List<List<Integer>> adjMatrixB = new ArrayList<List<Integer>>();

    public DecartProductAlgorithm(Graph graph1, Graph graph2) {
        this.graph1 = graph1;
        this.graph2 = graph2;
        List<Node> nodes = graph1.GetNodes();
        int V = nodes.size();
        for(int i = 0; i < V; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < V; j++) {
                row.add(0);
            }
            adjMatrixA.add(row);
        }
        for (Edge edge : graph1.GetEdges()) {
            Node left = edge.left;
            Node right = edge.right;
            int leftIndex = nodes.indexOf(left);
            int rightIndex = nodes.indexOf(right);
            adjMatrixA.get(rightIndex).set(leftIndex, 1);
        }
        nodes = graph2.GetNodes();
        V = nodes.size();
        for(int i = 0; i < V; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < V; j++) {
                row.add(0);
            }
            adjMatrixB.add(row);
        }
        for (Edge edge : graph2.GetEdges()) {
            Node left = edge.left;
            Node right = edge.right;
            int leftIndex = nodes.indexOf(left);
            int rightIndex = nodes.indexOf(right);
            adjMatrixB.get(rightIndex).set(leftIndex, 1);
        }
    }

    public void Start() {
        List<List<Integer>> adjMatrixC = new ArrayList<List<Integer>>();

        for(int i = 0; i < CalculateMatrixSize(); i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < CalculateMatrixSize(); j++) {
                row.add(0);
            }
            adjMatrixC.add(row);
        } 
        KroneckerProduct(adjMatrixC);
        //return newGraph;
    }

    private int CalculateMatrixSize() {
        int matrixASize = adjMatrixA.size();
        int matrixBSize = adjMatrixB.size();
        return matrixASize * matrixBSize;
    }

    private void KroneckerProduct(List<List<Integer>> adjMatrixC) {
        int matrixASize = adjMatrixA.size();
        int matrixBSize = adjMatrixB.size();
        List<Integer> numbers = new ArrayList<Integer>();
        for(int i = 0; i < matrixASize; i++) {
            for(int j = 0; j < matrixASize; j++) {
                int numberA = adjMatrixA.get(i).get(j);
                for(int k = 0; k < matrixBSize; k++) {
                    for(int l = 0; l < matrixBSize; l++) {
                        int numberB = adjMatrixB.get(k).get(l);
                        numbers.add(numberA * numberB);
                    }
                }
            }
        }
    }

    private List<List<Integer>> InvertMatrix(List<List<Integer>> matrix) {
        List<List<Integer>> invertedMatrix = new ArrayList<List<Integer>>();
        
    }
}