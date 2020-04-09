package src.util.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.util.model.Student;

public class Controller {
    private List<Student> students;
    private Socket socket;
    private String url;
    private int port;
    private HttpClient client = HttpClient.newHttpClient();

    public Controller() throws UnknownHostException, IOException, ClassNotFoundException {
        File file = new File("*.properties");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] data = input.split(":");
            url = data[0];
            port = Integer.parseInt(data[1]);
            System.out.println(url + port);
        }
        scanner.close();
    }

    public void load(String filePath) throws UnknownHostException, ClassNotFoundException, IOException,
            InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/load"))
        .setHeader("FileName", filePath)
        .build();

        client.send(request, BodyHandlers.ofInputStream());
    }

    public void save(String filePath) throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "save";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.close();
    }

    public List<Student> getAllStudents() throws UnknownHostException, ClassNotFoundException, IOException,
            InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/getAllStudents"))
        .build();

        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        this.students = (List<Student>) ois.readObject();
        ois.close();
        return students;
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(String name, String fatherName, String motherName,
                           int fatherIncome, int motherIncome, int numOfBrothers, int numOfSisters) throws IOException,
            ClassNotFoundException {
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
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.close();
    }

    public void deleteStudents(List<Student> students)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "deleteStudents";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
    }

    public List<Student> findByName(String name) throws IOException,
            ClassNotFoundException {
        String method = "findByName";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByName(String name, List<Student> students) throws IOException,
            ClassNotFoundException {
        String method = "findByName";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByFatherName(String fatherName)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByFatherName";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByFatherName(String fatherName, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByFatherName";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByMotherName(String motherName)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByMotherName";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByMotherName(String motherName, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByMotherName";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByNumOfBrothers";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByNumOfBrothers";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByNumOfSisters(int numOfSisters)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByNumOfSisters";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByNumOfSisters(int numOfSisters, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByNumOfSisters";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByFatherIncomeLower(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByFatherIncomeLower";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByFatherIncomeLower(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByFatherIncomeLower";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByFatherIncomeHigher(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByFatherIncomeHigher";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByFatherIncomeHigher(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByFatherIncomeHigher";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByMotherIncomeLower(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByMotherIncomeLower";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByMotherIncomeLower(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByMotherIncomeLower";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByMotherIncomeHigher(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByMotherIncomeHigher";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> findByMotherIncomeHigher(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "findByMotherIncomeHigher";
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }

    public List<Student> getStudentPage(int index, int numOfStudentsOnPage, List<Student> students)
            throws UnknownHostException, ClassNotFoundException, IOException {
        String method = "getStudentPage " + index;
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.close();
        oos.close();
        return null;
    }
}