import java.io.Serializable;

public class Edge implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Node nodeLeft;
    Node nodeRight;
    int weight = 1;
}