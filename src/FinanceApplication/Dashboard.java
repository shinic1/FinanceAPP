package FinanceApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dashboard extends Application {

    /**
     * Starts the application by loading the Dashboard view.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = loader.load();

        // Get the controller instance and set up the budget
        DashboardController controller = loader.getController();
        controller.setBudget(new Budget()); // Initialize a new budget

        primaryStage.setTitle("Budget Dashboard");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * Main method to launch the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
