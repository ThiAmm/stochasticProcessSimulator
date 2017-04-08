package ammon.thilo.app.stochproc;

import ammon.thilo.app.HelperFunctions.StringHelperFunctions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by thilo on 07.04.17.
 */
public class PoissonProcessFrame extends Stage {
    StochProcessesController stochasticprocessesCtrl;
    public PoissonProcessFrame(StochProcessesController stochasticprocessesCtrl) {
        this.stochasticprocessesCtrl = stochasticprocessesCtrl;
        init();
    }

    private void init() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        this.setTitle("Add Poisson Process");

        Scene sc = new Scene(grid, 400, 150);

        Button createProc = new Button();
        createProc.setText("Create Poisson Process");

        Label lb = new Label("Intensity (Lambda):");
        final TextField tflambda = new TextField();
        tflambda.setPromptText("1");

        GridPane.setConstraints(lb, 0, 0);
        GridPane.setConstraints(tflambda, 1, 0);
        grid.getChildren().addAll(lb, tflambda);

        Label lblNumberOfPaths = new Label("Number of Realisations");
        final TextField numberOfPaths = new TextField();
        numberOfPaths.setPromptText("1");
        GridPane.setConstraints(lblNumberOfPaths, 0, 2);
        GridPane.setConstraints(numberOfPaths, 1, 2);
        grid.getChildren().addAll(lblNumberOfPaths, numberOfPaths);


        createProc.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                for (int i = 0; i < Integer.parseInt(numberOfPaths.getText()); i++) {
                    PoissonProcessController poissonProcessController = new PoissonProcessController();
                    poissonProcessController.setLambda(Double.parseDouble(tflambda.getText()));
                    close();
                    stochasticprocessesCtrl.addProcess(poissonProcessController);
                }
            }
        });

        GridPane.setConstraints(createProc, 1, 3);
        grid.getChildren().addAll(createProc);

        this.setScene(sc);
    }
}

