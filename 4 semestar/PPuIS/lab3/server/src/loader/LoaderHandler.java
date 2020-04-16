package src.loader;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import src.model.Student;

public class LoaderHandler extends DefaultHandler {
    private boolean bName = false;
    private boolean bFatherName = false;
    private boolean bFatherIncome = false;
    private boolean bMotherName = false;
    private boolean bMotherIncome = false;
    private boolean bNumOfBrothers = false;
    private boolean bNumOfSisters = false;

    private List<Student> students = null;
    private Student student = null;
    private StringBuilder data;

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(qName.equalsIgnoreCase("student")) {
            student = new Student();
            if(students == null) {
                students = new ArrayList<Student>();
            }
        }
        else if (qName.equalsIgnoreCase("Name")) {
            bName = true;
        }
        else if (qName.equalsIgnoreCase("FatherName")) {
            bFatherName = true;
        }
        else if (qName.equalsIgnoreCase("FatherIncome")) {
            bFatherIncome = true;
        }
        else if (qName.equalsIgnoreCase("MotherName")) {
            bMotherName = true;
        }
        else if (qName.equalsIgnoreCase("MotherIncome")) {
            bMotherIncome = true;
        }
        else if (qName.equalsIgnoreCase("NumOfBrothers")) {
            bNumOfBrothers = true;
        }
        else if (qName.equalsIgnoreCase("NumOfSisters")) {
            bNumOfSisters = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(bName) {
            student.setName(data.toString());
            bName = false;
        }
        else if (bFatherName) {
            student.setFatherName(data.toString());
            bFatherName = false;
        }
        else if (bFatherIncome) {
            student.setFatherIncome(Integer.parseInt(data.toString()));
            bFatherIncome = false;
        }
        else if (bMotherName) {
            student.setMotherName(data.toString());
            bMotherName = false;
        }
        else if (bMotherIncome) {
            student.setMotherIncome(Integer.parseInt(data.toString()));
            bMotherIncome = false;
        }
        else if (bNumOfBrothers) {
            student.setNumOfBrothers(Integer.parseInt(data.toString()));
            bNumOfBrothers = false;
        }
        else if (bNumOfSisters) {
            student.setNumOfSisters(Integer.parseInt(data.toString()));
            bNumOfSisters = false;
        }

        if (qName.equalsIgnoreCase("Student")) {
            students.add(student);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        data.append(new String(ch, start, length));
    }
}