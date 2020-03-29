import java.util.ArrayList;
import java.net.*;
import java.io.*;

import src.util.controller.Controller;
import src.util.model.Student;
import src.util.view.Window;

public class client {
    public static void main(String []args) throws UnknownHostException, IOException {
        Controller controller = new Controller(new ArrayList<Student>());
        Window window = new Window(controller);
        controller.connect("localhost", 8080);
        window.start();
    }
}