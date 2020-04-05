package src.util.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import src.util.loader.Loader;
import src.util.model.Student;
import src.util.packet.Packet;
import src.util.saver.Saver;

public class Controller {
    private List<Student> students;
    private Loader loader;
    private Saver saver;

    public Controller(List<Student> students) {
        this.students = students;
        loader = new Loader();
        saver = new Saver();
    }

    public void load(String filePath) {
        this.students = loader.load(filePath);
    }

    public void save(String filePath) {
        saver.save(this.students, filePath);
    }

    public void getAllStudents(Socket socket, ObjectOutputStream oos) throws IOException {
        Packet packet = new Packet("", students);
        oos.writeObject(packet);
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(List<Student> data) throws IOException {
        students.add(data.get(0));
    }

    public void deleteStudents(List<Student> students) {
        for (Student student : students) {
            students.remove(student);
        }
    }

    public void findByName(BufferedReader data, Socket socket) throws IOException, ClassNotFoundException {
        String name = data.readLine();

        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        List<Student> students = (List<Student>) inputStream.readObject();
        for (Student student : students) {
            if(student.getName().contains(name)) {
                students.add(student);
            }
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(students);
        outputStream.close();
        data.close();
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