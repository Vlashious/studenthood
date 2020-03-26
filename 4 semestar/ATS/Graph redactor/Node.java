import java.io.Serializable;

import org.eclipse.swt.graphics.Color;

public class Node implements Serializable {
    private static final long serialVersionUID = -4957584992625683746L;
    public int x;
    public int y;
    public Color color;
    public String name = "";

    protected void finalize() {
        color.dispose();
    }
}