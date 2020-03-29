import java.util.ArrayList;

import src.util.controller.Controller;
import src.util.model.Student;
import src.util.view.Window;

public class lab2 {
    public static void main(String []args) {
        Controller controller = new Controller(new ArrayList<Student>());
        Window window = new Window(controller);
        window.start();
    }
}