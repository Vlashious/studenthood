package src.util.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import src.util.loader.Loader;
import src.util.model.Student;
import src.util.saver.Saver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Controller implements HttpHandler {
    private List<Student> students;
    private Loader loader;
    private Saver saver;

    public Controller(List<Student> students) {
        this.students = students;
        loader = new Loader();
        saver = new Saver();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String httpMethod = exchange.getRequestMethod();
        String requestMethod = exchange.getRequestURI().toString();

        OutputStream os = exchange.getResponseBody();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        if (httpMethod.equalsIgnoreCase("GET")) {
            switch (requestMethod) {
                case "/load":
                    String filePath = exchange.getRequestHeaders().get("FileName").get(0);
                    load(filePath);
                    exchange.sendResponseHeaders(200, 0);
                    exchange.getResponseBody().write("File loaded.".getBytes());
                    oos.writeObject(students);
                    os.write(bos.toByteArray());
                    break;
                case "/getAllStudents":
                    oos.writeObject(students);
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                default:
                    exchange.sendResponseHeaders(404, 0);
                    exchange.getResponseBody().write("<h1>404 Not Found<h1>".getBytes());
                    break;
            }
        }
        if (httpMethod.equalsIgnoreCase("POST")) {
            InputStream is = exchange.getRequestBody();
            ObjectInputStream ois = new ObjectInputStream(is);
            switch (requestMethod) {
                case "/addStudent":
                    try {
                        Student newStudent = (Student) ois.readObject();
                        addStudent(newStudent);
                        exchange.sendResponseHeaders(200, 0);
                        exchange.getResponseBody().write("OK".getBytes());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/findByName":
                    String findName = exchange.getRequestHeaders().get("Name").get(0);
                    try {
                        oos.writeObject(findByName(findName, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByFatherName":
                    findName = exchange.getRequestHeaders().get("Name").get(0);
                    try {
                        oos.writeObject(findByFatherName(findName, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByMotherName":
                    findName = exchange.getRequestHeaders().get("Name").get(0);
                    try {
                        oos.writeObject(findByMotherName(findName, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByNumOfBrothers":
                    int findNum = Integer.parseInt(exchange.getRequestHeaders().get("Num").get(0));
                    try {
                        oos.writeObject(findByNumOfBrothers(findNum, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByNumOfSisters":
                    findNum = Integer.parseInt(exchange.getRequestHeaders().get("Num").get(0));
                    try {
                        oos.writeObject(findByNumOfSisters(findNum, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByFatherIncomeLower":
                    findNum = Integer.parseInt(exchange.getRequestHeaders().get("Num").get(0));
                    try {
                        oos.writeObject(findByFatherIncomeLower(findNum, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByFatherIncomeHigher":
                    findNum = Integer.parseInt(exchange.getRequestHeaders().get("Num").get(0));
                    try {
                        oos.writeObject(findByFatherIncomeHigher(findNum, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByMotherIncomeLower":
                    findNum = Integer.parseInt(exchange.getRequestHeaders().get("Num").get(0));
                    try {
                        oos.writeObject(findByMotherIncomeLower(findNum, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/findByMotherIncomeHigher":
                    findNum = Integer.parseInt(exchange.getRequestHeaders().get("Num").get(0));
                    try {
                        oos.writeObject(findByMotherIncomeHigher(findNum, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                case "/getStudentPage":
                    findNum = Integer.parseInt(exchange.getRequestHeaders().get("NumOfPage").get(0));
                    int studentNum = Integer.parseInt(exchange.getRequestHeaders().get("NumOnPage").get(0));
                    try {
                        oos.writeObject(getStudentPage(findNum, studentNum, (List<Student>) ois.readObject()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    exchange.sendResponseHeaders(200, 0);
                    os.write(bos.toByteArray());
                    break;
                default:
                    exchange.sendResponseHeaders(404, 0);
                    exchange.getResponseBody().write("<h1>404 Not Found :(<h1>".getBytes());
                    break;
            }
            is.close();
            ois.close();
        }
        if (httpMethod.equalsIgnoreCase("DELETE")) {
            InputStream is = exchange.getRequestBody();
            ObjectInputStream ois = new ObjectInputStream(is);
            switch (requestMethod) {
                case "/deleteStudents":
                    try {
                        List<Student> studentsToDelete = (List<Student>) ois.readObject();
                        deleteStudents(studentsToDelete);
                        exchange.sendResponseHeaders(200, 0);
                        exchange.getResponseBody().write("OK".getBytes());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                break;
                default:
                    exchange.sendResponseHeaders(404, 0);
                    exchange.getResponseBody().write("<h1>404 Not Found :(<h1>".getBytes());
                break;
            }
            is.close();
            ois.close();
        }
        os.close();
        bos.close();
        oos.close();
        exchange.getResponseBody().close();
    }

    public void load(String filePath) {
        this.students = loader.load(filePath);
    }

    public void save(String filePath) {
        saver.save(this.students, filePath);
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) throws IOException {
        students.add(student);
    }

    public void deleteStudents(List<Student> studentsList) throws IOException {
        for (int i = 0; i < studentsList.size(); i++) {
            for (int j = 0; j < this.students.size(); j++) {
                if (compareStudents(studentsList.get(i), this.students.get(j))) {
                    this.students.remove(j);
                    j--;
                }
            }
        }
    }

    private boolean compareStudents(Student left, Student right) {
        if (left.getName().equals(right.getName()) && left.getFatherName().equals(right.getFatherName())
                && left.getMotherName().equals(right.getMotherName())) {
            return true;
        }
        return false;
    }

    public List<Student> findByName(String name, List<Student> students) {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByFatherName(String name, List<Student> students) throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getFatherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getFatherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByMotherName(String name, List<Student> students) throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getMotherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getMotherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByNumOfBrothers(int numOfBrothers, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getNumOfBrothers() == numOfBrothers) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getNumOfBrothers() == numOfBrothers) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByNumOfSisters(int numOfSisters, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getNumOfSisters() == numOfSisters) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getNumOfSisters() == numOfSisters) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByFatherIncomeLower(int upperBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getFatherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getFatherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByFatherIncomeHigher(int lowerBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getFatherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getFatherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByMotherIncomeLower(int upperBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getMotherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getMotherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> findByMotherIncomeHigher(int lowerBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if (students == null) {
            for (Student student : this.students) {
                if (student.getMotherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if (student.getMotherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        }
        return outputStudents;
    }

    public List<Student> getStudentPage(int index, int numOfStudentsOnPage, List<Student> students)
            throws IOException {
        List<List<Student>> pages = calculatePages(numOfStudentsOnPage, students);
        if (!pages.isEmpty()) {
            return pages.get(index);
        } else {
            List<Student> page = new ArrayList<Student>();
        }
        return null;
    }

    private List<List<Student>> calculatePages(int numOfStudentsOnPage, List<Student> students) {
        List<List<Student>> pages = new ArrayList<List<Student>>();
        int numOfPages = (int) Math.ceil((double) students.size() / numOfStudentsOnPage);

        System.out.println("Students on page: " + numOfStudentsOnPage);
        System.out.println("Number of pages: " + numOfPages);

        for (int j = 0; j < numOfPages; j++) {
            List<Student> studentPage = new ArrayList<Student>();
            for (int i = numOfStudentsOnPage * j; i < numOfStudentsOnPage * j + numOfStudentsOnPage; i++) {
                try {
                    studentPage.add(students.get(i));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            pages.add(studentPage);
        }
        return pages;
    }
}