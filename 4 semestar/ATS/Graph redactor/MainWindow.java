import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class MainWindow extends Window {

    public MainWindow(Shell shell) {
        super(shell);
        //this.parentShell = shell;
        shell = new Shell(shell);
    }

    public void SetContent() {
        GridLayout gridLayout = new GridLayout(12, true);
        shell.setLayout(gridLayout);

        Button newCanvasButton = new Button(shell, SWT.PUSH);
        newCanvasButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        newCanvasButton.setText("New Graph");

        Button setGraphNameButton = new Button(shell, SWT.PUSH);
        setGraphNameButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setGraphNameButton.setText("Set Graph Name");

        Button saveGraphButton = new Button(shell, SWT.PUSH);
        saveGraphButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        saveGraphButton.setText("Save...");

        Button loadGraphButton = new Button(shell, SWT.PUSH);
        loadGraphButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        loadGraphButton.setText("Load...");

        Button newNodeButton = new Button(shell, SWT.PUSH);
        newNodeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        newNodeButton.setText("New Node");

        Button deleteNodeButton = new Button(shell, SWT.PUSH);
        deleteNodeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        deleteNodeButton.setText("Delete Node");

        Button setNodeNameButton = new Button(shell, SWT.PUSH);
        setNodeNameButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setNodeNameButton.setText("Set Node Name");

        Button newNotOrientedEdgeButton = new Button(shell, SWT.PUSH);
        newNotOrientedEdgeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        newNotOrientedEdgeButton.setText("New UNOR Edge");

        Button newOrientedEdgeButton = new Button(shell, SWT.PUSH);
        newOrientedEdgeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        newOrientedEdgeButton.setText("New OR Edge");

        Button deleteEdgeButton = new Button(shell, SWT.PUSH);
        deleteEdgeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        deleteEdgeButton.setText("Delete Edge");

        Button setColorButton = new Button(shell, SWT.PUSH);
        setColorButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setColorButton.setText("Set Color");

        Button seeGraphInfoButton = new Button(shell, SWT.PUSH);
        seeGraphInfoButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        seeGraphInfoButton.setText("Info");

        GraphCanvas graphCanvas = new GraphCanvas(shell, SWT.NONE);
        graphCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 12, 12));

        newNodeButton.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                graphCanvas.graph.AddNode();
                graphCanvas.Redraw();
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {

            }
        });

        setGraphNameButton.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                Shell child = new Shell(shell);
                DialogWindow dWindow = new DialogWindow(child);
                dWindow.SetWindowName("Name Graph");
                dWindow.SetSize(400, 200);
                dWindow.SetText("Name Graph", "New Graph");
                dWindow.SetContent();
                dWindow.StartWindow();
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });

        setNodeNameButton.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                Shell child = new Shell(shell);
                DialogWindow dWindow = new DialogWindow(child);
                dWindow.SetWindowName("Name Node");
                dWindow.SetSize(400, 200);
                dWindow.SetText("New Name", "Node");
                dWindow.SetContent();
                dWindow.StartWindow();
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
    }
}