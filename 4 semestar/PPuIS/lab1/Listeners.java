import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Listeners {
    private GraphController graphController;
    private Graph graph;
    private Shell parent;
    private Algorithm algorithm;

    public Listeners(Graph graph, GraphController graphController, Shell parent, Algorithm algorithm) {
        this.graph = graph;
        this.graphController = graphController;
        this.parent = parent;
        this.algorithm = algorithm;
    }

    Listener newNodeListener = new Listener() {

        @Override
        public void handleEvent(Event event) {
            graphController.AddNode();
        }
    };

    Listener newEdgeListener = new Listener(){
    
        @Override
        public void handleEvent(Event event) {
            graphController.NewEdge();
        }
    };

    Listener setIdListener = new Listener(){
    
        @Override
        public void handleEvent(Event event) {
            Shell child = new Shell(parent);
            child.setSize(200, 100);
            child.setLayout(new RowLayout(SWT.VERTICAL));
            child.setText("Set ID");

            Label label = new Label(child, SWT.NONE);
            label.setText("New identificator:");

            Text text = new Text(child, SWT.BORDER);
            text.setLayoutData(new RowData(150, 10));
            text.addListener(SWT.KeyDown, new Listener() {

                @Override
                public void handleEvent(Event e) {
                    if (e.keyCode == 13) {
                        graphController.SetNodeID(text.getText());
                        child.dispose();
                    }
                }
            });

            if(!graphController.isSelectedNodesEmpty()) {
                child.open();
            }
        }
    };

    Listener setWeightListener = new Listener(){
    
        @Override
        public void handleEvent(Event event) {
            if(graphController.selectedNodes.size() == 2) {
                if(graphController.AreConnected()) {
                    Shell child = new Shell(parent);
                    child.setSize(200, 100);
                    child.setText("Set Weight");
                    child.setLayout(new RowLayout());

                    Label label = new Label(child, SWT.NONE);
                    label.setText("New Weight:");

                    Text text = new Text(child, SWT.BORDER);
                    text.setLayoutData(new RowData(150, 10));
                    text.addListener(SWT.KeyDown, new Listener() {

                        @Override
                        public void handleEvent(Event e) {
                            if (e.keyCode == 13) {
                                graphController.SetEdgeWeight(Integer.parseInt(text.getText()));
                                child.dispose();
                            }
                        }
                    });

                    child.open();
                }
            }
        }
    };

    Listener deleteNodeListener = new Listener(){
    
        @Override
        public void handleEvent(Event event) {
            graphController.DeleteNode();
        }
    };

    Listener deleteEdgeListener = new Listener(){
    
        @Override
        public void handleEvent(Event event) {
            graphController.DeleteEdge();
        }
    };

    Listener saveListener = new Listener() {

        @Override
        public void handleEvent(Event e) {

        FileDialog fDialog = new FileDialog(parent, SWT.SAVE);
        fDialog.setFilterNames(new String[] {"Graph files", "All Files"});
        fDialog.setFilterExtensions(new String [] {"*.gr", "*.*"});
        fDialog.setFileName("yourGraph.gr");
        String outputFile = fDialog.open();
        if(outputFile != null) {
                try {
                    FileOutputStream FOStream = new FileOutputStream(new File(outputFile));
                    ObjectOutputStream OOStream = new ObjectOutputStream(FOStream);

                    OOStream.writeObject(graph);
                    OOStream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };

    Listener loadListener = new Listener() {

        @Override
        public void handleEvent(Event e) {

            FileDialog fDialog = new FileDialog(parent, SWT.OPEN);
            fDialog.setFilterNames(new String[] {"Graph files", "All Files"});
            fDialog.setFilterExtensions(new String [] {"*.gr", "*.*"});
            String inputFile = fDialog.open();
            if(inputFile != null) {
                try {
                    FileInputStream FIStream = new FileInputStream(new File(inputFile));
                    ObjectInputStream OIStream = new ObjectInputStream(FIStream);
    
                    Graph loadedGraph = (Graph) OIStream.readObject();
                    graph.edges = loadedGraph.edges;
                    graph.nodes = loadedGraph.nodes;
                    //System.out.println(graph);
    
                    OIStream.close();
                    graphController.selectedNodes.clear();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };

    Listener algorithmListener = new Listener(){
    
        @Override
        public void handleEvent(Event event) {
            algorithm.StartAlgorithm();
        }
    };
}