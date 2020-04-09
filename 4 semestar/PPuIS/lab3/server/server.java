import java.util.ArrayList;
import java.util.concurrent.Executors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;
import java.net.*;

import src.util.controller.Controller;
import src.util.model.Student;
import com.sun.net.httpserver.HttpServer;

public class server {
    static Controller controller = new Controller(new ArrayList<Student>());
    static boolean isWorking = false;
    static HttpServer server = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Display display = new Display();
        Shell shell = new Shell(display);
        GridLayout layout = new GridLayout(2, true);
        shell.setLayout(layout);
        shell.setSize(400, 200);

        Button startButton = new Button(shell, SWT.PUSH);
        startButton.setText("Zapuści server");
        startButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        startButton.addListener(SWT.MouseUp, new Listener() {

            @Override
            public void handleEvent(Event arg0) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            startServer();
                        } catch (ClassNotFoundException | IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        Button stopButton = new Button(shell, SWT.PUSH);
        stopButton.setText("Prypyni server");
        stopButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        stopButton.addListener(SWT.MouseUp, new Listener(){
        
            @Override
            public void handleEvent(Event arg0) {
                stopServer();
            }
        });

        shell.open();

        while(!shell.isDisposed()) {
            if(display.readAndDispatch()) display.sleep();
        }
    }

    public static void startServer() throws IOException, ClassNotFoundException {
        InetSocketAddress addr = new InetSocketAddress(8080);
        server = HttpServer.create(addr, 0);
        isWorking = true;
        System.out.println("Server zapuściŭsia na porcie 8080.");
        server.createContext("/", controller);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
    }

    public static void stopServer() {
        server.stop(1);
    }
}