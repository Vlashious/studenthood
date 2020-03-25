import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

public class MainWindow extends Window {

    private Graph graph;
    private GraphButton activeGraphButton;

    public MainWindow(Shell shell) {
        super(shell);
        //this.parentShell = shell;
        shell = new Shell(shell);
        graph = new Graph();
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

        List<GraphButton> graphNameButtons = new ArrayList<GraphButton>();

        GraphCanvas graphCanvas = new GraphCanvas(shell, SWT.BORDER, graph);
        graphCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 12, 1));

        newCanvasButton.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                GraphButton button = new GraphButton(shell, SWT.PUSH, graphCanvas);
                button.SetLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
                activeGraphButton = button;
                graphNameButtons.add(activeGraphButton);
                Point point = shell.getSize();
                shell.pack();
                shell.setSize(point);
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {

            }
        });

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
                SetGraphNameDialog setGraphNameDialog = new SetGraphNameDialog(child, graph, activeGraphButton);
                setGraphNameDialog.SetWindowName("Name Graph");
                setGraphNameDialog.SetSize(400, 100);
                setGraphNameDialog.SetText("Name Graph", "My Graph");
                setGraphNameDialog.SetContent();
                setGraphNameDialog.StartWindow();
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                
            }
        });

        setNodeNameButton.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                Shell child = new Shell(shell);
                SetNodeNameDialog setNodeNameDialog = new SetNodeNameDialog(child, graph);
                setNodeNameDialog.SetWindowName("Name Node");
                setNodeNameDialog.SetSize(400, 100);
                setNodeNameDialog.SetText("New Name", "Node");
                setNodeNameDialog.SetContent();
                setNodeNameDialog.StartWindow();
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                
            }
        });

        deleteNodeButton.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                for (Node node : graph.GetSelectedNodes()) {
                    graph.RemoveNode(node);
                }
                graph.UnselectAllNodes();
                graphCanvas.redraw();
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                
            }
        });
    }
}