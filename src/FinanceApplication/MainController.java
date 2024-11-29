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
    private TextField incomeField; // Input field for income
    @FXML
    private TextField expenseField; // Input field for expenses
    @FXML
    private Label resultLabel; // Label to display the budget calculation result

    private List<Income> incomes = new ArrayList<>(); // List to store income records
    private List<Expense> expenses = new ArrayList<>(); // List to store expense records

    private static final String DATA_FILE = "userData.dat"; // File for persistent storage of data

    /**
     * Called automatically when the controller is loaded.
     * Loads saved data and updates the result label with the last calculated budget if available.
     */
    
    @FXML
    public void initialize() {
        loadData(); // Load saved data into incomes and expenses lists
        updateResultLabel(); // Update the result label based on loaded data
    }

    /**
     * Handles the calculation of the remaining budget based on user input.
     * Validates the input, updates the result label, and saves the data.
     */
    @FXML
    public void handleCalculateBudget() {
        try {
            // Parse user input
            double income = Double.parseDouble(incomeField.getText().trim());
            double expense = Double.parseDouble(expenseField.getText().trim());

            // Calculate remaining budget
            double remainingBudget = income - expense;
            resultLabel.setText(String.format("$%.2f", remainingBudget));

            // Save data
            incomes.add(new Income("User Income", income));
            expenses.add(new Expense("User Expense", expense));
            saveData();

        } catch (NumberFormatException e) {
            showError("Please enter valid numeric values for income and expenses.");
        }
    }

    /**
     * Clears the input fields and resets the result label.
     */
    @FXML
    public void handleClearFields() {
        incomeField.clear();
        expenseField.clear();
        resultLabel.setText("-");
    }

    /**
     * Saves the incomes and expenses data to a file for persistent storage.
     */
    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Income income : incomes) {
                writer.write("INCOME," + income.getSource() + "," + income.getAmount());
                writer.newLine();
            }
            for (Expense expense : expenses) {
                writer.write("EXPENSE," + expense.getSource() + "," + expense.getAmount());
                writer.newLine();
            }
            System.out.println("Data saved: " + incomes.size() + " incomes and " + expenses.size() + " expenses.");
        } catch (IOException e) {
            showError("Failed to save user data.");
            e.printStackTrace();
        }
    }

    private void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                incomes.clear();
                expenses.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String type = parts[0];
                        String source = parts[1];
                        double amount = Double.parseDouble(parts[2]);
                        if ("INCOME".equals(type)) {
                            incomes.add(new Income(source, amount));
                        } else if ("EXPENSE".equals(type)) {
                            expenses.add(new Expense(source, amount));
                        }
                    }
                }
                System.out.println("Data loaded: " + incomes.size() + " incomes and " + expenses.size() + " expenses.");
            } catch (IOException | NumberFormatException e) {
                showError("Failed to load user data.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Data file not found, starting with empty data.");
        }
    }


    /**
     * Updates the result label to show the last calculated budget if data is available.
     */
    private void updateResultLabel() {
        if (!incomes.isEmpty() && !expenses.isEmpty()) {
            double lastIncome = incomes.get(incomes.size() - 1).getAmount();
            double lastExpense = expenses.get(expenses.size() - 1).getAmount();
            resultLabel.setText(String.format("$%.2f", lastIncome - lastExpense));
        } else {
            resultLabel.setText("-");
        }
    }

    /**
     * Loads a new FXML view into the main stage.
     *
     * @param fxmlFile The FXML file path for the view.
     * @param title    The title for the new view.
     */
    private void loadView(String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage primaryStage = (Stage) incomeField.getScene().getWindow(); // Get the main stage
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(title);
        } catch (IOException e) {
            showError("Unable to load " + title + " view.");
            e.printStackTrace(); // Print the stack trace for debugging
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
    
    @FXML
    public void goToSettings() {
    	loadView("Settings.fxml", "Settings");
    }
    
 
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
}
