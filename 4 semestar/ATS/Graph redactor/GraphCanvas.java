import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class GraphCanvas extends Composite {

    private Canvas canvas;
    Graph graph;
    private int radius = 20;

    public void Redraw() {
        canvas.redraw();
    }

    public void SetGraph(Graph graph) {
        this.graph = graph;
        redraw();
    }

    public GraphCanvas(Composite parent, int style, Graph graph) {
        super(parent, style);
        this.setLayout(new FillLayout());
        canvas = new Canvas(this, style);
        this.graph = graph;
        canvas.addPaintListener(new PaintListener(){
        
            @Override
            public void paintControl(PaintEvent e) {
                if(!graph.isNodesEmpty()) {
                    for (Node node : graph.GetNodes()) {
                        e.gc.setBackground(node.color);
                        e.gc.fillOval(node.x, node.y, radius, radius);
                        e.gc.drawText(node.name, node.x - radius, node.y + radius);
                    }
                }
                if(!graph.isSelectedNodesEmpty()) {
                    for (Node node : graph.GetSelectedNodes()) {
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
                if(e.stateMask == 0x80000 & !graph.isSelectedNodesEmpty()) {
                    Node node = graph.GetSelectedNodes().get(0);
                    node.x = e.x;
                    node.y = e.y;
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
                for (Node node : graph.GetNodes()) {
                    if(e.x < node.x + radius & e.y < node.y + radius & e.x > node.x & e.y > node.y) {
                        graph.SelectNode(node);
                        redraw();
                        isNodeClicked = true;
                    }
                }
                if(!isNodeClicked) {
                    graph.UnselectAllNodes();
                    redraw();
                }
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                Node node = new Node();
                node.x = e.x - radius / 2;
                node.y = e.y - radius / 2;
                Random random = new Random();
                node.color = new Color(Display.getDefault(), random.nextInt(255), random.nextInt(255), random.nextInt(255));
                graph.AddNode(node);
                redraw();
            }
        });

    }
}