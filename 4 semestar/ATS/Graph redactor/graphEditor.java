import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class graphEditor {
    public static void main(String[] args) {
        Display display = new Display();
        Window mWindow = new MainWindow(display);
        mWindow.SetWindowName("Graph Editor");
        mWindow.SetSize(display.getBounds().width - 100, display.getBounds().height - 100);
        mWindow.SetContent();
        mWindow.StartWindow();
    }
}