package ammon.thilo.app;

import ammon.thilo.app.stochproc.RandomWalkController;
import ammon.thilo.app.Simulation.SimulationRunnable;
import ammon.thilo.app.stochproc.RandomWalkFrame;
import ammon.thilo.app.stochproc.StochProcessesController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;

public class App extends Application {
    StochProcessesController stctrl = new StochProcessesController(2);

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
        initRandomWalkFrame();
        final SimulationRunnable sim = new SimulationRunnable(stctrl, stctrl.getStochProcCtrls(),1);
        final Thread t = new Thread(sim);
        t.setDaemon(true);

        VBox vbox = new VBox();

        Button btnStartSim = new Button();
        btnStartSim.setText("Start simulation");
        btnStartSim.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                t.start();
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
    }

    public void initRandomWalkFrame(){
        final RandomWalkFrame rwFrame = new RandomWalkFrame(stctrl);

        final Button btnRandomWalk = new Button();
        btnRandomWalk.setText("Add simple random walk");
        btnRandomWalk.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                rwFrame.show();
            }
        });
        bp.setLeft(btnRandomWalk);

    }
}
