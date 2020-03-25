import org.eclipse.swt.graphics.Color;

public class Node {
    public int x;
    public int y;
    public Color color;
    public String name = "";

    protected void finalize() {
        color.dispose();
    }
}