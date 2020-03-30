import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Shell;

public class SetNodeColorDialog extends DialogWindow {

    public SetNodeColorDialog(Shell shell, Graph graph) {
        super(shell, graph);
    }

    @Override
    public void SetContent() {
        ColorDialog cd = new ColorDialog(shell);
        cd.setText("Set Color");
        cd.setRGB(new RGB(255, 255, 255));

        RGB newColor = cd.open();
        
        for (Node node : graph.GetSelectedNodes()) {
            node.r = newColor.red;
            node.g = newColor.green;
            node.b = newColor.blue;
        }
    }
    
}