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
 * Controller class for managing expenses.
 */
public class ExpenseController {

    @FXML
    private TextField sourceField; // Input field for the expense source
    @FXML
    private TextField amountField; // Input field for the expense amount
    @FXML
    private ListView<String> expenseList; // List view to display expense history

    private Budget budget; // Reference to the budget object

    /**
     * Constructor initializes the budget object.
     */
    public ExpenseController() {
        this.budget = new Budget(); // Initialize the budget object
    }

    /**
     * Called automatically when the controller is loaded.
     * Binds the expense list view to the budget's observable expense history.
     */
    @FXML
    private void initialize() {
        expenseList.setItems(budget.getExpenseHistory()); // Bind list view to expense history
    }

    /**
     * Handles adding a new expense entry.
     */
    @FXML
    public void handleAddExpense() {
        // Retrieve input values
        String source = sourceField.getText().trim();
        String amountText = amountField.getText().trim();

        // Validate the source input
        if (source.isEmpty()) {
            showAlert("Input Error", "Please enter a source for the expense.");
            return;
        }

        // Validate the amount input
        if (amountText.isEmpty()) {
            showAlert("Input Error", "Please enter an amount for the expense.");
            return;
        }

        try {
            // Parse the amount and create a new Expense object
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert("Input Error", "Amount must be a positive number.");
                return;
            }

            Expense expense = new Expense(source, amount);
            budget.addExpense(expense); // Add expense to the budget

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
        System.out.println("Total Expenses: $" + budget.getTotalExpenses());
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
            Stage primaryStage = (Stage) expenseList.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu"); // Optional: Set the title for the main menu
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Unable to navigate back to the main menu.");
        }
    }
}
