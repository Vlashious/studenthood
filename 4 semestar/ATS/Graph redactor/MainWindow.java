import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class MainWindow extends Window {

    private int index;

    public MainWindow(Shell shell) {
        super(shell);
        //this.parentShell = shell;
        shell = new Shell(shell);
    }

    public void SetContent() {
        GridLayout gridLayout = new GridLayout(13, true);
        shell.setLayout(gridLayout);

        Button newGraphButton = new Button(shell, SWT.PUSH);
        newGraphButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        newGraphButton.setText("New Graph");

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

        Button setNodeColorButton = new Button(shell, SWT.PUSH);
        setNodeColorButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setNodeColorButton.setText("Set Node Color");

        Button setEdgeColorButton = new Button(shell, SWT.PUSH);
        setEdgeColorButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        setEdgeColorButton.setText("Set Edge Color");

        Button seeGraphInfoButton = new Button(shell, SWT.PUSH);
        seeGraphInfoButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        seeGraphInfoButton.setText("Info");

        GraphCanvas graphCanvas = new GraphCanvas(shell, SWT.BORDER);
        graphCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 13, 1));
        graphCanvas.CreateNewGraph();

        List<Button> graphButtons = new ArrayList<Button>();
        Point point = shell.getSize();
        Button button = new Button(shell, SWT.PUSH);
        button.setText(graphCanvas.GetCurrentGraph().GetName());
        button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        graphButtons.add(button);
        shell.pack();
        shell.setSize(point);

        newGraphButton.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                graphCanvas.CreateNewGraph();
                graphCanvas.Redraw();
                CreateGraphButtons(graphCanvas.GetGraphNum(), graphButtons, graphCanvas);
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
                graphCanvas.GetCurrentGraph().AddNode(graphCanvas.getSize().x, graphCanvas.getSize().y);
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
                SetGraphNameDialog setGraphNameDialog = new SetGraphNameDialog(child, graphCanvas.GetCurrentGraph());
                setGraphNameDialog.SetWindowName("Name Graph");
                setGraphNameDialog.SetSize(400, 100);
                setGraphNameDialog.SetText("Name Graph", "My Graph");
                setGraphNameDialog.SetContent();
                setGraphNameDialog.SetButton(graphButtons.get(graphCanvas.GetGraphIndex()));
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
                SetNodeNameDialog setNodeNameDialog = new SetNodeNameDialog(child, graphCanvas.GetCurrentGraph());
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
                Graph graph = graphCanvas.GetCurrentGraph();
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

        newNotOrientedEdgeButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                graphCanvas.GetCurrentGraph().AddNOREdge();
                graphCanvas.redraw();
            }
        });

        newOrientedEdgeButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                graphCanvas.GetCurrentGraph().AddOREdge();
                graphCanvas.redraw();
            }
        });

        deleteEdgeButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                graphCanvas.GetCurrentGraph().RemoveEdge();
                graphCanvas.redraw();
            }
        });

        setNodeColorButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                SetNodeColorDialog setNodeColorDialog = new SetNodeColorDialog(shell, graphCanvas.GetCurrentGraph());
                setNodeColorDialog.SetContent();
                setNodeColorDialog.StartWindow();
            }
        });

        setEdgeColorButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                SetEdgeColorButton setEdgeColorButton = new SetEdgeColorButton(shell, graphCanvas.GetCurrentGraph());
                setEdgeColorButton.SetContent();
                setEdgeColorButton.StartWindow();
            }
        });

        saveGraphButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                Saver saver = new Saver();
                saver.Save(graphCanvas.GetCurrentGraph(), shell);
                UpdateButtons(graphButtons, graphCanvas);
            }
        });

        loadGraphButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event e) {
                Loader loader = new Loader();
                Graph loadedGraph = loader.Load(shell);
                graphCanvas.SetCurrentGraph(loadedGraph);
                graphCanvas.Redraw();
                CreateGraphButtons(graphCanvas.GetGraphNum(), graphButtons, graphCanvas);
                UpdateButtons(graphButtons, graphCanvas);
            }
        });
    }

    private void UpdateButtons(List<Button> graphButtons, GraphCanvas graphCanvas) {
        for(int i = 0; i < graphButtons.size(); i++) {
            graphButtons.get(i).setText(graphCanvas.GetGraphAtIndex(i).GetName());
        }
    }

    private void CreateGraphButtons(int num, List<Button> graphButtons, GraphCanvas graphCanvas) {
        for(int i = 0; i < graphButtons.size(); i++) {
            graphButtons.get(i).dispose();
        }
        graphButtons.clear();
        Point point = shell.getSize();
        for(int i = 0; i < num; i++) {
            Button button = new Button(shell, SWT.PUSH);
            button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            button.setText(graphCanvas.GetGraphAtIndex(i).GetName());
            button.addListener(SWT.MouseUp, new Listener(){
            
                @Override
                public void handleEvent(Event e) {
                    index = graphButtons.indexOf(button);
                    graphCanvas.SetCurrentGraph(index);
                    graphCanvas.Redraw();
                }
            });
            graphButtons.add(button);
        }
        shell.pack();
        shell.setSize(point);
    }

}
