package src.util.listener;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import src.util.controller.Controller;
import src.util.view.Window;

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
        FileDialog fDialog = new FileDialog(parent, SWT.OPEN);
        fDialog.setFilterNames(new String[] { "XML File" });
        fDialog.setFilterExtensions(new String[] { "*.xml" });
        String inputFile = fDialog.open();
        controller.load(inputFile);
        try {
            window.updateTable();
        } catch (ClassNotFoundException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}