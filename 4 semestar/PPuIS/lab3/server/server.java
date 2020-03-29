import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import src.util.controller.Controller;
import src.util.model.Student;

public class server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Controller controller = new Controller(new ArrayList<Student>());
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Waiting for the client...");
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client is connected!");
            BufferedReader data = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String command = data.readLine();
            switch(command) {
                case "Add":
                    System.out.println("Add a student");
                    controller.addStudent(data);
                    data.close();
                    break;
                case "findByName":
                    System.out.println("Find by name");
                    controller.findByName(data, socket);
                    data.close();
                default:
                    System.out.println("No command.");
                    break;
            }
        }
    }
}