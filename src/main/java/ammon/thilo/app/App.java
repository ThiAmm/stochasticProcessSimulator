package ammon.thilo.app;

import ammon.thilo.app.stochproc.RandomWalkController;
import ammon.thilo.app.Simulation.SimulationRunnable;
import ammon.thilo.app.stochproc.RandomWalkFrame;
import ammon.thilo.app.stochproc.StochProcessesController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;

public class App extends Application {
    StochProcessesController stctrl = new StochProcessesController();

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
        FlowPane btnsStochasticProcesses = new FlowPane();

        btnsStochasticProcesses.setPadding(new Insets(5, 0, 5, 0));
        btnsStochasticProcesses.setVgap(4);
        btnsStochasticProcesses.setHgap(4);
        btnsStochasticProcesses.setPrefWrapLength(170); // preferred width allows for two columns
        btnsStochasticProcesses.setStyle("-fx-background-color: DAE6F3;");

        ImageView pages[] = new ImageView[4];
        final RandomWalkFrame rwFrame = new RandomWalkFrame(stctrl);
        pages[0] = new ImageView(
                    new Image(App.class.getResourceAsStream(
                            "RandomWalk.png")));

        pages[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                rwFrame.show();
            }
        });

        btnsStochasticProcesses.getChildren().add(pages[0]);
        bp.setLeft(btnsStochasticProcesses);



        final SimulationRunnable sim = new SimulationRunnable(stctrl, stctrl.getStochProcCtrls(),1);
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
    }

}
