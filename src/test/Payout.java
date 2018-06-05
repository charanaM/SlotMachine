package test;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
public class Payout {
 
    public void start() {
    	//new grid pane 
    	GridPane root = new GridPane();
    	//new stage
    	Stage stage = new Stage();
    	//position setting
    	root.setAlignment(Pos.CENTER);
    	//setting frame title
        stage.setTitle("Payout Table");
        //setting frame size
        Scene scene  = new Scene(root, 1407,789);
        
        stage.setScene(scene);
        stage.show();
        
        root.setStyle(
        		//set the background image
				"-fx-background-image : url('/images/Paytable.jpg')");
    }
 
}