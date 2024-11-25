package FinanceApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TextField incomeField; // TextField for user to input income
    @FXML
    private TextField expenseField; // TextField for user to input expenses
    @FXML
    private Label resultLabel; // Label to display the result of budget calculation

    private List<Income> incomes = new ArrayList<>(); // List to store Income objects
    private List<Expense> expenses = new ArrayList<>(); // List to store Expense objects

    private static final String DATA_FILE = "userData.dat"; // File for persistent storage

    // Method to handle budget calculation
    @FXML
    public void handleCalculateBudget() {
        try {
            double income = Double.parseDouble(incomeField.getText());
            double expense = Double.parseDouble(expenseField.getText());
            double remainingBudget = income - expense;

            resultLabel.setText(String.format("$%.2f", remainingBudget));

            Income newIncome = new Income("User Income", income);
            Expense newExpense = new Expense("User Expense", expense);
            incomes.add(newIncome);
            expenses.add(newExpense);

            saveData(); // Save updated data to the file

        } catch (NumberFormatException e) {
            showError("Please enter valid numeric values for income and expenses.");
        }
    }

    // Method to clear input fields
    @FXML
    public void handleClearFields() {
        incomeField.clear();
        expenseField.clear();
        resultLabel.setText("-");
    }

    // Load data from the file when the application starts
    @FXML
    public void initialize() {
        loadData(); // Load saved data into incomes and expenses lists
        updateResultLabel(); // Update the result label with the last calculated budget if available
    }

    // Save data to the file
    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(incomes);
            out.writeObject(expenses);
        } catch (IOException e) {
            showError("Failed to save user data.");
        }
    }

    // Load data from the file
    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                incomes = (List<Income>) in.readObject();
                expenses = (List<Expense>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                showError("Failed to load user data.");
            }
        }
    }

    // Update the result label with the last calculated budget
    private void updateResultLabel() {
        if (!incomes.isEmpty() && !expenses.isEmpty()) {
            double lastIncome = incomes.get(incomes.size() - 1).getAmount();
            double lastExpense = expenses.get(expenses.size() - 1).getAmount();
            resultLabel.setText(String.format("$%.2f", lastIncome - lastExpense));
        } else {
            resultLabel.setText("-");
        }
    }

    // Generic method to load different FXML views
    private void loadView(String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage primaryStage = (Stage) incomeField.getScene().getWindow(); // Reuse the main stage
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(title);
        } catch (IOException e) {
            showError("Unable to load " + title + " view.");
        }
    }

    // Navigation methods
    @FXML
    public void goToBudget() {
        loadView("Budget.fxml", "Manage Budget");
    }

    @FXML
    public void goToIncome() {
        loadView("Income.fxml", "Manage Income");
    }

    @FXML
    public void goToExpense() {
        loadView("Expense.fxml", "Manage Expenses");
    }

    @FXML
    public void goToDictionary() {
        loadView("Dictionary.fxml", "Financial Dictionary");
    }

    @FXML
    public void goToDashboard() {
        loadView("Dashboard.fxml", "Dashboard");
    }

    // Helper method to show error alerts
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
