package src.listener;

import java.io.IOException;

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

import src.controller.Controller;
import src.view.Window;

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

        Label fatherNameLabel = new Label(child, SWT.NONE);
        fatherNameLabel.setText("Imia ajca:");

        Text fatherNameText = new Text(child, SWT.NONE);
        fatherNameText.setLayoutData(new RowData(200, 20));

        Label motherNameLabel = new Label(child, SWT.NONE);
        motherNameLabel.setText("Imia matki:");

        Text motherNameText = new Text(child, SWT.NONE);
        motherNameText.setLayoutData(new RowData(200, 20));

        Label numOfBrothersLabel = new Label(child, SWT.NONE);
        numOfBrothersLabel.setText("Kolkaść bratoŭ:");

        Text numOfBrothersText = new Text(child, SWT.NONE);
        numOfBrothersText.setLayoutData(new RowData(200, 20));

        Label numOfSistersLabel = new Label(child, SWT.NONE);
        numOfSistersLabel.setText("Kolkaść siostraŭ:");

        Text numOfSistersText = new Text(child, SWT.NONE);
        numOfSistersText.setLayoutData(new RowData(200, 20));

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
                int studentsDeleted = 0;
                try {
                    studentsDeleted = controller.deleteStudents(nameText.getText(), fatherNameText.getText(), motherNameText.getText(),
                    numOfBrothersText.getText(), numOfSistersText.getText(),
                    upperBoundText.getText(), lowerBoundText.getText(),
                    fatherIncomeSearchCheck.getSelection(), motherIncomeSearchCheck.getSelection(),
                    lowerBoundCheck.getSelection(), upperBoundCheck.getSelection());
                } catch (ClassNotFoundException | IOException | InterruptedException e2) {
                    e2.printStackTrace();
                }
                Shell dialog = new Shell(child);
                dialog.setText("Vynik");
                dialog.setLayout(new FillLayout());
                Label resultLabel = new Label(dialog, SWT.NONE);
                if (studentsDeleted != 0) {
                    try {
                        window.updateTable();
                    } catch (ClassNotFoundException | IOException | InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    resultLabel.setText("Było vydalena " + studentsDeleted + " studentaŭ.");
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