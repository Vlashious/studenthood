import java.io.Serializable;

import org.eclipse.swt.graphics.Color;

public class Edge implements Serializable {
    private static final long serialVersionUID = 1L;
    public Node left;
    public Node right;
    public Color color;

    protected void finalize() {
        color.dispose();
    }
}