package src.util.listener;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import src.util.controller.Controller;
import src.util.view.Window;

public class AddListener implements Listener {

    private Controller controller;
    private Window window;
    private Shell parent;

    public AddListener(Shell parent, Controller controller, Window window) {
        this.parent = parent;
        this.controller = controller;
        this.window = window;
    }

    @Override
    public void handleEvent(Event e) {
        Shell child = new Shell(parent);
        child.setText("Dadańnie...");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 10;
        rowLayout.marginBottom = 10;
        rowLayout.marginLeft = 5;
        rowLayout.marginRight = 5;
        rowLayout.spacing = 5;
        child.setLayout(rowLayout);

        Label studentNameLabel = new Label(child, SWT.NONE);
        studentNameLabel.setText("Uviedzicie imia studenta:");

        Text studentNameText = new Text(child, SWT.NONE);
        studentNameText.setLayoutData(new RowData(200, 20));

        Label fatherNameLabel = new Label(child, SWT.NONE);
        fatherNameLabel.setText("Uviedzicie imia ajca:");

        Text fatherNameText = new Text(child, SWT.NONE);
        fatherNameText.setLayoutData(new RowData(200, 20));

        Label fatherIncomeLabel = new Label(child, SWT.NONE);
        fatherIncomeLabel.setText("Uviedzicie zarobak ajca:");

        Text fatherIncomeText = new Text(child, SWT.NONE);
        fatherIncomeText.setLayoutData(new RowData(200, 20));

        Label motherNameLabel = new Label(child, SWT.NONE);
        motherNameLabel.setText("Uviedzicie imia matki:");

        Text motherNameText = new Text(child, SWT.NONE);
        motherNameText.setLayoutData(new RowData(200, 20));

        Label motherIncomeLabel = new Label(child, SWT.NONE);
        motherIncomeLabel.setText("Uviedzicie zarobak matki:");

        Text motherIncomeText = new Text(child, SWT.NONE);
        motherIncomeText.setLayoutData(new RowData(200, 20));

        Label numOfBrothersLabel = new Label(child, SWT.NONE);
        numOfBrothersLabel.setText("Uviedzicie kolkaść bratoŭ:");

        Text numOfBrothersText = new Text(child, SWT.NONE);
        numOfBrothersText.setLayoutData(new RowData(200, 20));

        Label numOfSistersLabel = new Label(child, SWT.NONE);
        numOfSistersLabel.setText("Uviedzicie kolkaść siostraŭ:");

        Text numOfSistersText = new Text(child, SWT.NONE);
        numOfSistersText.setLayoutData(new RowData(200, 20));

        Button proceedButton = new Button(child, SWT.PUSH);
        proceedButton.setText("Dadaj");
        proceedButton.setLayoutData(new RowData(100, 30));

        Listener proceedListener = new Listener() {

            @Override
            public void handleEvent(Event arg0) {
                String studentName = studentNameText.getText();
                String fatherName = fatherNameText.getText();

                int fatherIncome = -1;
                try {
                    fatherIncome = Integer.parseInt(fatherIncomeText.getText());
                } catch (java.lang.NumberFormatException e) {
                    System.out.println("Father income is empty.");
                }

                String motherName = motherNameText.getText();
                int motherIncome = -1;
                try {
                    motherIncome = Integer.parseInt(motherIncomeText.getText());
                } catch (java.lang.NumberFormatException e) {
                    System.out.println("Mother income is empty.");
                }

                int numOfBrothers;
                try {
                    numOfBrothers = Integer.parseInt(numOfBrothersText.getText());
                } catch (java.lang.NumberFormatException e) {
                    numOfBrothers = 0;
                }

                int numOfSisters;
                try {
                    numOfSisters = Integer.parseInt(numOfSistersText.getText());
                } catch (java.lang.NumberFormatException e) {
                    numOfSisters = 0;
                }

                if (studentName != "" && fatherName != "" && motherName != "" && fatherIncome != -1
                        && motherIncome != -1) {
                    try {
                        controller.addStudent(studentName, fatherName, motherName, fatherIncome, motherIncome,
                                numOfBrothers, numOfSisters);
                    } catch (IOException | ClassNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        window.updateTable();
                    } catch (ClassNotFoundException | IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    child.dispose();
                }
            }
            
        };
        proceedButton.addListener(SWT.MouseDown, proceedListener);

        child.pack();
        child.open();
    }
}