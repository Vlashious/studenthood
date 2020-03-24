import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class GraphCanvas extends Composite {

    private Canvas canvas;
    Graph graph;
    private int radius = 20;

    public GraphCanvas(Composite parent, int style) {
        super(parent, style);
        this.setLayout(new FillLayout());
        canvas = new Canvas(this, style);
        graph = new Graph();
        canvas.addPaintListener(new PaintListener(){
        
            @Override
            public void paintControl(PaintEvent e) {
                if(!graph.isNodesEmpty()) {
                    for (Node node : graph.GetNodes()) {
                        e.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA));
                        e.gc.fillOval(node.x, node.y, radius, radius);
                    }
                }
            }
        });

        canvas.addMouseListener(new MouseListener(){
        
            @Override
            public void mouseUp(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDown(MouseEvent e) {
                
            }
        
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                System.out.println("Double Click");
                Node node = new Node();
                node.x = e.x - radius / 2;
                node.y = e.y - radius / 2;
                graph.AddNode(node);
                redraw();
            }
        });

    }
}