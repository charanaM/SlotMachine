package test;

//import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SlotMachine extends Application implements Runnable {
	
	//variables to check the reels' spinning status
	int reel1state = 0;
	int reel2state = 0;
	int reel3state = 0;

	//variables to store user's game-play data
	int bet = 0;
	static int totalCoins = 10;
	int woncredit = 0;
	
	//counters for statistics
	static int winNum = 0;
	static int loseNum = 0;
	static int playedNum = 0;
	static int creditsWon = 0;
	static double totalbet = 0;
	static double average = 0;

	//imageview variables for images reels
	volatile ImageView imgv1;
	volatile ImageView imgv2;
	volatile ImageView imgv3;

	//variables for images
	volatile Image img1;
	volatile Image img2;
	volatile Image img3;

	//variables to store the value assigned to the image on each reel
	int reel1value;
	int reel2value;
	int reel3value;

	//creating 3 reel objects 
	Reel reel1 = new Reel(img1, imgv1);
	Reel reel2 = new Reel(img1, imgv2);
	Reel reel3 = new Reel(img1, imgv3);
	
	//creating label objects in the gui
	Label creditArea = new Label("Credit Amount : "+ totalCoins);
	Label betArea = new Label("Current Bet : " +bet);
	Label score = new Label("Score");

	//creating button objects in the gui
	Button addCoin = new Button("Add Coin");
	Button betOne = new Button("Bet One");
	Button betMax = new Button("Bet Max");
	Button reset = new Button("Reset");
	Button spin = new Button("Spin");
	Button stats = new Button("Statistics");
	Button payout = new Button("Payout");

	


	// ======================================================================================================

	@SuppressWarnings("deprecation")
	public void start(Stage primaryStage) {

		// styling gui elements
		
		//shadow effects
		InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);
		
	
		
		//set styles for gui buttons
		spin.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: gold; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		reset.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: gold; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		stats.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: gold; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		betOne.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: white; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		betMax.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: white; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		addCoin.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: gold; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		payout.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: white; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		
		
		//styling gui labels
		creditArea.setStyle("  -fx-font: 25px Tahoma;    -fx-font-color:white;  ");
		creditArea.setTextFill(Color.WHITE);
		betArea.setStyle("  -fx-font: 25px Tahoma;    -fx-stroke-width: 5;");
		betArea.setTextFill(Color.WHITE);
		score.setStyle(
				"  -fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);   -fx-stroke: gold;  -fx-stroke-width: 3;");
		score.setMaxWidth(300);
		score.setMinWidth(300);
		
		score.setTextFill(Color.WHITE);

		// -------------------------------------------------------------------------

		//declaring new imageviews for reels to get the images accordingly
		imgv1 = new ImageView(new Image(reel1.symbarr.get(0).image));
		imgv2 = new ImageView(new Image(reel2.symbarr.get(0).image));
		imgv3 = new ImageView(new Image(reel3.symbarr.get(0).image));
		
		//============================================================================

		//spin button actions
		spin.setOnAction(e -> {

			//checking whether use has placed a bet
			if (bet != 0) {

			//assigning images to reels
			reel1 = new Reel(img1, imgv1);
			reel2 = new Reel(img1, imgv2);
			reel3 = new Reel(img1, imgv3);

			//starting the reel 1 thread and setting the volatile images
			reel1.thread.start();
			img1 = reel1.image;
			imgv1 = reel1.imageView;

			//starting the reel 2 thread and setting the volatile images
			reel2.thread.start();
			img2 = reel2.image;
			imgv2.setImage(img2);

			//starting the reel 3 thread and setting the volatile images
			reel3.thread.start();
			img3 = reel3.image;
			imgv3.setImage(img3);

			//disable the spin button after clicked once
			spin.setDisable(true);
			
			//if user has not placed any bet
			}else{
				score.setText("You have to place a bet");
			}

		});

		// ========================================================================================================

		//setting reel 1 to stop on mouse click
		imgv1.setOnMouseClicked(e -> {
			reel1.thread.stop();
			//get the value in the current image
			reel1value = reel1.symbarr.get(reel1.index).getValue();
			//set the state as stopped by assigning a specific integer
			reel1state = 1;
			//call the method for calculations
			reelstop();

		});

		//setting reel 2 to stop on mouse click
		imgv2.setOnMouseClicked(e -> {
			reel2.thread.stop();
			//get the value in the current image
			reel2value = reel2.symbarr.get(reel2.index).getValue();
			//set the state as stopped by assigning a specific integer
			reel2state = 1;
			//call the method for calculations
			reelstop();

		});

		//setting reel 3 to stop on mouse click
		imgv3.setOnMouseClicked(e -> {
			reel3.thread.stop();
			//get the value in the current image
			reel3value = reel3.symbarr.get(reel3.index).getValue();
			//set the state as stopped by assigning a specific integer
			reel3state = 1;
			//call the method for calculations
			reelstop();

		});

		// ======================================================================================================

		//reset button actions
		reset.setOnAction(e -> {
			
			bet = 0;
			totalCoins = 10;
			woncredit = 0;
			
			
			winNum = 0;
			loseNum = 0;
			playedNum = 0;
			creditsWon = 0;
			totalbet = 0.0;
			
			//set labels to display current stats
			creditArea.setText("Credit Amount : " + totalCoins);
			betArea.setText("Current Bet : " + bet);
			score.setText("");
			
		});

		// ======================================================================================================

		//calling the statistics window
		stats.setOnAction(e -> {
			average = (totalbet / playedNum);
			new Stats().start();
		});

		// ======================================================================================================

		//set the frame width and height
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1100, 700);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		//set title
		primaryStage.setTitle("Slot Machine");

		GridPane grid = new GridPane();
		
		//grid alignment
		grid.setAlignment(Pos.CENTER);

		//gui background image
		grid.setStyle(
				"-fx-background-image : url('/images/slot2.jpg')");

		//grid layout adjustments
		grid.setPadding(new Insets(50, 50, 50, 50));
		grid.setVgap(2);
		grid.setHgap(5);
		scene.setRoot(grid);

		//label placement
		grid.add(creditArea, 50, 0);
		grid.add(betArea, 50, 40);
		grid.add(score, 50, 60);

		//reel imageview placement
		grid.add(imgv1, 10, 10);
		grid.add(imgv2, 20, 10);
		grid.add(imgv3, 30, 10);
		
		//button placement
		grid.add(addCoin, 50, 5);
		grid.add(betOne, 50, 45);
		grid.add(betMax, 50, 50);
		grid.add(spin, 10, 70);
		grid.add(reset, 20, 70);
		grid.add(stats, 30, 70);
		grid.add(payout, 50, 70);

		//reel1 size
		imgv1.setFitHeight(150);
		imgv1.setFitWidth(150);

		//reel2 size
		imgv2.setFitHeight(150);
		imgv2.setFitWidth(150);

		//reel3 size
		imgv3.setFitHeight(150);
		imgv3.setFitWidth(150);

		primaryStage.show();

		
		//=============================================================================================
		
		//calling method for bet one, on click
		betOne.setOnAction(e -> {
			bet();
		});

		//calling method for bet max, on click
		betMax.setOnAction(e -> {
			betMax();
		});

		//calling method for add coin, on click
		addCoin.setOnAction(e -> {
			addCoin();
		});
		
		//calling method for add coin, on click
		payout.setOnAction(e -> {
			new Payout().start();
		});

	}

	// ===================================================================================================

	//method for bet one
	public void bet() {
		
			
		//check coin balance to place a bet
		if (totalCoins >= 1) { //if sufficient
			
			//increment current bet
			bet += 1;
			//calculate remaining coins
			totalCoins--;
			//set labels
			creditArea.setText("Credit Amount : " + totalCoins);
			betArea.setText("Current Bet : " + bet);
			
		} else { //if credit insufficient
			//prompt user
			creditArea.setText("Credit Amount : Insufficient");
			betArea.setText("Current Bet : " + bet);
		}
	}
	

	// -----------------------------------------------------------------------------
	
	//method for bet max
	public void betMax() {
		
		//check coin balance to place a bet
		if (totalCoins >= 3) { //if sufficient
			//increment current bet
			bet += 3;
			//calculate remaining coins
			totalCoins -= 3;
			//set labels
			creditArea.setText("Credit Amount : " + totalCoins);
			betArea.setText("Current Bet : " + bet);
			//disable the bet max button
			betMax.setDisable(true);
			
		} else {//if credit insufficient
			//prompt user
			creditArea.setText("Credit Amount : Insufficient");
			betArea.setText("Current Bet : " + bet);
		}
		
	}
	

	// -----------------------------------------------------------------------------
	
	//method to add credit
	public void addCoin() {
		//increment coins
		totalCoins++;
		//set label
		creditArea.setText("Credit Amount : " + totalCoins);
	}

	// -----------------------------------------------------------------------------

	//method to calculate score
	public void score() {
		
		//check whether at least two reel are same
			if (reel1value == reel2value || reel2value == reel3value || reel1value == reel3value) {
				
				//all three reels get the same image
				if (reel1value == reel2value && reel2value == reel3value) {
					//calculate score and coins
					woncredit = (reel1value * bet);
					totalCoins = totalCoins + woncredit;
					//set labels
					creditArea.setText("Credit Amount : " + totalCoins);
					score.setText("JACKPOT !!! " + woncredit + " credits");
					//increment number of wins
					winNum++;
					//calculate total credits won
					creditsWon += woncredit;
				

					//only reel 1 and 2 are same
				} else if (reel1value == reel2value) {
					//calculate score and coins
					woncredit = (reel1value * bet)/2 ;
					totalCoins = totalCoins + woncredit;
					//set labels
					creditArea.setText("Credit Amount : " + totalCoins);
					score.setText("You Won " + woncredit + " credits");
					//increment number of wins
					winNum++;
					//calculate total credits won
					creditsWon += woncredit;
				
					//only reel 2 and 3 are same
				} else if (reel2value == reel3value) {
					//calculate score and coins
					woncredit = (reel1value * bet)/2 ;
					totalCoins = totalCoins + woncredit;
					//set labels
					creditArea.setText("Credit Amount : " + totalCoins);
					score.setText("You Won " + woncredit + " credits");
					//increment number of wins
					winNum++;
					//calculate total credits won
					creditsWon += woncredit;
				
				} else {//only reel 1 and 3 are same
					
					//calculate score and coins
					woncredit = (reel1value * bet)/2 ;
					totalCoins = totalCoins + woncredit;
					//set labels
					creditArea.setText("Credit Amount : " + totalCoins);
					score.setText("You Won " + woncredit + " credits");
					//increment number of wins
					winNum++;
					//calculate total credits won
					creditsWon += woncredit;
				}
				//new barchart().start();
			} else { //all three reels are different
				score.setText("You lost");
				loseNum++;
			}

		}
	

	// --------------------------------------------------------------------------------

	public void reelstop() {
		
		//check if all three reels stopped spinning
		if (reel1state == 1 && reel2state == 1 && reel3state == 1) {
			//run the score method to for calculations
			score();
			
			//resetting variables for the next spin
			totalbet += bet;
			bet = 0;
			reel1state = 0;
			reel2state = 0;
			reel3state = 0;
			
			//increment times played
			playedNum++;
			
			//enabling the spin button
			spin.setDisable(false);
			//enabling the bet max button
			betMax.setDisable(false);
			//calculate total coins
			totalCoins += bet;
			//set the current bet to 0
			bet = 0;
			//set labels to display current stats
			betArea.setText("Current Bet : " + bet);
		}

	}

	// =====================================================================================================

	public static void main(String[] args) {
		launch(args);
	}

	// ======================================================================================================

	@Override
	public void run() {

		//starting threads
		reel1.run();
		reel2.run();
		reel3.run();

	}

}
