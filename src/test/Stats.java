package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Stats extends SlotMachine {
	
	
	//start method of the stats class
	public void start() {

		//grid pane object for alignment of the stats frame
		GridPane root = new GridPane();

		Stage stage = new Stage();
		//setting frame scene with height and width
		Scene scene = new Scene(root, 500, 500);

		//pane layout alignment
		root.setAlignment(Pos.CENTER);

		root.setPadding(new Insets(50, 50, 50, 50));

		//frame background image
		root.setStyle("-fx-background-image:url('/images/statbg.jpg')");
		scene.setRoot(root);
		//set pane visibility
		root.setVisible(true);

		//labels for each result
		Label wins = new Label();
		Label loses = new Label();
		Label timesPlayed = new Label();
		Label scoreGained = new Label();
		Label scoreAvailable = new Label();
		Label betPerGame = new Label();

		//creating button for save
		Button save = new Button("Save Statistics");
		//creating button for bar graph
		Button chart = new Button("Bar Chart");

		//frame element styling
		wins.setStyle(
				"  -fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);   -fx-stroke: gold;  -fx-stroke-width: 3;");
		loses.setStyle(
				"  -fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);   -fx-stroke: gold;  -fx-stroke-width: 3;");
		timesPlayed.setStyle(
				"  -fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);   -fx-stroke: gold;  -fx-stroke-width: 3;");
		scoreGained.setStyle(
				"  -fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);   -fx-stroke: gold;  -fx-stroke-width: 3;");
		scoreAvailable.setStyle(
				"  -fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);   -fx-stroke: gold;  -fx-stroke-width: 3;");
		betPerGame.setStyle(
				"  -fx-font: 25px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);   -fx-stroke: gold;  -fx-stroke-width: 3;");
		save.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: gold; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");
		chart.setStyle(
				"-fx-background-color:black; -fx-background-radius: 30; -fx-backgroud-radius :5 ,4,3, 5 ;  -fx-background-insets: 0,1,2,0; -fx-text-fill: gold; -fx-font-family: 'Arial';   -fx-font-size: 22px; -fx-padding: 10 20 10 20;");

		//set label font colors
		wins.setTextFill(Color.WHITE);
		loses.setTextFill(Color.WHITE);
		timesPlayed.setTextFill(Color.WHITE);
		scoreGained.setTextFill(Color.WHITE);
		scoreAvailable.setTextFill(Color.WHITE);
		betPerGame.setTextFill(Color.WHITE);

		// root.add(sTitle, 0, 10);
		root.add(wins, 0, 30);
		root.add(loses, 0, 40);
		root.add(timesPlayed, 0, 50);
		root.add(scoreGained, 0, 60);
		root.add(scoreAvailable, 0, 70);
		root.add(betPerGame, 0, 80);
		root.add(save, 0, 90);
		root.add(chart, 0, 100);

		stage.setScene(scene);
		stage.show();
		stage.setTitle("Statistics");

		//set label text for number of wins
		wins.setText("Number of wins \t\t: " + winNum);
		//set label text for number of loses
		loses.setText("Number of loses \t\t: " + loseNum);
		//set label text for number of times played
		timesPlayed.setText("Times played \t\t: " + playedNum);
		//set label text for number of credits won
		scoreGained.setText("Total credits earned \t: " + creditsWon);
		//set label text for total number of coins
		scoreAvailable.setText("Credits left \t\t: " + totalCoins);
		//set label text for credits netted per game
		betPerGame.setText("Average credits\nnetted per game \t: " + average);

		//button click action for save
		save.setOnAction(e -> {

			try {
				//method calling for write method
				write();
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				
				e1.printStackTrace();
			}

		});
		
		//button click action for bar chart
		chart.setOnAction(e -> {
			new barchart().start(stage);
		});

	}

	//method to save the statistics to a text file
	static void write() throws FileNotFoundException, UnsupportedEncodingException {
		
		//new printwriter
		PrintWriter writer = new PrintWriter("SlotMachine.txt", "UTF-8");
		//line for number of wins
		writer.println("Number of wins : " + winNum);
		//line for number of loses
		writer.println("Number of loses : " + loseNum);
		//line for number of games played
		writer.println("Times played : " + playedNum);
		//line for number of credits won
		writer.println("Total credits earned : " + creditsWon);
		//line for total coins
		writer.println("Credits left : " + totalCoins);
		//line for the average coins netted per game
		writer.println("Average credits netted per game : " + average);

		writer.close();
		//save status
		System.out.println("saved");

	}

}
