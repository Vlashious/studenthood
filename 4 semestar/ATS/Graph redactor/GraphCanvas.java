import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class GraphCanvas extends Composite {

    private Canvas canvas;
    private List<Graph> graphs = new ArrayList<Graph>();
    private Graph currentGraph;
    private int radius = 20;

    public void Redraw() {
        canvas.redraw();
    }

    public int GetGraphNum() {
        return graphs.size();
    }

    public int GetGraphIndex() {
        return graphs.indexOf(currentGraph);
    }

    public void SetCurrentGraph(Graph graph) {
        graphs.set(GetGraphIndex(), graph);
        this.currentGraph = graph;
        redraw();
    }

    public void CreateNewGraph() {
        graphs.add(new Graph());
        currentGraph = graphs.get(graphs.size() - 1);
    }

    public void SetCurrentGraph(int index) {
        this.currentGraph = graphs.get(index);
        redraw();
    }

    public Graph GetGraphAtIndex(int index) {
        return graphs.get(index);
    }

    public Graph GetCurrentGraph() {
        return currentGraph;
    }

    public GraphCanvas(Composite parent, int style) {
        super(parent, style);

        StackLayout layout = new StackLayout();

        this.setLayout(layout);
        canvas = new Canvas(this, style);
        layout.topControl = canvas;
        canvas.addPaintListener(new PaintListener(){
        
            @Override
            public void paintControl(PaintEvent e) {
                if(!currentGraph.isNodesEmpty()) {
                    for (Node node : currentGraph.GetNodes()) {
                        e.gc.setBackground(new Color(Display.getDefault(), node.r, node.g, node.b));
                        e.gc.fillOval(node.x, node.y, radius, radius);
                        e.gc.drawText(node.name, node.x - radius, node.y + radius);
                    }
                }
                if(!currentGraph.isSelectedNodesEmpty()) {
                    for (Node node : currentGraph.GetSelectedNodes()) {
                        e.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
                        e.gc.setLineWidth(4);
                        e.gc.drawOval(node.x, node.y, radius, radius);
                    }
                }
            }
        });

        canvas.addMouseMoveListener(new MouseMoveListener(){
        
            @Override
            public void mouseMove(MouseEvent e) {
                if(e.stateMask == 0x80000 & !currentGraph.isSelectedNodesEmpty()) {
                    Node node = currentGraph.GetSelectedNodes().get(0);
                    node.x = e.x - radius / 2;
                    node.y = e.y - radius / 2;
                    redraw();
                }
            }
        });

        canvas.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                boolean isNodeClicked = false;
                for (Node node : currentGraph.GetNodes()) {
                    if(e.x < node.x + radius & e.y < node.y + radius & e.x > node.x & e.y > node.y) {
                        currentGraph.SelectNode(node);
                        redraw();
                        isNodeClicked = true;
                    }
                }
                if(!isNodeClicked) {
                    currentGraph.UnselectAllNodes();
                    redraw();
                }
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                Node node = new Node();
                node.x = e.x - radius / 2;
                node.y = e.y - radius / 2;
                Random random = new Random();
                node.r = random.nextInt(255);
                node.g = random.nextInt(255);
                node.b = random.nextInt(255);
                currentGraph.AddNode(node);
                redraw();
            }
        });

    }
}