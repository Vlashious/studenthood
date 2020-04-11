package src.util.controller;

import java.io.ByteArrayOutputStream;
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
import java.net.http.HttpRequest.BodyPublishers;
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

    public void deleteStudents(List<Student> students)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(students);

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/deleteStudents"))
        .DELETE()
        .build();
        baos.close();
        oos.close();
        client.send(request, BodyHandlers.discarding());
    }

    public List<Student> findByName(String name) throws IOException,
            ClassNotFoundException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByName"))
        .setHeader("Name", name)
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByName(String name, List<Student> students) throws IOException,
            ClassNotFoundException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(students);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByName"))
        .setHeader("Name", name)
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByFatherName(String fatherName)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherName"))
        .setHeader("Name", fatherName)
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByFatherName(String fatherName, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherName"))
        .setHeader("Name", fatherName)
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByMotherName(String motherName)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByMotherName"))
        .setHeader("Name", motherName)
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByMotherName(String motherName, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByMotherName"))
        .setHeader("Name", motherName)
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByNumOfBrothers"))
        .setHeader("NumOfBrothers", Integer.toString(numOfBrothers))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByNumOfBrothers"))
        .setHeader("Num", Integer.toString(numOfBrothers))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByNumOfSisters(int numOfSisters)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByNumOfSisters"))
        .setHeader("Num", Integer.toString(numOfSisters))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByNumOfSisters(int numOfSisters, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByNumOfSisters"))
        .setHeader("Num", Integer.toString(numOfSisters))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByFatherIncomeLower(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherIncomeLower"))
        .setHeader("Num", Integer.toString(upperBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByFatherIncomeLower(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherIncomeLower"))
        .setHeader("Num", Integer.toString(upperBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByFatherIncomeHigher(int lowerBound)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherIncomeHigher"))
        .setHeader("Num", Integer.toString(lowerBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByFatherIncomeHigher(int lowerBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherIncomeHigher"))
        .setHeader("Num", Integer.toString(lowerBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByMotherIncomeLower(int upperBound)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByMotherIncomeLower"))
        .setHeader("Num", Integer.toString(upperBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByMotherIncomeLower(int upperBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByMotherIncomeLower"))
        .setHeader("Num", Integer.toString(upperBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByMotherIncomeHigher(int lowerBound)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ArrayList<Student>());
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherIncomeHigher"))
        .setHeader("Num", Integer.toString(lowerBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> findByMotherIncomeHigher(int lowerBound, List<Student> studentsList)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(studentsList);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/findByFatherIncomeHigher"))
        .setHeader("Num", Integer.toString(lowerBound))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }

    public List<Student> getStudentPage(int index, int numOfStudentsOnPage, List<Student> students)
            throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(students);
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://" + url + ":" + port + "/getStudentPage"))
        .setHeader("NumOnPage", Integer.toString(numOfStudentsOnPage))
        .setHeader("NumOfPage", Integer.toString(index))
        .POST(BodyPublishers.ofByteArray(baos.toByteArray()))
        .build();
        baos.close();
        oos.close();
        HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
        ObjectInputStream ois = new ObjectInputStream(response.body());
        List<Student> studentsFound = (List<Student>) ois.readObject();
        ois.close();
        return studentsFound;
    }
}