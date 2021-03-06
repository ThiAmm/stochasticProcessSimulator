package ammon.thilo.app.stochproc.GeometricBrownianMotion;

import ammon.thilo.app.stochproc.BrownianMotion.BrownianMotionController;
import ammon.thilo.app.stochproc.StochProcessesController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by thilo on 12.04.17.
 */


public class GeometricBrownianMotionFrame extends Stage {
    StochProcessesController stochasticprocessesCtrl;
    public GeometricBrownianMotionFrame(StochProcessesController stochasticprocessesCtrl) {
        this.stochasticprocessesCtrl = stochasticprocessesCtrl;
        init();
    }

    private void init() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        this.setTitle("Add geometric brownian motion");

        Scene sc = new Scene(grid, 400, 150);

        Button createProcess = new Button();
        createProcess.setText("Create geometric brownian motion");

        Label lb = new Label("Drift (Mu):");
        final TextField tfmu = new TextField();
        tfmu.setPromptText("0");

        GridPane.setConstraints(lb, 0, 0);
        GridPane.setConstraints(tfmu, 1, 0);
        grid.getChildren().addAll(lb, tfmu);

        Label lbSigma = new Label("Sigma:");
        final TextField tfSigma = new TextField();
        tfSigma.setPromptText("1");

        GridPane.setConstraints(lbSigma, 0, 1);
        GridPane.setConstraints(tfSigma, 1, 1);
        grid.getChildren().addAll(lbSigma, tfSigma);


        Label lblNumberOfPaths = new Label("Number of Realisations");
        final TextField numberOfPaths = new TextField();
        numberOfPaths.setPromptText("1");
        GridPane.setConstraints(lblNumberOfPaths, 0, 2);
        GridPane.setConstraints(numberOfPaths, 1, 2);
        grid.getChildren().addAll(lblNumberOfPaths, numberOfPaths);


        createProcess.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                for (int i = 0; i < Integer.parseInt(numberOfPaths.getText()); i++) {
                    GeometricBrownianMotionController geometricBrownianMotionController = new GeometricBrownianMotionController();
                    geometricBrownianMotionController.setMu(Double.parseDouble(tfmu.getText()));
                    geometricBrownianMotionController.setSigma(Double.parseDouble(tfSigma.getText()));
                    close();
                    stochasticprocessesCtrl.addProcess(geometricBrownianMotionController);
                }
            }
        });

        GridPane.setConstraints(createProcess, 1, 3);
        grid.getChildren().addAll(createProcess);

        this.setScene(sc);
    }
}

