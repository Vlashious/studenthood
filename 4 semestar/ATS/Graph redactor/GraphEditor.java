import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GraphEditor {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        Window mWindow = new MainWindow(shell);
        mWindow.SetWindowName("Graph Editor");
        mWindow.SetSize(1280, 720);
        mWindow.SetContent();
        mWindow.StartWindow();
    }
}