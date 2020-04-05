import java.util.ArrayList;

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
        startServer();
    }

    public static void startServer() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8080);
        isWorking = true;
        while(isWorking) {
            Socket socket = serverSocket.accept();
            System.out.println("Client is connected!");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Packet packet = (Packet) ois.readObject();
            String method = packet.getMethod();
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
            }
            ois.close();
            oos.close();
        }
        serverSocket.close();
    }

    public static void stopServer() {
        isWorking = false;
    }
}