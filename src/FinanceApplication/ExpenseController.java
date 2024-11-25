package FinanceApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExpenseController {

    @FXML
    private TextField sourceField;
    @FXML
    private TextField amountField;
    @FXML
    private ListView<String> expenseList;

    private Budget budget;

    public ExpenseController() {
        this.budget = new Budget();
    }

    @FXML
    private void initialize() {
        // Bind expenseList to the ObservableList from budget.getExpenseHistory()
        expenseList.setItems(budget.getExpenseHistory());
    }

    @FXML
    public void handleAddExpense() {
        String source = sourceField.getText().trim();
        String amountText = amountField.getText().trim();

        if (source.isEmpty()) {
            showAlert("Input Error", "Please enter a source for the expense.");
            return;
        }

        if (amountText.isEmpty()) {
            showAlert("Input Error", "Please enter an amount for the expense.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            Expense expense = new Expense(source, amount);
            budget.addExpense(expense);

            sourceField.clear();
            amountField.clear();
            updateBudgetSummary();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Amount must be a valid number.");
        }
    }

    private void updateBudgetSummary() {
        System.out.println("Total Expenses: $" + budget.getTotalExpenses());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to handle the Back button
    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent mainMenuRoot = loader.load();
            Scene mainMenuScene = new Scene(mainMenuRoot);

            // Get the current stage and set the main menu scene
            Stage primaryStage = (Stage) expenseList.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu"); // Optional: set the title to "Main Menu"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
