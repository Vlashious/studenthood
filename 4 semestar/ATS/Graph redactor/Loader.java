import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class Loader {
    public Graph Load(Shell parent) {
        FileDialog fDialog = new FileDialog(parent, SWT.OPEN);
        fDialog.setFilterExtensions(new String[] {"*.gr", "*.*"});
        fDialog.setFilterNames(new String[] {"Graph Files", "All Files"});
        String filePath = fDialog.open();
        if(filePath != null) {
            try {
                FileInputStream FIStream = new FileInputStream(new File(filePath));
                ObjectInputStream OIStream = new ObjectInputStream(FIStream);
                Graph newGraph = (Graph) OIStream.readObject();
                int first = filePath.lastIndexOf("/") + 1;
                int last = filePath.lastIndexOf(".");
                String graphName = filePath.substring(first, last);
                System.out.println(graphName);
                newGraph.SetName(graphName);
                OIStream.close();
                return newGraph;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
            return null;
        }
        return null;
    }
}