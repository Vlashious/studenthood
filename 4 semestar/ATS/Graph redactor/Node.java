import java.io.Serializable;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x;
    public int y;
    public int r, g, b;
    public String name = "";

    protected void finalize() {
        
    }
}