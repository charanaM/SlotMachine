package test;

//import java.io.File;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
import java.util.Collections;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Reel extends Application implements Runnable{
	
	
 	//creating arraylist to store images of the the reel
 	ArrayList<Symbol> symbarr = new ArrayList<Symbol>(6);
 	
 	//objects for each image
 	Symbol s1 = new Symbol();
 	Symbol s2 = new Symbol();
 	Symbol s3 = new Symbol();
 	Symbol s4 = new Symbol();
 	Symbol s5 = new Symbol();
 	Symbol s6 = new Symbol();
 
 	//creating new thread object
 	Thread thread = new Thread(this);
 	
 	//variables to store images in the reel
 	volatile Image image;
 	volatile ImageView imageView;
 
 	//variable to determine the index of the image in the array
 	int index;
 	
 	//passing parameters
 	Reel(Image img, ImageView imageView){
 		
 		this.image=img;
 		this.imageView=imageView;
 		
 		//setting image for each symbol object
 		s1.setImage("/images/bell.png");
 		s2.setImage("/images/cherry.png");
 		s3.setImage("/images/lemon.png");
 		s4.setImage("/images/plum.png");
 		s5.setImage("/images/redseven.png");
 		s6.setImage("/images/watermelon.png");
 		
 		//setting value for each symbol object
 		s1.setValue(6);
 		s2.setValue(2);
 		s3.setValue(3);
 		s4.setValue(4);
 		s5.setValue(7);
 		s6.setValue(5);
 		
 		//adding symbol objects to the arraylist
 		symbarr.add(s1);
 		symbarr.add(s2);
 		symbarr.add(s3);
 		symbarr.add(s4);
 		symbarr.add(s5);
 		symbarr.add(s6);
 		
 		
 	//shuffle arraylist 	
 	Collections.shuffle(symbarr);
 	}
 	
	public void run() {
		
		
				try {
					//array index
					for(int i=0; i>=0; i++){
						
						if(i>5){
							
							i=0;
						}
						//get the image reel, accordingly
					this.image = new Image(Reel.class.getResourceAsStream(this.symbarr.get(i).image));
					//get the index of the current image
					index = i;
					//set image reel
				    this.imageView.setImage(image);
					
				    //setting thread sleep time
					Thread.sleep(80);
					
					}
					//exception handling
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
	}
	
	public void start(Stage primaryStage) throws Exception {
	
	}
	

}
