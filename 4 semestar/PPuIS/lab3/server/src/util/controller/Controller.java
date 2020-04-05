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

    public void deleteStudents(List<Student> students) {
        for (Student student : students) {
            students.remove(student);
        }
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