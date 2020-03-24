import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DialogWindow extends Window {

    private String labelString;
    private String textString;

    public DialogWindow(Shell shell) {
        super(shell);
        this.shell = shell;
        this.display = Display.getDefault();
    }

    public void SetText(String label, String text) {
        labelString = label;
        textString = text;
    }

    @Override
    public void SetContent() {
        GridLayout gridLayout = new GridLayout(1, true);
        shell.setLayout(gridLayout);

        Label label = new Label(shell, SWT.NONE);
        label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        label.setText(labelString);

        Text text = new Text(shell, SWT.NONE);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        text.setText(textString);
    }
    
}