package src.util.listener;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import src.util.controller.Controller;
import src.util.model.Student;
import src.util.view.Window;

public class DeleteListener implements Listener {

    private Controller controller;
    private Window window;
    private Shell parent;

    public DeleteListener(Shell parent, Controller controller, Window window) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent, SWT.SHELL_TRIM | SWT.RESIZE);
        child.setText("Vydaleńnie...");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        child.setLayout(rowLayout);

        Label nameLabel = new Label(child, SWT.NONE);
        nameLabel.setText("Imia studenta:");

        Text nameText = new Text(child, SWT.NONE);
        nameText.setLayoutData(new RowData(200, 20));

        Button nameSearchCheck = new Button(child, SWT.CHECK);
        nameSearchCheck.setText("Vydali pa jimi");

        Label fatherNameLabel = new Label(child, SWT.NONE);
        fatherNameLabel.setText("Imia ajca:");

        Text fatherNameText = new Text(child, SWT.NONE);
        fatherNameText.setLayoutData(new RowData(200, 20));

        Button fatherNameSearchCheck = new Button(child, SWT.CHECK);
        fatherNameSearchCheck.setText("Vydali pa jimi ajca");

        Label motherNameLabel = new Label(child, SWT.NONE);
        motherNameLabel.setText("Imia matki:");

        Text motherNameText = new Text(child, SWT.NONE);
        motherNameText.setLayoutData(new RowData(200, 20));

        Button motherNameSearchCheck = new Button(child, SWT.CHECK);
        motherNameSearchCheck.setText("Vydali pa jimi matki");

        Label numOfBrothersLabel = new Label(child, SWT.NONE);
        numOfBrothersLabel.setText("Kolkaść bratoŭ:");

        Text numOfBrothersText = new Text(child, SWT.NONE);
        numOfBrothersText.setLayoutData(new RowData(200, 20));

        Button numOfBrothersSearchCheck = new Button(child, SWT.CHECK);
        numOfBrothersSearchCheck.setText("Vydali pa kolkaśći bratoŭ");

        Label numOfSistersLabel = new Label(child, SWT.NONE);
        numOfSistersLabel.setText("Kolkaść siostraŭ:");

        Text numOfSistersText = new Text(child, SWT.NONE);
        numOfSistersText.setLayoutData(new RowData(200, 20));

        Button numOfSistersSearchCheck = new Button(child, SWT.CHECK);
        numOfSistersSearchCheck.setText("Vydali pa kolkaśći siostraŭ");

        Label upperBoundLabel = new Label(child, SWT.NONE);
        upperBoundLabel.setText("Najvyšejšaja miaža zarobku:");

        Text upperBoundText = new Text(child, SWT.NONE);
        upperBoundText.setLayoutData(new RowData(200, 20));

        Label lowerBoundLabel = new Label(child, SWT.NONE);
        lowerBoundLabel.setText("Najnižejšaja miaža zarobku:");

        Text lowerBoundText = new Text(child, SWT.NONE);
        lowerBoundText.setLayoutData(new RowData(200, 20));

        Button upperBoundCheck = new Button(child, SWT.CHECK);
        upperBoundCheck.setText("Vydali nižejšy za najvyšejšy zarobak");

        Button lowerBoundCheck = new Button(child, SWT.CHECK);
        lowerBoundCheck.setText("Vydali vyšejšy za najnižejšy zarobak");

        Button fatherIncomeSearchCheck = new Button(child, SWT.CHECK);
        fatherIncomeSearchCheck.setText("Vydali pa zarobku ajca");

        Button motherIncomeSearchCheck = new Button(child, SWT.CHECK);
        motherIncomeSearchCheck.setText("Vydali pa zarobku matki");

        Button deleteButton = new Button(child, SWT.NONE);
        deleteButton.setText("Vydali");

        Listener proceedListener = new Listener() {
            @Override
            public void handleEvent(Event e) {
                List<Student> students = null;
                try {
                    students = controller.getAllStudents();
                } catch (ClassNotFoundException | IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                if (nameSearchCheck.getSelection()) {
                    String name = nameText.getText();
                    try {
                        students = controller.findByName(name);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }

                if (fatherNameSearchCheck.getSelection()) {
                    String fatherName = fatherNameText.getText();
                    try {
                        students = controller.findByFatherName(fatherName, students);
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                if (motherNameSearchCheck.getSelection()) {
                    String motherName = motherNameText.getText();
                    try {
                        students = controller.findByMotherName(motherName, students);
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                if (numOfBrothersSearchCheck.getSelection()) {
                    int numOfBrothers = Integer.parseInt(numOfBrothersText.getText());
                    try {
                        students = controller.findByNumOfBrothers(numOfBrothers, students);
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                if (numOfSistersSearchCheck.getSelection()) {
                    int numOfSIsters = Integer.parseInt(numOfSistersText.getText());
                    try {
                        students = controller.findByNumOfSisters(numOfSIsters, students);
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                if (fatherIncomeSearchCheck.getSelection()) {
                    if (lowerBoundCheck.getSelection()) {
                        try {
                            students = controller.findByFatherIncomeLower(Integer.parseInt(lowerBoundText.getText()),
                                    students);
                        } catch (NumberFormatException | ClassNotFoundException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    if (upperBoundCheck.getSelection()) {
                        try {
                            students = controller.findByFatherIncomeLower(Integer.parseInt(upperBoundText.getText()),
                                    students);
                        } catch (NumberFormatException | ClassNotFoundException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
                if (motherIncomeSearchCheck.getSelection()) {
                    if (lowerBoundCheck.getSelection()) {
                        try {
                            students = controller.findByFatherIncomeLower(Integer.parseInt(lowerBoundText.getText()),
                                    students);
                        } catch (NumberFormatException | ClassNotFoundException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    if (upperBoundCheck.getSelection()) {
                        try {
                            students = controller.findByFatherIncomeLower(Integer.parseInt(upperBoundText.getText()),
                                    students);
                        } catch (NumberFormatException | ClassNotFoundException | IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
                Shell dialog = new Shell(child);
                dialog.setText("Vynik");
                dialog.setLayout(new FillLayout());
                Label resultLabel = new Label(dialog, SWT.NONE);
                if (students.size() != 0) {
                    try {
                        controller.deleteStudents(students);
                    } catch (ClassNotFoundException | IOException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    try {
                        window.updateTable();
                    } catch (ClassNotFoundException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    resultLabel.setText("Było vydalena " + students.size() + " studentaŭ.");
                } else resultLabel.setText("Takich studentaŭ niama.");
                dialog.pack();
                dialog.open();
            }
            
        };
        deleteButton.addListener(SWT.MouseDown, proceedListener);

        child.pack();
        child.open();
    }
}