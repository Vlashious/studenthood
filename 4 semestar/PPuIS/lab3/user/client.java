import java.net.*;

import src.controller.Controller;
import src.view.Window;

import java.io.*;

public class client {
    public static void main(String []args) throws UnknownHostException, IOException, URISyntaxException,
            InterruptedException, ClassNotFoundException {
        Controller controller = new Controller();
        Window window = new Window(controller);
        window.start();
    }
}