import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class Window {
    protected Shell shell;
    protected Display display;

    public Window(Shell shell) {
        this.shell = shell;
        display = Display.getDefault();
    }

    public void StartWindow() {
        shell.open();
        while(!shell.isDisposed()) {
            if(!display.readAndDispatch()) display.sleep();
        }
    }

    public void SetWindowName(String name) {
        shell.setText(name);
    }

    public void SetSize(int width, int height) {
        shell.setSize(width, height);
    }

    public abstract void SetContent();
}