import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class graphEditor {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        Window mWindow = new MainWindow(shell);
        mWindow.SetWindowName("Graph Editor");
        mWindow.SetSize(display.getBounds().width - 100, display.getBounds().height - 100);
        mWindow.SetContent();
        mWindow.StartWindow();
    }
}