package FinanceApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for managing income.
 */
public class IncomeController {

    @FXML
    private TextField sourceField; // Input field for the income source
    @FXML
    private TextField amountField; // Input field for the income amount
    @FXML
    private ListView<String> incomeList; // List view to display income history

    private Budget budget; // Reference to the budget object

    /**
     * Constructor initializes the budget object.
     */
    public IncomeController() {
        this.budget = new Budget(); // Ensure the budget object is initialized
    }

    /**
     * Called automatically when the controller is loaded.
     * Binds the income list view to the budget's observable income history.
     */
    @FXML
    private void initialize() {
        incomeList.setItems(budget.getIncomeHistory()); // Bind list view to income history
    }

    /**
     * Handles adding a new income entry.
     */
    @FXML
    public void handleAddIncome() {
        // Retrieve input values
        String source = sourceField.getText().trim();
        String amountText = amountField.getText().trim();

        // Validate the source input
        if (source.isEmpty()) {
            showAlert("Input Error", "Please enter a source for the income.");
            return;
        }

        // Validate the amount input
        if (amountText.isEmpty()) {
            showAlert("Input Error", "Please enter an amount for the income.");
            return;
        }

        try {
            // Parse the amount and create a new Income object
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert("Input Error", "Amount must be a positive number.");
                return;
            }

            Income income = new Income(source, amount);
            budget.addIncome(income); // Add income to the budget

            // Clear input fields and update the UI
            sourceField.clear();
            amountField.clear();
            updateBudgetSummary();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Amount must be a valid number.");
        }
    }

    /**
     * Updates the budget summary in the console (placeholder for future UI updates).
     */
    private void updateBudgetSummary() {
        System.out.println("Total Income: $" + budget.getTotalIncome());
    }

    /**
     * Displays an alert with a given title and message.
     *
     * @param title   The title of the alert dialog.
     * @param message The message to display.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header for simplicity
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Handles the "Back" button action to return to the main menu.
     */
    @FXML
    public void handleBack() {
        try {
            // Load the Main.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent mainMenuRoot = loader.load();

            // Set the new scene on the current stage
            Scene mainMenuScene = new Scene(mainMenuRoot);
            Stage primaryStage = (Stage) incomeList.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu"); // Optional: Set the title for the main menu
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Unable to navigate back to the main menu.");
        }
    }
}
