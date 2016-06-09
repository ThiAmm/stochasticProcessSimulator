package stochproc;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField; 
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class RandomWalkFrame extends Stage{

  RandomWalkFrame(){
      init();
  }
  
  private void init(){
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);
    
    this.setTitle("Add Random Walk");

    Scene sc = new Scene(grid,400,150);

    Button createProc = new Button();
    createProc.setText("Create Random Walk");
    createProc.setOnAction(new EventHandler<ActionEvent>(){
      
      @Override
      public void handle(ActionEvent e){
        
      }
    });


    Label lb = new Label("Domain Space (use ';' as seperator):");
    
    TextField tf = new TextField();
    tf.setPromptText("1;-1");

    GridPane.setConstraints(lb,0,0);
    GridPane.setConstraints(tf,1,0);
    
    grid.getChildren().addAll(lb,tf);

    Label lbprbs = new Label("Probabilities (use ';' as seperator):");

    TextField tfprbs = new TextField();
    tfprbs.setPromptText("0.5;0.5");
    
    GridPane.setConstraints(lbprbs,0,1);
    GridPane.setConstraints(tfprbs,1,1);

    grid.getChildren().addAll(tfprbs,lbprbs);

    GridPane.setConstraints(createProc,1,2);
    grid.getChildren().addAll(createProc);

    this.setScene(sc);
  }
}
 
