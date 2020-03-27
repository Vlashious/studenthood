import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class Saver {
    public void Save(Graph graph, Shell parent) {
        FileDialog fDialog = new FileDialog(parent, SWT.SAVE);
        fDialog.setFileName(graph.GetName());
        fDialog.setFilterNames(new String[] {"Graph Files", "All Files"});
        fDialog.setFilterExtensions(new String[] {"*.gr", "*.*"});
        String filePath = fDialog.open();
        if(filePath != null) {
            try {
                FileOutputStream FOStream = new FileOutputStream(new File(filePath));
                ObjectOutputStream OOStream = new ObjectOutputStream(FOStream);
                int first = filePath.lastIndexOf("/") + 1;
                int last = filePath.lastIndexOf(".");
                String graphName = filePath.substring(first, last);
                graph.SetName(graphName);
                OOStream.writeObject(graph);
                OOStream.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}