package src.util.view;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import src.util.controller.Controller;
import src.util.model.Student;

public class StudentTable extends Composite {
    private Table table;
    private Controller controller;
    private Button firstPageButton, lastPageButton;
    private Button nextPageButton, previousPageButton;
    private int numOfStudentsOnPage = 10;
    private int pageNum = 0;
    private Text numOfStudentsText;
    private Label pagesIndicatorLabel;

    private List<Student> searchStudentList;

    public StudentTable(Composite parent, int style, Controller controller) {
        super(parent, style);

        RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        this.setLayout(rowLayout);

        table = new Table(this, style);
        table.setHeaderVisible(true);
        String[] titles = { "Imia Studenta", "Imia Ajca", "Zarobak Ajca", "Imia Matki", "Zarobak Matki",
                "Kolkaść bratoŭ", "Kolkaść siostraŭ" };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }
        this.controller = controller;

        firstPageButton = new Button(this, SWT.PUSH);
        firstPageButton.setText("<<");
        firstPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                pageNum = 0;
                if (searchStudentList.isEmpty()) {
                    try {
                        updateTable();
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else
                    updateTable(searchStudentList);
            }
        });

        previousPageButton = new Button(this, SWT.PUSH);
        previousPageButton.setText("<");
        previousPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (pageNum - 1 >= 0) {
                    pageNum--;
                    if (searchStudentList.isEmpty()) {
                        try {
                            updateTable();
                        } catch (ClassNotFoundException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else
                        updateTable(searchStudentList);
                }
            }
        });

        nextPageButton = new Button(this, SWT.PUSH);
        nextPageButton.setText(">");
        nextPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (searchStudentList.isEmpty()) {
                    try {
                        if (pageNum + 1 < Math
                                .ceil((double) controller.getAllStudents().size() / numOfStudentsOnPage)) {
                            pageNum++;
                            try {
                                updateTable();
                            } catch (ClassNotFoundException | IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    if (pageNum + 1 < Math.ceil((double) searchStudentList.size() / numOfStudentsOnPage)) {
                        pageNum++;
                        updateTable(searchStudentList);
                    }
                }
            }
        });

        lastPageButton = new Button(this, SWT.PUSH);
        lastPageButton.setText(">>");
        lastPageButton.addListener(SWT.MouseDown, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (searchStudentList.isEmpty()) {
                    try {
                        pageNum = (int) Math.ceil((double) controller.getAllStudents().size() / numOfStudentsOnPage)
                                - 1;
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        updateTable();
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    pageNum = (int) Math.ceil((double) searchStudentList.size() / numOfStudentsOnPage - 1);
                    updateTable(searchStudentList);
                }
            }
        });

        numOfStudentsText = new Text(this, SWT.NONE);
        numOfStudentsText.setText("1");

        searchStudentList = new ArrayList<Student>();

        ModifyListener modifyListener = new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                try {
                    numOfStudentsOnPage = Integer.parseInt(numOfStudentsText.getText());
                    pageNum = 0;
                    if (searchStudentList.isEmpty()) {
                        updateTable();
                    } else
                        updateTable(searchStudentList);
                } catch (Exception exception) {
                    exception.getStackTrace();
                }
            }
        };
        numOfStudentsText.addModifyListener(modifyListener);

        pagesIndicatorLabel = new Label(this, SWT.NONE);
        pagesIndicatorLabel.setText("1/1");

        this.pack();
        table.pack();
        super.pack();
    }

    public void updateTable() throws UnknownHostException, ClassNotFoundException, IOException {
        List<Student> students = controller.getStudentPage(pageNum, numOfStudentsOnPage, controller.getAllStudents());
        table.removeAll();
        for (Student student : students) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, student.getName());
            item.setText(1, student.getFatherName());
            item.setText(2, Integer.toString(student.getFatherIncome()));
            item.setText(3, student.getMotherName());
            item.setText(4, Integer.toString(student.getMotherIncome()));
            item.setText(5, Integer.toString(student.getNumOfBrothers()));
            item.setText(6, Integer.toString(student.getNumOfSisters()));
        }
        pagesIndicatorLabel.setText(String.valueOf(pageNum + 1) + "/" + (int) Math.ceil((double) controller.getAllStudents().size() / numOfStudentsOnPage));
        table.pack();
        this.pack();
        //super.pack();
    }

    public void updateTable(List<Student> studentsList) {
        searchStudentList = studentsList;
        List<Student> students = controller.getStudentPage(pageNum, numOfStudentsOnPage, studentsList);
        table.removeAll();
        for (Student student : students) {
            TableItem item = new TableItem(table, SWT.NONE);
            //item.setText(student.name);
            item.setText(0, student.getName());
            item.setText(1, student.getFatherName());
            item.setText(2, Integer.toString(student.getFatherIncome()));
            item.setText(3, student.getMotherName());
            item.setText(4, Integer.toString(student.getMotherIncome()));
            item.setText(5, Integer.toString(student.getNumOfBrothers()));
            item.setText(6, Integer.toString(student.getNumOfSisters()));
        }
        pagesIndicatorLabel.setText(String.valueOf(pageNum + 1) + "/" + (int) Math.ceil((double) searchStudentList.size() / numOfStudentsOnPage));
        table.pack();
        this.pack();
        //super.pack();
    }
}