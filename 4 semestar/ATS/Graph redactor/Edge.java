import org.eclipse.swt.graphics.Color;

public class Edge {
    public Node left;
    public Node right;
    public Color color;

    protected void finalize() {
        color.dispose();
    }
}