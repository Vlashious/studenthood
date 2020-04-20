package src.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Scanner;

import src.Filter;
import src.model.Student;

public class Controller {
    private List<Student> students;
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
        .POST(BodyPublishers.ofString(filePath))
        .build();

        client.send(request, BodyHandlers.discarding());
    }

    public void save(String filePath) throws UnknownHostException, ClassNotFoundException, IOException,
            InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/save"))
        .POST(BodyPublishers.ofString(filePath))
        .build();

        client.send(request, BodyHandlers.discarding());
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public int getAllStudentsSize() throws IOException, InterruptedException, ClassNotFoundException {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/getAllStudentsSize"))
        .build();

        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        int size = (Integer) ois.readObject();
        return size;
    }

    public void addStudent(String name, String fatherName, String motherName,
                           int fatherIncome, int motherIncome, int numOfBrothers, int numOfSisters) throws IOException,
            ClassNotFoundException, InterruptedException {
        Student newStudent = new Student(name, fatherName, motherName, fatherIncome, motherIncome, numOfBrothers, numOfSisters);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(newStudent);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/addStudent"))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        client.send(request, BodyHandlers.discarding());
    }

    public int deleteStudents(String name, String fatherName, String motherName, String numOfBrothers, String numOfSisters, String upperBound, String lowerBound,
    boolean checkFatherIncome, boolean checkMotherIncome, boolean findLowerIncome, boolean findHigherIncome)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Filter filter = new Filter(name, fatherName, motherName, numOfBrothers, numOfSisters, upperBound, lowerBound,
        checkFatherIncome, checkMotherIncome, findLowerIncome, findHigherIncome);
        oos.writeObject(filter);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/deleteStudents"))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        int deletedStudentsNum = (Integer) ois.readObject();
        ois.close();

        return deletedStudentsNum;
    }

    public List<Student> find(String name, String fatherName, String motherName, String numOfBrothers, String numOfSisters, String upperBound, String lowerBound,
    boolean checkFatherIncome, boolean checkMotherIncome, boolean findLowerIncome, boolean findHigherIncome)
            throws IOException, InterruptedException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Filter filter = new Filter(name, fatherName, motherName, numOfBrothers, numOfSisters, upperBound, lowerBound,
        checkFatherIncome, checkMotherIncome, findLowerIncome, findHigherIncome);
        oos.writeObject(filter);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/find"))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();

        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> foundStudents = (List<Student>) ois.readObject();
        return foundStudents;
    }

    public List<Student> getStudentPage(int index, int numOfStudentsOnPage, String searchOnFound)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/getStudentPage"))
        .POST(BodyPublishers.ofString("index=" + index + "&numOfStudentsOnPage=" + numOfStudentsOnPage + "&searchOnFound=" + searchOnFound))
        .build();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }
}