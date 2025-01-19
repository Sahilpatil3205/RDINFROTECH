import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CALCI extends Application {

    @Override
    public void start(Stage primaryStage) {
     
        Label lblNum1 = new Label("Number 1:");
        TextField txtNum1 = new TextField();
        Label lblNum2 = new Label("Number 2:");
        TextField txtNum2 = new TextField();
        Label lblResult = new Label("Result:");
        TextField txtResult = new TextField();
        txtResult.setEditable(false);
 
        Button btnAdd = new Button("+");
        Button btnSubtract = new Button("-");
        Button btnMultiply = new Button("*");
        Button btnDivide = new Button("/");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(lblNum1, 0, 0);
        grid.add(txtNum1, 1, 0);
        grid.add(lblNum2, 0, 1);
        grid.add(txtNum2, 1, 1);
        grid.add(lblResult, 0, 2);
        grid.add(txtResult, 1, 2);
        grid.add(btnAdd, 0, 3);
        grid.add(btnSubtract, 1, 3);
        grid.add(btnMultiply, 0, 4);
        grid.add(btnDivide, 1, 4);

       
        btnAdd.setOnAction(e -> {
            double num1 = getNumber(txtNum1);
            double num2 = getNumber(txtNum2);
            txtResult.setText(String.valueOf(num1 + num2));
        });

        btnSubtract.setOnAction(e -> {
            double num1 = getNumber(txtNum1);
            double num2 = getNumber(txtNum2);
            txtResult.setText(String.valueOf(num1 - num2));
        });

        btnMultiply.setOnAction(e -> {
            double num1 = getNumber(txtNum1);
            double num2 = getNumber(txtNum2);
            txtResult.setText(String.valueOf(num1 * num2));
        });

        btnDivide.setOnAction(e -> {
            double num1 = getNumber(txtNum1);
            double num2 = getNumber(txtNum2);
            if (num2 != 0) {
                txtResult.setText(String.valueOf(num1 / num2));
            } else {
                txtResult.setText("Error: Division by 0");
            }
        });

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setTitle("Basic Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double getNumber(TextField textField) {
        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            return 0; 
        }
    }

}
