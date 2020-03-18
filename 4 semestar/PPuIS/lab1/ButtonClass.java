import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class ButtonClass {

    private Shell parent;
    private Listeners listeners;

    public Composite Initialize(Shell parent, Listeners listeners) {
        this.parent = parent;
        this.listeners = listeners;
        Composite buttonsLayer = new Composite(parent, SWT.BORDER);
        buttonsLayer.setLayoutData(new RowData(1280, 72));
        buttonsLayer.setLayout(new FillLayout(SWT.HORIZONTAL));

        Button newNodeButton = new Button(buttonsLayer, SWT.PUSH);
        newNodeButton.setText("New\nNode");
        newNodeButton.addListener(SWT.Selection, listeners.newNodeListener);

        Button newEdgeButton = new Button(buttonsLayer, SWT.PUSH);
        newEdgeButton.setText("New\nEdge");
        newEdgeButton.addListener(SWT.Selection, listeners.newEdgeListener);

        Button setIdButton = new Button(buttonsLayer, SWT.PUSH);
        setIdButton.setText("Set\nIdentificator");
        setIdButton.addListener(SWT.Selection, listeners.setIdListener);

        Button setEdgeWeightButton = new Button(buttonsLayer, SWT.PUSH);
        setEdgeWeightButton.setText("Set\nEdge\nWeight");
        setEdgeWeightButton.addListener(SWT.Selection, listeners.setWeightListener);

        Button deleteNodeButton = new Button(buttonsLayer, SWT.PUSH);
        deleteNodeButton.setText("Delete Node");
        deleteNodeButton.addListener(SWT.Selection, listeners.deleteNodeListener);

        Button deleteEdgeButton = new Button(buttonsLayer, SWT.PUSH);
        deleteEdgeButton.setText("Delete Edge");
        deleteEdgeButton.addListener(SWT.Selection, listeners.deleteEdgeListener);

        Button saveButton = new Button(buttonsLayer, SWT.PUSH);
        saveButton.setText("Save");
        saveButton.addListener(SWT.Selection, listeners.saveListener);

        Button loadButton = new Button(buttonsLayer, SWT.PUSH);
        loadButton.setText("Load");
        loadButton.addListener(SWT.Selection, listeners.loadListener);

        Button algorithmButton = new Button(buttonsLayer, SWT.PUSH);
        algorithmButton.setText("Show\nAlgorithm");
        algorithmButton.addListener(SWT.Selection, listeners.algorithmListener);
        return buttonsLayer;
    }

    public void MenuInitialize(Canvas canvas) {
        Menu menu = new Menu(canvas);

        MenuItem menuAddNode = new MenuItem(menu, SWT.NONE);
        menuAddNode.setText("New Node");
        menuAddNode.addListener(SWT.Selection, listeners.newNodeListener);

        MenuItem menuAddEdge = new MenuItem(menu, SWT.NONE);
        menuAddEdge.setText("New Edge");
        menuAddEdge.addListener(SWT.Selection, listeners.newEdgeListener);

        MenuItem menuSetId = new MenuItem(menu, SWT.NONE);
        menuSetId.setText("Set ID");
        menuSetId.addListener(SWT.Selection, listeners.setIdListener);

        MenuItem menuSetWeight = new MenuItem(menu, SWT.NONE);
        menuSetWeight.setText("Set Weight");
        menuSetWeight.addListener(SWT.Selection, listeners.setWeightListener);

        MenuItem menuDeleteNode = new MenuItem(menu, SWT.NONE);
        menuDeleteNode.setText("Delete Node");
        menuDeleteNode.addListener(SWT.Selection, listeners.deleteNodeListener);

        MenuItem menuDeleteEdge = new MenuItem(menu, SWT.NONE);
        menuDeleteEdge.setText("Delete Edge");
        menuDeleteEdge.addListener(SWT.Selection, listeners.deleteEdgeListener);

        MenuItem menuSave = new MenuItem(menu, SWT.NONE);
        menuSave.setText("Save...");
        menuSave.addListener(SWT.Selection, listeners.saveListener);

        MenuItem menuLoad = new MenuItem(menu, SWT.NONE);
        menuLoad.setText("Load...");
        menuLoad.addListener(SWT.Selection, listeners.loadListener);

        MenuItem menuShowAlgorithm = new MenuItem(menu, SWT.NONE);
        menuShowAlgorithm.setText("Show Algorithm");
        menuShowAlgorithm.addListener(SWT.Selection, listeners.algorithmListener);

        canvas.setMenu(menu);

    }

}