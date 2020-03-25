import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SetNodeNameDialog extends DialogWindow {

    public SetNodeNameDialog(Shell shell, Graph graph) {
        super(shell, graph);
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
        text.addListener(SWT.KeyDown, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                if(e.keyCode == 13) {
                    for (Node node : graph.GetSelectedNodes()) {
                        node.name = text.getText();
                    }
                    shell.dispose();
                }
            }
        });
    }

}