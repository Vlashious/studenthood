import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class Window {
    protected Shell shell;
    protected Display display;
    protected Shell parentShell;

    public Window(Display display, int style) {
        this.display = display;
        shell = new Shell(display, style);
    }

    public Window(Display display) {
        this.display = display;
        shell = new Shell(display);
    }

    public Window(Shell shell, int style) {
        this.parentShell = shell;
        shell = new Shell(shell);
    }

    public Window(Shell shell) {
        this.parentShell = shell;
        shell = new Shell(shell);
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