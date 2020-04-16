package src.listener;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import src.controller.Controller;
import src.view.Window;

public class LoadListener implements Listener {

    private Shell parent;
    private Controller controller;
    private Window window;

    public LoadListener(Shell parent, Window window, Controller controller) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent);
        child.setLayout(new GridLayout(2, true));
        child.setSize(200, 100);

        Label label = new Label(child, SWT.NONE);
        label.setText("File name:");
        label.setLayoutData(new GridData());

        Text text = new Text(child, SWT.NONE);
        text.setText("Students.xml");
        text.setLayoutData(new GridData());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == 13) {
                    String filePath = text.getText();
                    try {
                        controller.load(filePath);
                    } catch (ClassNotFoundException | IOException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    child.dispose();
                    try {
                        window.updateTable();
                    } catch (ClassNotFoundException | IOException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        
            @Override
            public void keyPressed(KeyEvent arg0) {

            }
        });
        child.open();
    }
}