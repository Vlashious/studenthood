import java.util.Random;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;

public class lab1 {

    static Graph graph = Graph.getInstance(); //Instantiating a graph singleton.
    static GraphController graphController = new GraphController(graph);
    static Algorithm algorithm = new Algorithm(graph, graphController);
    static Random random = new Random(); //Random instance.
    static int radius = 20; //Radius of the nodes.

    public static void main(String[] args) {
        //graph = new Graph();

        Display display = new Display(); // Initializing the display.
        Shell shell = new Shell(display, SWT.SHELL_TRIM);  //& ~SWT.RESIZE);
        shell.setSize(1280, 745);
        shell.setText("Graph editor");
        RowLayout rLayout = new RowLayout(SWT.VERTICAL);
        shell.setLayout(rLayout);

        ButtonClass buttons = new ButtonClass();
        Listeners listeners = new Listeners(graph, graphController, shell, algorithm);
        Composite buttonsLayer = buttons.Initialize(shell, listeners); // Creating the buttons.

//#region Canvas

        ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        scrolledComposite.setMinSize(4096, 2160);
        scrolledComposite.setLayout(new FillLayout());
        scrolledComposite.setLayoutData(new RowData(1270, 605));

        Canvas canvas = new Canvas(scrolledComposite, SWT.NONE);
        buttons.MenuInitialize(canvas); // Creating the right click menu.
        canvas.setLayoutData(new FillLayout());
        canvas.addPaintListener(new PaintListener(){ // Drawing on canvas listener.
        
            @Override
            public void paintControl(PaintEvent e) {
                if(!graph.edges.isEmpty()) { // If there are edges, draw them.
                    for(int i = 0; i < graph.edges.size(); i++) {
                        Edge t = graph.edges.get(i);
                        e.gc.drawLine(t.nodeLeft.x + radius / 2, t.nodeLeft.y + radius / 2, t.nodeRight.x + radius / 2, t.nodeRight.y + radius / 2);
                        e.gc.drawText(Integer.toString(t.weight), (t.nodeLeft.x + t.nodeRight.x) / 2, (t.nodeLeft.y + t.nodeRight.y) / 2);
                    }
                }
                if(!graph.nodes.isEmpty()) { // If there are nodes, draw them.
                    for(int i = 0; i < graph.nodes.size(); i++) {
                        e.gc.setBackground(display.getSystemColor(SWT.COLOR_DARK_MAGENTA));
                        e.gc.fillOval(graph.nodes.get(i).x, graph.nodes.get(i).y, radius, radius);
                        e.gc.setBackground(display.getSystemColor(SWT.INHERIT_FORCE));
                        e.gc.drawText(graph.nodes.get(i).Id, graph.nodes.get(i).x, graph.nodes.get(i).y + radius);
                    }
                }
                if(!graphController.visitedNodes.isEmpty()) { // If there are visited nodes, draw them on graph nodes.
                    e.gc.setBackground(display.getSystemColor(SWT.COLOR_DARK_YELLOW));
                    for(int i = 0; i < graphController.visitedNodes.size(); i++) {
                        e.gc.fillOval(graphController.visitedNodes.get(i).x, graphController.visitedNodes.get(i).y, radius, radius);
                    }
                }
                if(!graphController.selectedNodes.isEmpty()) { // If there are selected nodes, draw them on top of everything.
                    e.gc.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
                    for(int i = 0; i < graphController.selectedNodes.size(); i++) {
                        e.gc.fillOval(graphController.selectedNodes.get(i).x, graphController.selectedNodes.get(i).y, radius, radius);
                    }
                }
            }
        });

        canvas.addListener(SWT.MouseDown, new Listener(){ // Listener for checking what is clicked
                                                          // a node or the canvas itself.
        
            @Override
            public void handleEvent(Event e) {
                int mouseX = e.x;
                int mouseY = e.y;
                boolean clickedOnNode = false;
                int nodeClickedIndex = -1;
                for(int i = 0; i < graph.nodes.size(); i++) { // Loop through the nodes to check
                    if(mouseX > graph.nodes.get(i).x && mouseX < graph.nodes.get(i).x + radius && mouseY > graph.nodes.get(i).y && mouseY < graph.nodes.get(i).y + radius) {
                        clickedOnNode = true;
                        nodeClickedIndex = i;
                        break;
                    }
                    else clickedOnNode = false;
                }

                if(clickedOnNode == true && !graphController.selectedNodes.contains(graph.nodes.get(nodeClickedIndex))) {
                    graphController.selectedNodes.add(graph.nodes.get(nodeClickedIndex));
                } else if(clickedOnNode == false && e.button == 1) {
                    graphController.selectedNodes.clear();
                }

                if(e.count == 2) {
                    Node newNode = new Node();
                    newNode.x = mouseX - radius / 2;
                    newNode.y = mouseY - radius / 2;
                    graph.nodes.add(newNode);
                }
            }
        });
        scrolledComposite.setContent(canvas);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

//#endregion Canvas

        shell.addListener(SWT.Resize, new Listener() {

            @Override
            public void handleEvent(Event e) {
                Rectangle rect = shell.getClientArea();
                buttonsLayer.setLayoutData(new RowData(rect.width, (int) (rect.height * 0.1)));
                scrolledComposite.setLayoutData(new RowData((int)(rect.width * 0.9921875), (int) (rect.height * 0.87)));
                //System.out.println(rect);
            }
            
        });

        shell.open();
        while(!shell.isDisposed()) {
            canvas.redraw();
            if(!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}