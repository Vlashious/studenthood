package src.saver;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import src.model.Student;

public class Saver {
    public void save(List<Student> students, String filePath) {
        try {
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Students");
            doc.appendChild(rootElement);

            for (Student student : students) {
                Element studentElement = doc.createElement("Student");
                rootElement.appendChild(studentElement);

                Element studName = doc.createElement("Name");
                studName.appendChild(doc.createTextNode(student.getName()));
                studentElement.appendChild(studName);

                Element fatherName = doc.createElement("FatherName");
                fatherName.appendChild(doc.createTextNode(student.getFatherName()));
                studentElement.appendChild(fatherName);

                Element fatherIncome = doc.createElement("FatherIncome");
                fatherIncome.appendChild(doc.createTextNode(String.valueOf(student.getFatherIncome())));
                studentElement.appendChild(fatherIncome);

                Element motherName = doc.createElement("MotherName");
                motherName.appendChild(doc.createTextNode(student.getMotherName()));
                studentElement.appendChild(motherName);

                Element motherIncome = doc.createElement("MotherIncome");
                motherIncome.appendChild(doc.createTextNode(String.valueOf(student.getMotherIncome())));
                studentElement.appendChild(motherIncome);

                Element numOfBrothers = doc.createElement("NumOfBrothers");
                numOfBrothers.appendChild(doc.createTextNode(String.valueOf(student.getNumOfBrothers())));
                studentElement.appendChild(numOfBrothers);

                Element numOfSisters = doc.createElement("numOfSisters");
                numOfSisters.appendChild(doc.createTextNode(String.valueOf(student.getNumOfSisters())));
                studentElement.appendChild(numOfSisters);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}