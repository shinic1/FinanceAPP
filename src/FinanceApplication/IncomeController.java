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

public class IncomeController {

    @FXML
    private TextField sourceField;
    @FXML
    private TextField amountField;
    @FXML
    private ListView<String> incomeList;

    private Budget budget;

    public IncomeController() {
        this.budget = new Budget();
    }

    @FXML
    private void initialize() {
        // Bind incomeList to the ObservableList from budget.getIncomeHistory()
        incomeList.setItems(budget.getIncomeHistory());
    }

    @FXML
    public void handleAddIncome() {
        String source = sourceField.getText().trim();
        String amountText = amountField.getText().trim();

        if (source.isEmpty()) {
            showAlert("Input Error", "Please enter a source for the income.");
            return;
        }

        if (amountText.isEmpty()) {
            showAlert("Input Error", "Please enter an amount for the income.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            Income income = new Income(source, amount);
            budget.addIncome(income);

            sourceField.clear();
            amountField.clear();
            updateBudgetSummary();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Amount must be a valid number.");
        }
    }

    private void updateBudgetSummary() {
        System.out.println("Total Income: $" + budget.getTotalIncome());
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
            Stage primaryStage = (Stage) incomeList.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu"); // Optional: set the title to "Main Menu"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
