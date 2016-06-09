package ammon.thilo.app;

import stochproc.StochProcessController;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class App extends Application{
    StochProcessController stctrl = new StochProcessController(2);

    BorderPane bp = null;

    public static void main( String[] args ){
    launch(args);
    }

    @Override 
    public void start(Stage stage) {
    bp = new BorderPane();
    bp.setCenter(stctrl.getCoorSys());
    stage.setTitle("Viewer for stochastic processes");

    initComp();

    Scene scene = new Scene(bp,800,600);
    stage.setScene(scene);
    stage.show();
  }

  private void initComp(){
    Button btnRandomWalk = new Button();
    btnRandomWalk.setText("Add simple random walk");
    btnRandomWalk.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        stctrl.showRandomWalkFrame();  
      }
    });
    bp.setLeft(btnRandomWalk);
  }
}
