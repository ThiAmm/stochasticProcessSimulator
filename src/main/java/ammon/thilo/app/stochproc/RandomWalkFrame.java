package ammon.thilo.app.stochproc;

import ammon.thilo.app.HelperFunctions.StringHelperFunctions;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class RandomWalkFrame extends Stage {
    StochProcessesController stochasticprocessesCtrl;
    public RandomWalkFrame(StochProcessesController stochasticprocessesCtrl) {
        this.stochasticprocessesCtrl = stochasticprocessesCtrl;
        init();
    }

    private void init() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        this.setTitle("Add Random Walk");

        Scene sc = new Scene(grid, 400, 150);

        Button createProc = new Button();
        createProc.setText("Create Random Walk");

        Label lb = new Label("Domain Space (use ';' as seperator):");
        final TextField tfjumps = new TextField();
        tfjumps.setPromptText("1;-1");

        GridPane.setConstraints(lb, 0, 0);
        GridPane.setConstraints(tfjumps, 1, 0);
        grid.getChildren().addAll(lb, tfjumps);

        Label lbprbs = new Label("Probabilities (use ';' as seperator):");
        final TextField tfprbs = new TextField();
        tfprbs.setPromptText("0.5;0.5");

        GridPane.setConstraints(lbprbs, 0, 1);
        GridPane.setConstraints(tfprbs, 1, 1);
        grid.getChildren().addAll(tfprbs, lbprbs);

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
                    RandomWalkController randCtrl = new RandomWalkController();

                    String jumpsAsString = tfjumps.getText();
                    ArrayList<Double> jumps = null;
                    ArrayList<Double> probs = null;
                    try {
                        jumps = StringHelperFunctions.crtDblArrayFrmStrg(jumpsAsString);
                        String probsAsString = tfprbs.getText();
                        probs = StringHelperFunctions.crtDblArrayFrmStrg(probsAsString);
                        try {
                            randCtrl.validateJumpProbsPairs(probs);
                        } catch (IllegalArgumentException iaexcp) {

                        }
                    } catch (Exception excp) {

                    } finally {
                        randCtrl.setJumpsProbsPair(jumps, probs);
                        close();
                    }
                    stochasticprocessesCtrl.addProcess(randCtrl);
                }
            }
        });

        GridPane.setConstraints(createProc, 1, 3);
        grid.getChildren().addAll(createProc);

        this.setScene(sc);
    }
}
 
