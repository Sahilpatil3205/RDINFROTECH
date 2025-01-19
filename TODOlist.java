import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TODOlist extends Application {

    @Override
    public void start(Stage primaryStage) {
       
        ObservableList<String> tasks = FXCollections.observableArrayList();

        ListView<String> taskListView = new ListView<>(tasks);
        TextField taskInputField = new TextField();
        taskInputField.setPromptText("Enter a new task...");
        Button addButton = new Button("Add Task");
        Button editButton = new Button("Edit Task");
        Button deleteButton = new Button("Delete Task");

   
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(taskInputField, addButton, taskListView, editButton, deleteButton);

        addButton.setOnAction(e -> {
            String task = taskInputField.getText().trim();
            if (!task.isEmpty()) {
                tasks.add(task);
                taskInputField.clear();
            } else {
                showAlert(Alert.AlertType.WARNING, "Input Error", "Task cannot be empty!");
            }
        });

        editButton.setOnAction(e -> {
            int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                String newTask = taskInputField.getText().trim();
                if (!newTask.isEmpty()) {
                    tasks.set(selectedIndex, newTask);
                    taskInputField.clear();
                } else {
                    showAlert(Alert.AlertType.WARNING, "Input Error", "Task cannot be empty!");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Selection Error", "No task selected for editing!");
            }
        });

        deleteButton.setOnAction(e -> {
            int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                tasks.remove(selectedIndex);
            } else {
                showAlert(Alert.AlertType.WARNING, "Selection Error", "No task selected for deletion!");
            }
        });

     
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("To-Do List Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
