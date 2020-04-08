import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Packet packet = (Packet) ois.readObject();
            String method = packet.getMethod().split(" ")[0];
            System.out.println("Message received: " + method);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            switch(method) {
                case "addStudent":
                controller.addStudent(packet.getData());
                break;
                case "getAllStudents":
                controller.getAllStudents(oos);
                break;
                case "load":
                controller.load(packet.getFilePath());
                break;
                case "save":
                controller.save(packet.getFilePath());
                break;
                case "findByName":
                controller.findByName(oos, packet.getFindByName(), packet.getData());
                break;
                case "findByFatherName":
                controller.findByFatherName(oos, packet.getFindByName(), packet.getData());
                break;
                case "findByMotherName":
                controller.findByMotherName(oos, packet.getFindByName(), packet.getData());
                break;
                case "findByNumOfBrothers":
                controller.findByNumOfBrothers(oos, packet.getFindByNumber(), packet.getData());
                break;
                case "findByNumOfSisters":
                controller.findByNumOfSisters(oos, packet.getFindByNumber(), packet.getData());
                break;
                case "findByFatherIncomeLower":
                controller.findByFatherIncomeLower(oos, packet.getFindByNumber(), packet.getData());
                break;
                case "findByFatherIncomeHigher":
                controller.findByFatherIncomeHigher(oos, packet.getFindByNumber(), packet.getData());
                break;
                case "findByMotherIncomeLower":
                controller.findByMotherIncomeLower(oos, packet.getFindByNumber(), packet.getData());
                break;
                case "findByMotherIncomeHigher":
                controller.findByMotherIncomeHigher(oos, packet.getFindByNumber(), packet.getData());
                break;
                case "getStudentPage":
                controller.getStudentPage(oos, Integer.parseInt(packet.getMethod().split(" ")[1]), packet.getFindByNumber(), packet.getData());
                break;
                case "deleteStudents":
                controller.deleteStudents(oos, packet.getData());
                break;
            }
            ois.close();
            oos.close();
        }
        serverSocket.close();
        System.out.println("Server spyniŭsia");
    }

    public static void stopServer() {
        isWorking = false;
    }
}