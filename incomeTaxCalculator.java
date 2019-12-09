import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class incomeTaxCalculator extends Application {
	Label ftrmsg;
	Label ftamsg;
	Label nystrmsg;
	Label nystamsg;
	Label titmsg;
	TextField income;
	ComboBox<String> statusComboBox;
	
	public void start(Stage primaryStage) {
	    // Create a pane to display the menu
	    // Pane pane = new Pane();
		
	    /* Using GridPane instead of Pane */
	    GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setPadding(new Insets(25, 25, 25, 25));
			grid.setHgap(10);
			grid.setVgap(10);

	    // Create a scene and place it in the stage
	    Scene scene = new Scene(grid, 550, 340);
	    	primaryStage.setTitle("Income Tax Calculator");
	    	
	    	
	    grid.add(new Label("Income: "), 0, 0);
	 	income = new TextField();
	 	grid.add(income, 1, 0);
	 	
	 	statusComboBox = new ComboBox<String>();
	    statusComboBox.getItems().addAll("Single", "Married");      
	    grid.add(new Label("Filing as: "), 0, 2);
	    grid.add(statusComboBox, 1, 2);
	     
	    grid.add(new Label("Federal Tax Rate: "), 0, 4);
	    ftrmsg = new Label();
	    ftrmsg.setWrapText(true);
	    ftrmsg.setPrefWidth(300);
	    ftrmsg.setWrapText(true);
		grid.add(ftrmsg, 1, 4);
		
	    grid.add(new Label("Federal Tax Amount: "), 0, 5);
	    ftamsg = new Label();
	    ftamsg.setWrapText(true);
	    ftamsg.setPrefWidth(300);
	    ftamsg.setWrapText(true);
	    grid.add(ftamsg, 1, 5);
	    
	    grid.add(new Label("NYS Tax Rate: "), 0, 7);
	    nystrmsg = new Label();
	    nystrmsg.setWrapText(true);
	    nystrmsg.setPrefWidth(300);
	    nystrmsg.setWrapText(true);
	    grid.add(nystrmsg, 1, 7);
	    
	    grid.add(new Label("NYS Tax Amount: "), 0, 8);
	    nystamsg = new Label();
	    nystamsg.setWrapText(true);
	    nystamsg.setPrefWidth(300);
	    nystamsg.setWrapText(true);
	    grid.add(nystamsg, 1, 8);
	    
	    grid.add(new Label("Total Income Tax: "), 0, 10);
	    titmsg = new Label();
	    titmsg.setWrapText(true);
	    titmsg.setPrefWidth(300);
	    grid.add(titmsg, 1, 10);
	    
	    Button btn = new Button();
	    btn.setText("Calculate");
	    btn.setOnAction(event -> calculateButtonClicked());
	    HBox buttonBox = new HBox(15);
		buttonBox.getChildren().add(btn);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		grid.add(buttonBox, 0, 12, 2, 1);
	    	
	    primaryStage.setScene(scene); // Place the scene in the stage
	 	primaryStage.show(); // Display the stage   
	}
	
	private void calculateButtonClicked() {
		Double taxNY;
		Double federalTax;
		String nysTaxRateDecision;
		String fedTaxRateDecision;
		
		taxNY = computeNYSTax();
		nystamsg.setText("$" + taxNY);
		federalTax = computeFederalTax();
		ftamsg.setText("$" + federalTax);
		nysTaxRateDecision = decideNYSTaxRate();
		nystrmsg.setText(nysTaxRateDecision);
		fedTaxRateDecision = decideFederalTaxRate();
		ftrmsg.setText(fedTaxRateDecision);
		titmsg.setText("$" + (taxNY+federalTax));
	}
	
	public double computeNYSTax () {
		double realIncome = Double.parseDouble(income.getText());
		double nysTax = 0;
		
		if ((realIncome > 21400) && (realIncome <= 80650)) {
			nysTax = 1042+(0.0633*(realIncome-21400));
				
		} else if ((realIncome > 80650) && (realIncome <= 215400)) {
			nysTax = 4793+(0.0657*(realIncome-80650));	
		}
		return nysTax;	
	}
	
	public double computeFederalTax () {
		double realIncome2 = Double.parseDouble(income.getText());
		double fedTax = 0;
		
		if ((realIncome2 > 38700) && (realIncome2 <= 82500)) {
			fedTax = realIncome2*0.22;
				
		} else if ((realIncome2 > 82500) && (realIncome2 <= 157500)) {
			fedTax = realIncome2*0.24;	
		}
		return fedTax;	
	}
	
	public String decideNYSTaxRate () {
		double realIncome3 = Double.parseDouble(income.getText());
		String nysTaxRate = "";
		
		if ((realIncome3 > 21400) && (realIncome3 <= 80650)) {
			nysTaxRate = "$1,042 plus 6.33% of the excess over $21,400";	
			
		} else if ((realIncome3 > 80650) && (realIncome3 <= 215400)) {
			nysTaxRate = "$4,793 plus 6.57% of the excess over $80650";	
		  }
		return nysTaxRate;		
	}
	
	public String decideFederalTaxRate () {
		double realIncome4 = Double.parseDouble(income.getText());
		String federalTaxRate = "";
		
		if ((realIncome4 > 38700) && (realIncome4 <= 82500)) {
			federalTaxRate = ("22% of " + realIncome4);
				
		} else if ((realIncome4 > 82500) && (realIncome4 <= 157500)) {
			federalTaxRate = ("24% of " + realIncome4);	
		  }
		return federalTaxRate;	
	}		
		
	public static void main(String[] args) {
	    launch(args);
}

}
