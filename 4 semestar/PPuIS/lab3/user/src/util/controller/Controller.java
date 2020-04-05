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
        Packet packet = new Packet(method, null, filePath, null, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        students = incomePacket.getData();
    }

    public void save(String filePath) throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "save";
        Packet packet = new Packet(method, null, filePath, null, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        oos.close();
    }

    public List<Student> getAllStudents() throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "getAllStudents";
        Packet packet = new Packet(method, null, null, null, 0);
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
        Packet packet = new Packet(method, oneStudent, null, null, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        oos.close();
    }

    public void deleteStudents(List<Student> students) {
        for (Student student : students) {
            students.remove(student);
        }
    }

    public List<Student> findByName(String name) throws IOException,
            ClassNotFoundException {
        connect(url, port);
        String method = "findByName";
        Packet packet = new Packet(method, null, null, name, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByName(String name, List<Student> students) throws IOException,
            ClassNotFoundException {
        connect(url, port);
        String method = "findByName";
        Packet packet = new Packet(method, students, null, name, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByFatherName(String fatherName)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByFatherName";
        Packet packet = new Packet(method, null, null, fatherName, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByFatherName(String fatherName, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByFatherName";
        Packet packet = new Packet(method, studentsList, null, fatherName, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByMotherName(String motherName)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByMotherName";
        Packet packet = new Packet(method, null, null, motherName, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByMotherName(String motherName, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByMotherName";
        Packet packet = new Packet(method, studentsList, null, motherName, 0);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByNumOfBrothers";
        Packet packet = new Packet(method, null, null, null, numOfBrothers);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByNumOfBrothers";
        Packet packet = new Packet(method, studentsList, null, null, numOfBrothers);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByNumOfSisters(int numOfSisters)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByNumOfSisters";
        Packet packet = new Packet(method, null, null, null, numOfSisters);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByNumOfSisters(int numOfSisters, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByNumOfSisters";
        Packet packet = new Packet(method, studentsList, null, null, numOfSisters);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByFatherIncomeLower(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByFatherIncomeLower";
        Packet packet = new Packet(method, null, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByFatherIncomeLower(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByFatherIncomeLower";
        Packet packet = new Packet(method, studentsList, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByFatherIncomeHigher(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByFatherIncomeHigher";
        Packet packet = new Packet(method, null, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByFatherIncomeHigher(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByFatherIncomeHigher";
        Packet packet = new Packet(method, studentsList, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByMotherIncomeLower(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByMotherIncomeLower";
        Packet packet = new Packet(method, null, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByMotherIncomeLower(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByMotherIncomeLower";
        Packet packet = new Packet(method, studentsList, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByMotherIncomeHigher(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByMotherIncomeHigher";
        Packet packet = new Packet(method, null, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
    }

    public List<Student> findByMotherIncomeHigher(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        connect(url, port);
        String method = "findByMotherIncomeHigher";
        Packet packet = new Packet(method, studentsList, null, null, upperBound);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(packet);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Packet incomePacket = (Packet) ois.readObject();
        ois.close();
        oos.close();
        return incomePacket.getData();
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