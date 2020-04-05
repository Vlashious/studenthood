package src.util.packet;

import java.io.Serializable;
import java.util.List;

import src.util.model.Student;

public class Packet implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String method;
    List<Student> data;

    public Packet(String method, List<Student> data) {
        this.method = method;
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public List<Student> getData() {
        return data;
    }
}