package FinanceApplication;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class BudgetController {

    @FXML
    private Label totalIncomeLabel;
    @FXML
    private Label totalExpensesLabel;
    @FXML
    private Label netBalanceLabel;

    @FXML
    private TextField incomeSourceField;
    @FXML
    private TextField incomeAmountField;
    @FXML
    private TextField expenseCategoryField;
    @FXML
    private TextField expenseAmountField;

    @FXML
    private ListView<String> incomeList;
    @FXML
    private ListView<String> expenseList;

    private Budget budget;

    public void setBudget(Budget budget) {
        this.budget = budget;
        updateDashboard();
    }

    private void updateDashboard() {
        // Update total labels
        totalIncomeLabel.setText("$" + String.format("%.2f", budget.getTotalIncome()));
        totalExpensesLabel.setText("$" + String.format("%.2f", budget.getTotalExpenses()));
        netBalanceLabel.setText("$" + String.format("%.2f", budget.getNetBalance()));

        // Update ListViews with data from ObservableLists
        incomeList.setItems(FXCollections.observableArrayList(budget.getIncomeHistory()));
        expenseList.setItems(FXCollections.observableArrayList(budget.getExpenseHistory()));
    }

    @FXML
    public void handleAddIncome() {
        if (budget == null) {
            showAlert("Error", "Budget is not initialized.");
            return;
        }

        String source = incomeSourceField.getText();
        String amountText = incomeAmountField.getText();

        if (source.isEmpty() || amountText.isEmpty()) {
            showAlert("Error", "Please fill in both the income source and amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert("Error", "Amount must be a positive number.");
                return;
            }

            Income income = new Income(source, amount);
            budget.addIncome(income);

            incomeSourceField.clear();
            incomeAmountField.clear();
            updateDashboard();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for the amount.");
        }
    }

    @FXML
    public void handleAddExpense() {
        if (budget == null) {
            showAlert("Error", "Budget is not initialized.");
            return;
        }

        String category = expenseCategoryField.getText();
        String amountText = expenseAmountField.getText();

        if (category.isEmpty() || amountText.isEmpty()) {
            showAlert("Error", "Please fill in both the expense category and amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert("Error", "Amount must be a positive number.");
                return;
            }

            Expense expense = new Expense(category, amount);
            budget.addExpense(expense);

            expenseCategoryField.clear();
            expenseAmountField.clear();
            updateDashboard();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for the amount.");
        }
    }

    @FXML
    public void handleRefresh() {
        if (budget == null) {
            showAlert("Error", "Budget is not initialized.");
            return;
        }

        updateDashboard();
    }

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    public void handleSave() {
        if (budget == null) {
            showAlert("Error", "Budget is not initialized.");
            return;
        }

        try {
            budget.saveToFile("budget_data.ser");
            showAlert("Success", "Data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data.");
        }
    }

    @FXML
    public void handleLoad() {
        try {
            if (budget == null) {
                budget = new Budget(); // Initialize budget if null
            }
            budget.loadFromFile("budget_data.ser");
            updateDashboard();
            showAlert("Success", "Data loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load data.");
        }
    }

}
