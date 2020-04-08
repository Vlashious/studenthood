import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

import src.util.controller.Controller;
import src.util.model.Student;
import src.util.packet.Packet;

public class server {
    static Controller controller = new Controller(new ArrayList<Student>());
    static boolean isWorking = false;

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
        ServerSocket serverSocket = new ServerSocket(8080);
        isWorking = true;
        System.out.println("Server zapuściŭsia");
        while(isWorking) {
            Socket socket = serverSocket.accept();
            System.out.println("Client is connected!");
            InputStream ois = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ois));
            String packet = (String) br.readLine();
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.write("GET / HTTP/1.1");
            br.close();
            pw.close();
            ois.close();
        }
        serverSocket.close();
        System.out.println("Server spyniŭsia");
    }

    public static void stopServer() {
        isWorking = false;
    }
}