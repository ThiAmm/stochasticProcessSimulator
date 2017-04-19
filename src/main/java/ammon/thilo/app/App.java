package ammon.thilo.app;

import ammon.thilo.app.Simulation.SimulationRunnable;
import ammon.thilo.app.stochproc.BrownianMotion.BrownianMotionFrame;
import ammon.thilo.app.stochproc.GeometricBrownianMotion.GeometricBrownianMotionFrame;
import ammon.thilo.app.stochproc.PoissonProcessFrame;
import ammon.thilo.app.stochproc.RandomWalkFrame;
import ammon.thilo.app.stochproc.StochProcessesController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public class App extends Application {
    final StochProcessesController stctrl = new StochProcessesController();
    final static double timeBetweenSimulationPoints = 0.01;
    BorderPane bp = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        bp = new BorderPane();
        bp.setCenter(stctrl.getCoorSys());
        stage.setTitle("Viewer for stochastic processes");

        initComp();

        Scene scene = new Scene(bp, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void initComp() {
        bp.setLeft(createButtonsStochasticProcesses());

        final SimulationRunnable sim = new SimulationRunnable(stctrl, stctrl.getStochProcCtrls(),timeBetweenSimulationPoints);
        final Thread t = new Thread(sim);
        t.setDaemon(true);

        VBox vbox = new VBox();

        final Button btnStartSim = new Button();
        btnStartSim.setText("Start simulation");
        btnStartSim.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                t.start();
                btnStartSim.setDisable(true);
            }
        });

        Button pauseSimulation = new Button();
        pauseSimulation.setText("Pause simulation");
        pauseSimulation.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                sim.setSimulationActive(false);
            }
        });

        Button continueSimulation = new Button();
        continueSimulation.setText("Continue simulation");
        continueSimulation.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                sim.setSimulationActive(true);
            }
        });
        pauseSimulation.setMaxWidth(Double.MAX_VALUE);
        continueSimulation.setMaxWidth(Double.MAX_VALUE);
        vbox.getChildren().addAll(btnStartSim, pauseSimulation, continueSimulation);

        bp.setRight(vbox);

        HBox bottomBox = new HBox();
        TreeItem<String> treeItemMarkovProcesses =
                new TreeItem<String>("Markov processes");
        TreeItem<String> treeItemOtherProcesses =
                new TreeItem<String>("Other processes");
        TreeView<String> treeViewStochasticProcesses = new TreeView<String>(new TreeItem<String>("Stochastic Processes"));
        treeViewStochasticProcesses.getRoot().getChildren().addAll(treeItemMarkovProcesses, treeItemOtherProcesses);
        TextArea taInformation = new TextArea();
        taInformation.setPrefRowCount(10);
        bottomBox.getChildren().addAll(treeViewStochasticProcesses,taInformation);
        bottomBox.setPrefHeight(250);
        bp.setBottom(bottomBox);
    }

    private FlowPane createButtonsStochasticProcesses() {
        FlowPane btnsStochasticProcesses = new FlowPane();

        btnsStochasticProcesses.setPadding(new Insets(5, 0, 5, 0));
        btnsStochasticProcesses.setVgap(4);
        btnsStochasticProcesses.setHgap(4);
        btnsStochasticProcesses.setPrefWrapLength(170); // preferred width allows for two columns
        btnsStochasticProcesses.setStyle("-fx-background-color: DAE6F3;");

        final ArrayList<ButtonStochasticProcess> fileNamesButtons = new ArrayList<ButtonStochasticProcess>();
        fileNamesButtons.add(new ButtonStochasticProcess("RandomWalk.png", new RandomWalkFrame(stctrl)));
        fileNamesButtons.add(new ButtonStochasticProcess("PoissonProcess.png", new PoissonProcessFrame(stctrl)));
        fileNamesButtons.add(new ButtonStochasticProcess("BrownianMotion.png", new BrownianMotionFrame(stctrl)));
        fileNamesButtons.add(new ButtonStochasticProcess("GeometricBrownianMotion.png", new GeometricBrownianMotionFrame(stctrl)));

        ImageView pages[] = new ImageView[fileNamesButtons.size()];
        for (int i = 0; i < fileNamesButtons.size(); i++) {
            pages[i] = createImageViewWithIcon(fileNamesButtons.get(i).getFileName());
            final Stage stochasticProcessCreatorFrame = fileNamesButtons.get(i).getStochasticProcessCreatorFrame();
            pages[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                     stochasticProcessCreatorFrame.show();
                }
            });
            btnsStochasticProcesses.getChildren().add(pages[i]);
        }
        return btnsStochasticProcesses;
    }

    private ImageView createImageViewWithIcon(String fileName){
        return new ImageView(
                new Image(App.class.getResourceAsStream(
                        fileName)));

    }

    public class ButtonStochasticProcess{
        String fileName;
        Stage stochasticProcessCreatorFrame;
        public ButtonStochasticProcess(String fileName, Stage stochasticProcessCreatorFrame) {
            this.fileName = fileName;
            this.stochasticProcessCreatorFrame = stochasticProcessCreatorFrame;
        }

        public String getFileName() {
            return fileName;
        }

        public Stage getStochasticProcessCreatorFrame() {
            return stochasticProcessCreatorFrame;
        }
    }
}
