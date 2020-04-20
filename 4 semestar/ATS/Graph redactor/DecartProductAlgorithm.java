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

    public Graph Start() {
        List<List<Integer>> adjMatrixC = KroneckerProduct1(adjMatrixA, adjMatrixB);
        List<List<Integer>> adjMatrixD = KroneckerProduct2(adjMatrixA, adjMatrixB);
        List<List<Integer>> adjMatrixFinal = MatrixSum(adjMatrixC, adjMatrixD);

        Graph newGraph = new Graph();
        newGraph = MatrixToGraph(newGraph, adjMatrixFinal);

        return newGraph;
    }

    private int CalculateMatrixSize() {
        int matrixASize = adjMatrixA.size();
        int matrixBSize = adjMatrixB.size();
        return matrixASize * matrixBSize;
    }

    private Graph MatrixToGraph(Graph graph, List<List<Integer>> matrix) {
        int nodesNum = matrix.size();
        for(int i = 0; i < nodesNum; i++) {
            Node node = new Node();
            graph.GetNodes().add(node);
        }
        for(int i = 0; i < nodesNum; i++) {
            for(int j = 0; j < nodesNum; j++) {
                if(matrix.get(i).get(j) == 1) {
                    Node left = graph.GetNodes().get(i);
                    Node right = graph.GetNodes().get(j);
                    graph.AddNOREdge(left, right);
                }
            }
        }
        return graph;
    }

    private List<List<Integer>> KroneckerProduct1(List<List<Integer>> adjMatrixA, List<List<Integer>> adjMatrixB) {

        List<List<Integer>> adjMatrixC = new ArrayList<List<Integer>>();

        for(int i = 0; i < CalculateMatrixSize(); i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < CalculateMatrixSize(); j++) {
                row.add(0);
            }
            adjMatrixC.add(row);
        } 

        int matrixASize = adjMatrixA.size();
        int matrixBSize = adjMatrixB.size();
        List<Integer> numbers = new ArrayList<Integer>();

        List<List<Integer>> invertedB = GetIdentityMatrix(matrixBSize);

        for(int i = 0; i < matrixASize; i++) {
            for(int j = 0; j < matrixASize; j++) {
                int numberA = adjMatrixA.get(i).get(j);
                for(int k = 0; k < matrixBSize; k+=matrixBSize) {
                    for(int l = 0; l < matrixBSize; l++) {
                        int numberB = invertedB.get(k).get(l);
                        numbers.add(numberA * numberB);
                    }
                }
            }
            for(int j = 0; j < matrixASize; j++) {
                int numberA = adjMatrixA.get(i).get(j);
                for(int k = 1; k < matrixBSize; k+=matrixBSize) {
                    for(int l = 0; l < matrixBSize; l++) {
                        int numberB = invertedB.get(k).get(l);
                        numbers.add(numberA * numberB);
                    }
                }
            }
        }
        int i = 0;
        for(int j = 0; j < adjMatrixC.size(); j++) {
            for(int k = 0; k < adjMatrixC.size(); k++) {
                adjMatrixC.get(j).set(k, numbers.get(i));
                i++;
            }
        }
        return adjMatrixC;
    }

    private List<List<Integer>> KroneckerProduct2(List<List<Integer>> adjMatrixA, List<List<Integer>> adjMatrixB) {

        List<List<Integer>> adjMatrixC = new ArrayList<List<Integer>>();

        for(int i = 0; i < CalculateMatrixSize(); i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < CalculateMatrixSize(); j++) {
                row.add(0);
            }
            adjMatrixC.add(row);
        } 

        int matrixASize = adjMatrixA.size();
        int matrixBSize = adjMatrixB.size();
        List<Integer> numbers = new ArrayList<Integer>();

        List<List<Integer>> invertedA = GetIdentityMatrix(matrixASize);

        for(int i = 0; i < matrixASize; i++) {
            for(int j = 0; j < matrixASize; j++) {
                int numberA = invertedA.get(i).get(j);
                for(int k = 0; k < matrixBSize; k+=matrixBSize) {
                    for(int l = 0; l < matrixBSize; l++) {
                        int numberB = adjMatrixB.get(k).get(l);
                        numbers.add(numberA * numberB);
                    }
                }
            }
            for(int j = 0; j < matrixASize; j++) {
                int numberA = invertedA.get(i).get(j);
                for(int k = 1; k < matrixBSize; k+=matrixBSize) {
                    for(int l = 0; l < matrixBSize; l++) {
                        int numberB = adjMatrixB.get(k).get(l);
                        numbers.add(numberA * numberB);
                    }
                }
            }
        }
        int i = 0;
        for(int j = 0; j < adjMatrixC.size(); j++) {
            for(int k = 0; k < adjMatrixC.size(); k++) {
                adjMatrixC.get(j).set(k, numbers.get(i));
                i++;
            }
        }
        return adjMatrixC;
    }

    private List<List<Integer>> GetIdentityMatrix(int size) {
        List<List<Integer>> invertedMatrix = new ArrayList<List<Integer>>();
        for(int i = 0; i < size; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < size; j++) {
                if(i == j) row.add(1);
                else row.add(0);
            }
            invertedMatrix.add(row);
        }
        return invertedMatrix;
    }

    private List<List<Integer>> MatrixSum(List<List<Integer>> adjMatrixA, List<List<Integer>> adjMatrixB) {

        List<List<Integer>> adjMatrixC = new ArrayList<List<Integer>>();

        for(int i = 0; i < CalculateMatrixSize(); i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < CalculateMatrixSize(); j++) {
                row.add(0);
            }
            adjMatrixC.add(row);
        } 
        for(int i = 0; i < adjMatrixA.size(); i++) {
            for(int j = 0; j < adjMatrixA.size(); j++) {
                adjMatrixC.get(i).set(j, adjMatrixA.get(i).get(j) + adjMatrixB.get(i).get(j));
            }
        }

        return adjMatrixC;
    }
}