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
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
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

    /*
    Returns graph by it's name.
    @param name
    */
    public Graph GetGraphByName(String name) {
        for (Graph graph : graphs) {
            if(graph.GetName().equals(name)) {
                return graph;
            }
        }
        return null;
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
                if(!currentGraph.isEdgesEmpty()) {
                    for (Edge edge : currentGraph.GetNOREdges()) {
                        e.gc.setBackground(new Color(Display.getDefault(), edge.r, edge.g, edge.b));
                        e.gc.setForeground(new Color(Display.getDefault(), edge.r, edge.g, edge.b));
                        e.gc.setLineWidth(5);
                        e.gc.drawLine(edge.left.x + radius / 2, edge.left.y + radius / 2, edge.right.x + radius / 2, edge.right.y + radius / 2);
                    }
                    for (Edge edge : currentGraph.GetOREdges()) {
                        e.gc.setBackground(new Color(Display.getDefault(), edge.r, edge.g, edge.b));
                        e.gc.setForeground(new Color(Display.getDefault(), edge.r, edge.g, edge.b));
                        e.gc.setLineWidth(5);
                        drawArrow(e.gc, edge.left.x + radius / 2, edge.left.y + radius / 2, edge.right.x + radius / 2, edge.right.y + radius / 2, 40, Math.toRadians(10));
                    }
                    for (Edge edge : currentGraph.GetLoopEdges()) {
                        e.gc.setForeground(new Color(Display.getDefault(), edge.r, edge.g, edge.b));
                        e.gc.setBackground(new Color(Display.getDefault(), edge.r, edge.g, edge.b));
                        e.gc.setLineWidth(5);
                        e.gc.drawOval(edge.left.x, edge.left.y, 50, 50);
                    }
                }
                if(!currentGraph.isNodesEmpty()) {
                    for (Node node : currentGraph.GetNodes()) {
                        e.gc.setBackground(new Color(Display.getDefault(), node.r, node.g, node.b));
                        e.gc.fillOval(node.x, node.y, radius, radius);
                        e.gc.drawText(node.name, node.x - radius, node.y + radius);
                    }
                }
                if(!currentGraph.isSelectedNodesEmpty()) {
                    for (Node node : currentGraph.GetSelectedNodes()) {
                        e.gc.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
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

    public void drawArrow(GC gc, int x1, int y1, int x2, int y2, double arrowLength, double arrowAngle) {
        double theta = Math.atan2(y2 - y1, x2 - x1);
        double offset = (arrowLength - 2) * Math.cos(arrowAngle);
    
        gc.drawLine(x1, y1, (int)(x2 - offset * Math.cos(theta)), (int)(y2 - offset * Math.sin(theta)));
    
        Path path = new Path(gc.getDevice());
        path.moveTo((float)(x2 - arrowLength * Math.cos(theta - arrowAngle)), (float)(y2 - arrowLength * Math.sin(theta - arrowAngle)));
        path.lineTo((float)x2, (float)y2);
        path.lineTo((float)(x2 - arrowLength * Math.cos(theta + arrowAngle)), (float)(y2 - arrowLength * Math.sin(theta + arrowAngle)));
        path.close();
    
        gc.fillPath(path);
    
        path.dispose();
    }
}