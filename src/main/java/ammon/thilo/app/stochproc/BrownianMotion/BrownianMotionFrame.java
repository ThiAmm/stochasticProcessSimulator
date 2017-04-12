package ammon.thilo.app.stochproc.BrownianMotion;

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
 * Created by thilo on 11.04.17.
 */

public class BrownianMotionFrame extends Stage {
    StochProcessesController stochasticprocessesCtrl;
    public BrownianMotionFrame(StochProcessesController stochasticprocessesCtrl) {
        this.stochasticprocessesCtrl = stochasticprocessesCtrl;
        init();
    }

    private void init() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        this.setTitle("Add Brownian motion");

        Scene sc = new Scene(grid, 400, 150);

        Button createProcess = new Button();
        createProcess.setText("Create Brownian motion");

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
                    BrownianMotionController brownianMotionController = new BrownianMotionController();
                    brownianMotionController.setMu(Double.parseDouble(tfmu.getText()));
                    brownianMotionController.setSigma(Double.parseDouble(tfSigma.getText()));
                    close();
                    stochasticprocessesCtrl.addProcess(brownianMotionController);
                }
            }
        });

        GridPane.setConstraints(createProcess, 1, 3);
        grid.getChildren().addAll(createProcess);

        this.setScene(sc);
    }
}

