import org.eclipse.swt.graphics.Color;

public class Node {
    public int x;
    public int y;
    public Color color;

    protected void finalize() {
        color.dispose();
    }
}