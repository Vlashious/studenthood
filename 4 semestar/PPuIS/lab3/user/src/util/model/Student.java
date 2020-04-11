package src.util.model;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
    private String fatherName;
    private String motherName;
    private int fatherIncome;
    private int motherIncome;
    private int numOfBrothers;
    private int numOfSisters;

    public Student() {

    }

    public Student(String name, String fatherName, String motherName, int fatherIncome, int motherIncome, int numOfBrothers, int numOfSisters) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.fatherIncome = fatherIncome;
        this.motherIncome = motherIncome;
        this.numOfBrothers = numOfBrothers;
        this.numOfSisters = numOfSisters;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public int getFatherIncome() {
        return fatherIncome;
    }
    public void setFatherIncome(int fatherIncome) {
        this.fatherIncome = fatherIncome;
    }
    public String getMotherName() {
        return motherName;
    }
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    public int getMotherIncome() {
        return motherIncome;
    }
    public void setMotherIncome(int motherIncome) {
        this.motherIncome = motherIncome;
    }
    public int getNumOfBrothers() {
        return numOfBrothers;
    }
    public void setNumOfBrothers(int numOfBrothers) {
        this.numOfBrothers = numOfBrothers;
    }
    public int getNumOfSisters() {
        return numOfSisters;
    }
    public void setNumOfSisters(int numOfSisters) {
        this.numOfSisters = numOfSisters;
    }
}