import java.io.Serializable;
import java.util.List;

public class Edge implements Serializable {
    private static final long serialVersionUID = 1L;
    public Node left;
    public Node right;
    public int r, g, b;
}