package src.util.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import src.util.controller.Controller;

public class SaveListener implements Listener {
    
    private Shell parent;
    private Controller controller;

    public SaveListener(Shell parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
    }

    @Override
    public void handleEvent(Event e) {
        FileDialog fDialog = new FileDialog(parent, SWT.SAVE);
        fDialog.setFileName("yourXML.xml");
        fDialog.setFilterNames(new String[] {"XML File"});
        fDialog.setFilterExtensions(new String[] {"*.xml"});
        String outputFile = fDialog.open();
        controller.save(outputFile);
    }
}