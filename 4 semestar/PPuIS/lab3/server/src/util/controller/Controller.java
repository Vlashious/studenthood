package src.util.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.util.loader.Loader;
import src.util.model.Student;
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

    public List<Student> getAllStudents() {
        return students;
    }

    public void setAllStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(BufferedReader data) throws IOException {
        Student student = new Student();
        String name = data.readLine();
        String fatherName = data.readLine();
        String motherName = data.readLine();
        int fatherIncome = Integer.parseInt(data.readLine());
        int motherIncome = Integer.parseInt(data.readLine());
        int numOfBrothers = Integer.parseInt(data.readLine());
        int numOfSisters = Integer.parseInt(data.readLine());
        
        student.setName(name);
        student.setFatherName(fatherName);
        student.setFatherIncome(fatherIncome);
        student.setMotherName(motherName);
        student.setMotherIncome(motherIncome);
        student.setNumOfBrothers(numOfBrothers);
        student.setNumOfSisters(numOfSisters);
        students.add(student);
    }

    public void deleteStudents(List<Student> students) {
        for (Student student : students) {
            students.remove(student);
        }
    }

    public List<Student> findByName(String name) {
        List<Student> students = new ArrayList<Student>();
        for (Student student : students) {
            if(student.getName().contains(name)) {
                students.add(student);
            }
        }
        return students;
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