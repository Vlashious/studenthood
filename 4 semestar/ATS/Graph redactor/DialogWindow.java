import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class DialogWindow extends Window {

    protected String labelString;
    protected String textString;
    protected Graph graph;

    public DialogWindow(Shell shell, Graph graph) {
        super(shell);
        this.shell = shell;
        this.display = Display.getDefault();
        this.graph = graph;
    }

    public void SetText(String label, String text) {
        labelString = label;
        textString = text;
    }
    public abstract void SetContent();    
}