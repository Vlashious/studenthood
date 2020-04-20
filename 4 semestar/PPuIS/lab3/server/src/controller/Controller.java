package src.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import src.Filter;
import src.loader.Loader;
import src.model.Student;
import src.saver.Saver;

public class Controller implements HttpHandler {
    private List<Student> students;
    private List<Student> foundStudents;
    private Loader loader;
    private Saver saver;

    public Controller(List<Student> students) {
        this.students = students;
        foundStudents = new ArrayList<Student>();
        loader = new Loader();
        saver = new Saver();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String httpMethod = exchange.getRequestMethod();
        String requestMethod = exchange.getRequestURI().toString();
        if (httpMethod.equalsIgnoreCase("GET")) {
            switch (requestMethod) {
                case "/getAllStudentsSize":
                    getAllStudentsSize(exchange);
                    break;
                default:
                    exchange.sendResponseHeaders(404, 0);
                    exchange.getResponseBody().write("<h1>404 Not Found<h1>".getBytes());
                    break;
            }
        }
        if (httpMethod.equalsIgnoreCase("POST")) {
            switch (requestMethod) {
                case "/load":
                    load(exchange);
                    break;
                case "/save":
                    save(exchange);
                    break;
                case "/addStudent":
                    addStudent(exchange);
                    break;
                case "/find":
                    try {
                        find(exchange);
                    } catch (ClassNotFoundException e) {
                        exchange.sendResponseHeaders(400, 0);
                        e.printStackTrace();
                    }
                    break;
                case "/deleteStudents":
                    try {
                        deleteStudents(exchange);
                    } catch (ClassNotFoundException e) {
                        exchange.sendResponseHeaders(400, 0);
                        e.printStackTrace();
                    }
                    break;
                case "/getStudentPage":
                    getStudentPage(exchange);
                    break;
                default:
                    exchange.sendResponseHeaders(404, 0);
                    exchange.getResponseBody().write("<h1>404 Not Found :(<h1>".getBytes());
                    break;
            }
        }
        exchange.getResponseBody().close();
    }

    private void load(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String filePath = reader.readLine();
        this.students = loader.load(filePath);
        exchange.sendResponseHeaders(200, 0);
        exchange.getResponseBody().write("File loaded.".getBytes());
    }

    private void save(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        OutputStream os = exchange.getResponseBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String filePath = reader.readLine();
        saver.save(this.students, filePath);
        exchange.sendResponseHeaders(200, 0);
        os.write("OK".getBytes());
    }

    private void addStudent(HttpExchange exchange) throws IOException {
        try {
            InputStream is = exchange.getRequestBody();
            ObjectInputStream ois = new ObjectInputStream(is);
            Student newStudent = (Student) ois.readObject();
            students.add(newStudent);
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().write("OK".getBytes());
        } catch (ClassNotFoundException e) {
            exchange.sendResponseHeaders(400, 0);
            e.printStackTrace();
        }
    }

    private void getAllStudentsSize(HttpExchange exchange) throws IOException {
        OutputStream os = exchange.getResponseBody();
        os.write(this.students.size());
        exchange.sendResponseHeaders(200, 0);
    }

    private void deleteStudents(HttpExchange exchange) throws ClassNotFoundException, IOException {
        OutputStream os = exchange.getResponseBody();
        
        List<Student> studentsToDelete = find(exchange);
        for(int i = 0; i < studentsToDelete.size(); i++) {
            students.remove(studentsToDelete.get(i));
        }

        exchange.sendResponseHeaders(200, 0);
        os.write(studentsToDelete.size());
    }

    private List<Student> find(HttpExchange exchange) throws IOException, ClassNotFoundException {
        Filter filter = null;
        InputStream is = exchange.getRequestBody();
        OutputStream os = exchange.getResponseBody();
        ObjectInputStream ois = new ObjectInputStream(is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        filter = (Filter) ois.readObject();
        exchange.sendResponseHeaders(200, 0);
        os.write(bos.toByteArray());

        List<Student> studentsFound = students;

        String name = filter.getName();
        String fatherName = filter.getFatherName();
        String motherName = filter.getMotherName();
        int numOfBrothers = -1;
        try {
            numOfBrothers = Integer.parseInt(filter.getBrothers());
        } catch (Exception e) {

        }
        int numOfSisters = -1;
        try {
            numOfSisters = Integer.parseInt(filter.getSisters());
        } catch (Exception e) {

        }

        Boolean findHigher = filter.checkHigherIncome();
        Boolean findLower = filter.checkLowerIncome();
        Boolean findByFatherIncome = filter.checkFather();
        Boolean findByMotherIncome = filter.checkMother();
        int upperBound = -1;
        try {
            upperBound = Integer.parseInt(filter.getUpperBound());
        } catch (Exception e) {

        }
        int lowerBound = -1;
        try {
            lowerBound = Integer.parseInt(filter.getLowerBound());
        } catch (Exception e) {

        }

        if(!name.isEmpty()) studentsFound = findByName(name, studentsFound);
        if(!fatherName.isEmpty()) studentsFound = findByFatherName(fatherName, studentsFound);
        if(!motherName.isEmpty()) studentsFound = findByMotherName(motherName, studentsFound);

        if(numOfBrothers != -1) studentsFound = findByNumOfBrothers(numOfBrothers, studentsFound);
        if(numOfSisters != -1) studentsFound = findByNumOfSisters(numOfSisters, studentsFound);

        if(findHigher) {
            if(findByFatherIncome && lowerBound != -1) studentsFound = findByFatherIncomeHigher(lowerBound, studentsFound);
            if(findByMotherIncome && lowerBound != -1) studentsFound = findByMotherIncomeHigher(lowerBound, studentsFound);
        }
        if(findLower) {
            if(findByFatherIncome && upperBound != -1) studentsFound = findByFatherIncomeLower(upperBound, studentsFound);
            if(findByMotherIncome && upperBound != -1) studentsFound = findByMotherIncomeLower(upperBound, studentsFound);
        }
        foundStudents = studentsFound;
        oos.writeObject(foundStudents);
        exchange.sendResponseHeaders(200, 0);
        os.write(bos.toByteArray());
        return foundStudents;
    }

    private List<Student> findByName(String name, List<Student> students) {
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

    private List<Student> findByFatherName(String name, List<Student> students) {
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

    private List<Student> findByMotherName(String name, List<Student> students) {
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

    private List<Student> findByNumOfBrothers(int numOfBrothers, List<Student> students)
            {
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

    private List<Student> findByNumOfSisters(int numOfSisters, List<Student> students)
            {
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

    private List<Student> findByFatherIncomeLower(int upperBound, List<Student> students)
            {
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

    private List<Student> findByFatherIncomeHigher(int lowerBound, List<Student> students)
            {
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

    private List<Student> findByMotherIncomeLower(int upperBound, List<Student> students)
            {
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

    private List<Student> findByMotherIncomeHigher(int lowerBound, List<Student> students)
            {
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

    private void getStudentPage(HttpExchange e) throws IOException {
        InputStream is = e.getRequestBody();
        OutputStream os = e.getResponseBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String requestString = reader.readLine();
        reader.close();
        String[] params = requestString.split("&");
        int index = Integer.parseInt(params[0].split("=")[1]);
        int numOfStudentsOnPage = Integer.parseInt(params[1].split("=")[1]);
        boolean searchByFound = Boolean.parseBoolean(params[2].split("=")[1]);
        List<List<Student>> pages = null;
        if(!searchByFound) {
            pages = calculatePages(numOfStudentsOnPage, this.students);
        } else {
            pages = calculatePages(numOfStudentsOnPage, foundStudents);
        }
        if (!pages.isEmpty()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(pages.get(index));
            os.write(baos.toByteArray());
            e.sendResponseHeaders(200, 0);
        } else {
            List<Student> page = new ArrayList<Student>();
            e.sendResponseHeaders(400, 0);
        }
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