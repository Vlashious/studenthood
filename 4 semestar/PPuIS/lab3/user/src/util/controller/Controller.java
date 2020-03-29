package src.util.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import src.util.loader.Loader;
import src.util.model.Student;
import src.util.saver.Saver;

public class Controller {
    private List<Student> students;
    private Loader loader;
    private Saver saver;
    private Socket socket;

    public Controller(List<Student> students) {
        this.students = students;
        loader = new Loader();
        saver = new Saver();
    }

    public void connect(String url, int port) throws UnknownHostException, IOException {
        socket = new Socket(url, port);
    }

    public void load(String filePath) {
        this.students = loader.load(filePath);
    }

    public void save(String filePath) {
        saver.save(this.students, filePath);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(String name, String fatherName, String motherName, int fatherIncome, int motherIncome, int numOfBrothers, int numOfSisters)
            throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("Add");
        out.println(name);
        out.println(fatherName);
        out.println(motherName);
        out.println(fatherIncome);
        out.println(motherIncome);
        out.println(numOfBrothers);
        out.println(numOfSisters);
        out.close();
    }

    public void deleteStudents(List<Student> students) {
        for (Student student : students) {
            students.remove(student);
        }
    }

    public List<Student> findByName(String name) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : students) {
            if(student.getName().contains(name)) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByName(String name, List<Student> studentsList) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : studentsList) {
            if(student.getName().contains(name)) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByFatherName(String fatherName) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : students) {
            if(student.getFatherName().contains(fatherName)) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByFatherName(String fatherName, List<Student> studentsList) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : studentsList) {
            if(student.getFatherName().contains(fatherName)) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByMotherName(String motherName) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : students) {
            if(student.getMotherName().contains(motherName)) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByMotherName(String motherName, List<Student> studentsList) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : studentsList) {
            if(student.getMotherName().contains(motherName)) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : students) {
            if(student.getNumOfBrothers() == numOfBrothers) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers, List<Student> studentsList) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : studentsList) {
            if(student.getNumOfBrothers() == numOfBrothers) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByNumOfSisters(int numOfSisters) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : students) {
            if(student.getNumOfSisters() == numOfSisters) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByNumOfSisters(int numOfSisters, List<Student> studentsList) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : studentsList) {
            if(student.getNumOfSisters() == numOfSisters) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> findByIncome(int upperIncome, int lowerIncome, boolean includeUpper, boolean includeLower, boolean includeFather, boolean includeMother) {
        List<Student> students = new ArrayList<Student>();
        if(includeUpper && !includeLower) {
            for (Student student : students) {
                if(includeFather) {
                    if(student.getFatherIncome() < upperIncome) {
                        students.add(student);
                    }
                }
                if(includeMother) {
                    if(student.getMotherIncome() < upperIncome && !students.contains(student)) {
                        students.add(student);
                    }
                }
            }
            return students;
        }
        if(includeLower && !includeUpper) {
            for (Student student : students) {
                if(includeFather) {
                    if(student.getFatherIncome() > lowerIncome) {
                        students.add(student);
                    }
                }
                if(includeMother) {
                    if(student.getMotherIncome() > lowerIncome && !students.contains(student)) {
                        students.add(student);
                    }
                }
            }
            return students;
        }
        if(includeUpper && includeLower) {
            for (Student student : students) {
                if(includeFather) {
                    if(student.getFatherIncome() > lowerIncome && student.getFatherIncome() < upperIncome) {
                        students.add(student);
                    }
                }
                if(includeMother) {
                    if(student.getMotherIncome() > lowerIncome && student.getMotherIncome() < upperIncome && !students.contains(student)) {
                        students.add(student);
                    }
                }
            }
            return students;
        }
        return students;
    }

    public List<Student> findByIncome(int upperIncome, int lowerIncome, boolean includeUpper, boolean includeLower, boolean includeFather, boolean includeMother, List<Student> studentsList) {
        List<Student> students = new ArrayList<Student>();
        if(includeUpper && !includeLower) {
            for (Student student : studentsList) {
                if(includeFather) {
                    if(student.getFatherIncome() < upperIncome) {
                        students.add(student);
                    }
                }
                if(includeMother) {
                    if(student.getMotherIncome() < upperIncome && !students.contains(student)) {
                        students.add(student);
                    }
                }
            }
            return students;
        }
        if(includeLower && !includeUpper) {
            for (Student student : studentsList) {
                if(includeFather) {
                    if(student.getFatherIncome() > lowerIncome) {
                        students.add(student);
                    }
                }
                if(includeMother) {
                    if(student.getMotherIncome() > lowerIncome && !students.contains(student)) {
                        students.add(student);
                    }
                }
            }
            return students;
        }
        if(includeUpper && includeLower) {
            for (Student student : studentsList) {
                if(includeFather) {
                    if(student.getFatherIncome() > lowerIncome && student.getFatherIncome() < upperIncome) {
                        students.add(student);
                    }
                }
                if(includeMother) {
                    if(student.getMotherIncome() > lowerIncome && student.getMotherIncome() < upperIncome && !students.contains(student)) {
                        students.add(student);
                    }
                }
            }
            return students;
        }
        return students;
    }
}