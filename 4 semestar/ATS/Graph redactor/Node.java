import java.io.Serializable;
import java.util.Random;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x;
    public int y;
    public int r, g, b;
    public String name = "";
    public String content = "";

    public Node() {
        Random random = new Random();
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        x = random.nextInt(400);
        y = random.nextInt(400);
    }

    protected void finalize() {
        
    }
}