package src;

import java.io.Serializable;

public class Filter implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String fatherName;
    private String motherName;
    private String upperBound;
    private String lowerBound;
    private String numOfSisters;
    private String numOfBrothers;
    private boolean checkFatherIncome;
    private boolean checkMotherIncome;
    private boolean findLower;
    private boolean findHigher;

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getBrothers() {
        return numOfBrothers;
    }

    public String getSisters() {
        return numOfSisters;
    }

    public String getLowerBound() {
        return lowerBound;
    }

    public String getUpperBound() {
        return upperBound;
    }

    public boolean checkFather() {
        return checkFatherIncome;
    }

    public boolean checkMother() {
        return checkMotherIncome;
    }

    public boolean checkHigherIncome() {
        return findHigher;
    }

    public boolean checkLowerIncome() {
        return findLower;
    }
}