package gui;

import java.text.NumberFormat;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.*;

public class InterestTableGUI extends Application {
	
	private TextArea results;
	private TextField principal, rate;
	private Slider numberOfYearsSlider;
	
	@Override 
	public void start(Stage primaryStage) {
		
		int sceneWidth = 550, sceneHeight = 350;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
				
		// Results box		
		results = new TextArea();
		results.setEditable(false);
		results.setWrapText(true);
		results.setMaxSize(500, 300);
		ScrollPane scrollPane = new ScrollPane(results);
		BorderPane resultsPane = new BorderPane();
		resultsPane.setTop(results);
		resultsPane.setRight(scrollPane);
				
		// Principal Element
		Label principalLabel = new Label("Principal: ");
		principal = new TextField();
		principal.setMaxWidth(150);
		
		// Rate Element
		Label rateLabel = new Label("Rate(Percentage): ");
		rate = new TextField();
		rate.setMaxWidth(120);
		
		// Principal and Rate Pane
		GridPane principalAndRatePane = new GridPane();
		principalAndRatePane.setHgap(horSpaceBetweenNodes);
		principalAndRatePane.setPadding(new Insets(0, 30, 0, 30));
		principalAndRatePane.add(principalLabel, 0, 0);
		principalAndRatePane.add(principal, 1, 0);
		principalAndRatePane.add(rateLabel, 2, 0);
		principalAndRatePane.add(rate, 3, 0);
		
		// Number of Years Element
		Label numberOfYearsLabel = new Label("Number of Years: ");
		numberOfYearsSlider = new Slider();
		numberOfYearsSlider.setSnapToTicks(true);
		numberOfYearsSlider.setPrefSize(300, 20);
		numberOfYearsSlider.setMin(1);
		numberOfYearsSlider.setMax(25);
		numberOfYearsSlider.setValue(25);
		numberOfYearsSlider.setMajorTickUnit(4);
		numberOfYearsSlider.setShowTickMarks(true);
		numberOfYearsSlider.setShowTickLabels(true);
		
		// Number of Years Pane
		GridPane numOfYearsPane = new GridPane();
		numOfYearsPane.setPadding(new Insets(0, 50, 0, 50));
		numOfYearsPane.add(numberOfYearsLabel, 0, 0);
		numOfYearsPane.add(numberOfYearsSlider, 1, 0);
		
		// Simple Interest Button
		Button simpleInterestButton = new Button("SimpleInterest");
		simpleInterestButton.setPrefWidth(160);
		simpleInterestButton.setOnAction(new simpleInterestButtonHandler());
		
		// Compound Interest Button
		Button compoundInterestButton = new Button("CompoundInterest");
		compoundInterestButton.setPrefWidth(160);
		// Anonymous inner class
		compoundInterestButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				StringBuffer strB = new StringBuffer();
				strB.append("Principal: " + NumberFormat.getCurrencyInstance().format(Double.parseDouble(principal.getText())));
				strB.append(", Rate: " + rate.getText());
				strB.append("\nYear, Compound Interest Amount\n");
				int i;
				int sliderValue = (int)numberOfYearsSlider.getValue();
				for (i = 1; i <= sliderValue; i++) {
					strB.append(i + "-->" + Interest.compoundInterestCalculator(principal.getText(), rate.getText(), i) + "\n");
				}
				results.setText(strB.toString());
			}
		});
		
		// Both Interests Button
		Button bothInterestsButton = new Button("BothInterests");
		bothInterestsButton.setPrefWidth(160);
		// Lambda expression
		bothInterestsButton.setOnAction(e -> {
			StringBuffer strB = new StringBuffer();
			strB.append("Principal: " + NumberFormat.getCurrencyInstance().format(Double.parseDouble(principal.getText())));
			strB.append(", Rate: " + rate.getText());
			strB.append("\nYear, Simple Interest Amount, Compound Interest Amount\n");
			int i;
			int sliderValue = (int)numberOfYearsSlider.getValue();
			for (i = 1; i <= sliderValue; i++) {
				strB.append(i + "-->" + Interest.simpleInterestCalculator(principal.getText(), rate.getText(), i));
				strB.append("-->" + Interest.compoundInterestCalculator(principal.getText(), rate.getText(), i) + "\n");
			}
			results.setText(strB.toString());
		});
		
		// Button Pane
		GridPane buttonPane = new GridPane();
		buttonPane.setHgap(4);
		buttonPane.setPadding(new Insets(0, 20, 0, 20));
		buttonPane.add(simpleInterestButton, 0, 0);
		buttonPane.add(compoundInterestButton, 1, 0);
		buttonPane.add(bothInterestsButton, 2, 0);
		
		// Main Pane made up o sub-panes
		GridPane mainPane = new GridPane();
		mainPane.setVgap(verSpaceBetweenNodes);
		mainPane.setPadding(new Insets(paneBorderTop, paneBorderRight, paneBorderBottom, paneBorderLeft));
		mainPane.add(resultsPane, 0, 0);
		mainPane.add(principalAndRatePane, 0, 1);
		mainPane.add(numOfYearsPane, 0, 2);
		mainPane.add(buttonPane, 0, 3);
		
		Scene scene = new Scene(mainPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Non-anonymous inner class
	private class simpleInterestButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			StringBuffer strB = new StringBuffer();
			strB.append("Principal: " + NumberFormat.getCurrencyInstance().format(Double.parseDouble(principal.getText())));
			strB.append(", Rate: " + rate.getText());
			strB.append("\nYear, Simple Interest Amount\n");
			int i;
			int sliderValue = (int)numberOfYearsSlider.getValue();
			for (i = 1; i <= sliderValue; i++) {
				strB.append(i + "-->" + Interest.simpleInterestCalculator(principal.getText(), rate.getText(), i) + "\n");
			}
			results.setText(strB.toString());
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
