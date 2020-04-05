package src.util.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import src.util.model.Student;
import src.util.packet.Packet;

public class Controller {
    private List<Student> students;
    private Socket socket;
    private String url = "localhost";
    private int port = 8080;

    public Controller(List<Student> students) {
        this.students = students;
    }

    public void connect(String url, int port) throws UnknownHostException, IOException, ClassNotFoundException {
        socket = new Socket(url, port);
    }

    public void load(String filePath) throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "load";
        Packet packet = new Packet(method, null, filePath);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        students = incomePacket.getData();
    }

    public void save(String filePath) throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "save";
        Packet packet = new Packet(method, null, filePath);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        students = incomePacket.getData();
    }

    public List<Student> getAllStudents() throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "getAllStudents";
        Packet packet = new Packet(method, null, null);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        oos.close();
        ois.close();
        return incomePacket.getData();
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(String name, String fatherName, String motherName,
                           int fatherIncome, int motherIncome, int numOfBrothers, int numOfSisters) throws IOException,
            ClassNotFoundException {
        connect(url, port);
        String method = "addStudent";
        Student newStud = new Student();
        newStud.setName(name);
        newStud.setFatherName(fatherName);
        newStud.setMotherName(motherName);
        newStud.setFatherIncome(fatherIncome);
        newStud.setMotherIncome(motherIncome);
        newStud.setNumOfBrothers(numOfBrothers);
        newStud.setNumOfSisters(numOfSisters);
        List<Student> oneStudent = new ArrayList<Student>();
        oneStudent.add(newStud);
        Packet packet = new Packet(method, oneStudent, null);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        oos.close();
    }

    public void deleteStudents(List<Student> students) {
        for (Student student : students) {
            students.remove(student);
        }
    }

    public List<Student> findByName(String name, List<Student> studentsList) throws IOException,
            ClassNotFoundException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("findByName");
        out.println(name);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(studentsList);
        out.flush();
        out.close();

        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        List<Student> students = (List<Student>) inputStream.readObject();
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

    public List<Student> getStudentPage(int index, int numOfStudentsOnPage, List<Student> students) {
        List<List<Student>> pages = calculatePages(numOfStudentsOnPage, students);
        if(!pages.isEmpty()) {
            return pages.get(index);
        } else {
            List<Student> page = new ArrayList<Student>();
            return page;
        }
    }

    private List<List<Student>> calculatePages(int numOfStudentsOnPage, List<Student> students) {
        List<List<Student>> pages = new ArrayList<List<Student>>();
        int numOfPages = (int) Math.ceil((double) students.size() / numOfStudentsOnPage );

        System.out.println("Students on page: " + numOfStudentsOnPage);
        System.out.println("Number of pages: " + numOfPages);

        for(int j = 0; j < numOfPages; j++) {
            List<Student> studentPage = new ArrayList<Student>();
            for(int i = numOfStudentsOnPage * j; i < numOfStudentsOnPage * j + numOfStudentsOnPage; i++) {
                try {
                studentPage.add(students.get(i));
                }
                catch (Exception e) {
                    e.getStackTrace();
                }
            }
            pages.add(studentPage);
        }
        return pages;
    }
}