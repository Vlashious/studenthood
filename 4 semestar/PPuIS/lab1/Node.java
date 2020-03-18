import java.io.Serializable;
import java.util.Random;

public class Node implements Serializable {

    Random random = new Random();
    
    private static final long serialVersionUID = 1L;
    
    int x;
    int y;
    public String Id = "";

    public Node() {
        x = random.nextInt(1280);
        y = random.nextInt(613);
    }
}