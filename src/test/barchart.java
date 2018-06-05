package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class barchart extends SlotMachine {
	//new string for wins
	final static String wins = "Wins";
	//new string for loses
    final static String loses = "Loses";
  //new string for average bet
    final static String avrg = "Average Bet";
 
    @Override public void start(Stage stage) {
    	//set the frame title
        stage.setTitle("Statistics Chart");
        //axis for category line
        final CategoryAxis xAxis = new CategoryAxis();
        //axis for number line
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> 
        //creating new barchart
        bc = new BarChart<String,Number>(xAxis,yAxis);
        //set bar chart summary 
        bc.setTitle("Player Summary");
        //set axis labels
        xAxis.setLabel("");       
        yAxis.setLabel("");
 
        //new chart series
        XYChart.Series series1 = new XYChart.Series();
        //add number of wins data
        series1.getData().add(new XYChart.Data(wins, winNum));
      //add number of loses data
        series1.getData().add(new XYChart.Data(loses, loseNum));
      //add number of average data
        series1.getData().add(new XYChart.Data(avrg, average));      
        
        
        //set frame size
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}