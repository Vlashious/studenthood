import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Shell;

public class SetEdgeColorButton extends DialogWindow {

    public SetEdgeColorButton(Shell shell, Graph graph) {
        super(shell, graph);
    }

    @Override
    public void SetContent() {
        ColorDialog cd = new ColorDialog(shell);
        cd.setText("Set Color");
        cd.setRGB(new RGB(255, 255, 255));
        RGB newColor = cd.open();
        if(newColor != null) {
            Edge edge = graph.GetEdge();
            edge.r = newColor.red;
            edge.g = newColor.green;
            edge.b = newColor.blue;
        }
    }
    
}