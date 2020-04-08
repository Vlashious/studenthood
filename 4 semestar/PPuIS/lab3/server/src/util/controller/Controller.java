package src.util.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
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

    public void getAllStudents(ObjectOutputStream oos) throws IOException {
        Packet packet = new Packet("", students, null, null, 0);
        oos.writeObject(packet);
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(List<Student> data) throws IOException {
        students.add(data.get(0));
    }

    public void deleteStudents(ObjectOutputStream oos, List<Student> studentsList) throws IOException {
        for(int i = 0; i < studentsList.size(); i++) {
            for(int j = 0; j < this.students.size(); j++) {
                if(compareStudents(studentsList.get(i), this.students.get(j))) {
                    this.students.remove(j);
                    j--;
                }
            }
        }
        Packet packet = new Packet(null, this.students, null, null, 0);
        oos.writeObject(packet);
        oos.close();
    }

    private boolean compareStudents(Student left, Student right) {
        if(left.getName().equals(right.getName()) && left.getFatherName().equals(right.getFatherName()) && left.getMotherName().equals(right.getMotherName())) {
            return true;
        }
        return false;
    }

    public void findByName(ObjectOutputStream oos, String name, List<Student> students) throws IOException, ClassNotFoundException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByFatherName(ObjectOutputStream oos, String name, List<Student> students) throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getFatherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getFatherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByMotherName(ObjectOutputStream oos, String name, List<Student> students) throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getMotherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getMotherName().contains(name)) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByNumOfBrothers(ObjectOutputStream oos, int numOfBrothers, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getNumOfBrothers() == numOfBrothers) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getNumOfBrothers() == numOfBrothers) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByNumOfSisters(ObjectOutputStream oos, int numOfSisters, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getNumOfSisters() == numOfSisters) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getNumOfSisters() == numOfSisters) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByFatherIncomeLower(ObjectOutputStream oos, int upperBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getFatherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getFatherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByFatherIncomeHigher(ObjectOutputStream oos, int lowerBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getFatherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getFatherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByMotherIncomeLower(ObjectOutputStream oos, int upperBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getMotherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getMotherIncome() < upperBound) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void findByMotherIncomeHigher(ObjectOutputStream oos, int lowerBound, List<Student> students)
            throws IOException {
        List<Student> outputStudents = new ArrayList<Student>();
        if(students == null) {
            for (Student student : this.students) {
                if(student.getMotherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        } else {
            for (Student student : students) {
                if(student.getMotherIncome() > lowerBound) {
                    outputStudents.add(student);
                }
            }
        }
        Packet outcomePacket = new Packet(null, outputStudents, null, null, 0);
        oos.writeObject(outcomePacket);
        oos.close();
    }

    public void getStudentPage(ObjectOutputStream oos, int index, int numOfStudentsOnPage, List<Student> students)
            throws IOException {
        List<List<Student>> pages = calculatePages(numOfStudentsOnPage, students);
        if(!pages.isEmpty()) {
            Packet packet = new Packet(null, pages.get(index), null, null, 0);
            oos.writeObject(packet);
            oos.close();
        } else {
            List<Student> page = new ArrayList<Student>();
            Packet packet = new Packet(null, page, null, null, 0);
            oos.writeObject(packet);
            oos.close();
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