package FinanceApplication;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    private Budget budget = new Budget();

    public void setBudget(Budget budget) {
        this.budget = budget;
        updateDashboard();
    }

    private void updateDashboard() {
        if (budget == null) return;

        totalIncomeLabel.setText("$" + String.format("%.2f", budget.getTotalIncome()));
        totalExpensesLabel.setText("$" + String.format("%.2f", budget.getTotalExpenses()));
        netBalanceLabel.setText("$" + String.format("%.2f", budget.getNetBalance()));

        incomeList.setItems(FXCollections.observableArrayList(budget.getIncomeHistory()));
        expenseList.setItems(FXCollections.observableArrayList(budget.getExpenseHistory()));
    }

    @FXML
    public void handleAddIncome() {
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

            budget.addIncome(new Income(source, amount));
            incomeSourceField.clear();
            incomeAmountField.clear();
            updateDashboard();
            showAlert("Success", "Income added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for the amount.");
        }
    }

    @FXML
    public void handleAddExpense() {
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

            budget.addExpense(new Expense(category, amount));
            expenseCategoryField.clear();
            expenseAmountField.clear();
            updateDashboard();
            showAlert("Success", "Expense added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number for the amount.");
        }
    }

    @FXML
    public void handleRefresh() {
        updateDashboard();
    }

    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FinanceApplication/Main.fxml"));
            Parent mainMenuRoot = loader.load();
            Scene mainMenuScene = new Scene(mainMenuRoot);

            Stage primaryStage = (Stage) incomeList.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the main menu.");
        }
    }

    @FXML
    public void handleSave() {
        try {
            budget.saveToFile("budget_data.ser");
            showAlert("Success", "Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data.");
        }
    }

    @FXML
    public void handleLoad() {
        try {
            budget = Budget.loadFromFile("budget_data.ser");
            updateDashboard();
            showAlert("Success", "Data loaded successfully.");
        } catch (FileNotFoundException e) {
            showAlert("Error", "Data file not found.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load data.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
