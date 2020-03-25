import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class GraphButton {

    private Graph graph = new Graph();
    public Button button;

    public GraphButton(Composite parent, int style, GraphCanvas canvas) {
        this.button = new Button(parent, style);
        button.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                canvas.SetGraph(graph);
            }
        });
    }

    public void SetLayoutData(GridData gridData) {
        button.setLayoutData(gridData);
    }

    public Graph GetGraph() {
        return graph;
    }

    public void SetGraph(Graph graph) {
        this.graph = graph;
    }
    
    public void SetText(String text) {
        button.setText(text);
    }
}