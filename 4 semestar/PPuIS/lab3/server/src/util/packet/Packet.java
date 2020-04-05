package src.util.packet;

import java.io.Serializable;
import java.util.List;

import src.util.model.Student;

public class Packet implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String method;
    private String filePath;
    private List<Student> data;
    private String findByName;

    public Packet(String method, List<Student> data, String path, String findByName) {
        this.method = method;
        this.data = data;
        this.filePath = path;
        this.findByName = findByName;
    }

    public String getMethod() {
        return method;
    }

    public List<Student> getData() {
        return data;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFindByName() {
        return findByName;
    }
}