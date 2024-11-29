package FinanceApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Budget implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Income> incomes; // List to store income data
    private final List<Expense> expenses; // List to store expense data

    // Transient fields for displaying in the UI
    private transient ObservableList<String> incomeHistory;
    private transient ObservableList<String> expenseHistory;

    public Budget() {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        initializeTransientFields();
    }

    // Initialize transient fields
    private void initializeTransientFields() {
        incomeHistory = FXCollections.observableArrayList();
        expenseHistory = FXCollections.observableArrayList();
    }

    // Add income and update history
    public void addIncome(Income income) {
        incomes.add(income);
        incomeHistory.add("Source: " + income.getSource() + ", Amount: $" + String.format("%.2f", income.getAmount()));
    }

    // Add expense and update history
    public void addExpense(Expense expense) {
        expenses.add(expense);
        expenseHistory.add("Category: " + expense.getSource() + ", Amount: $" + String.format("%.2f", expense.getAmount()));
    }

    // Calculate total income
    public double getTotalIncome() {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    // Calculate total expenses
    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    // Calculate net balance
    public double getNetBalance() {
        return getTotalIncome() - getTotalExpenses();
    }

    public ObservableList<String> getIncomeHistory() {
        return incomeHistory;
    }

    public ObservableList<String> getExpenseHistory() {
        return expenseHistory;
    }

    // Save data to a file
    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this); // Save the entire Budget object
        }
    }

    // Load data from a file
    public static Budget loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("The data file does not exist.");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Budget loadedBudget = (Budget) ois.readObject(); // Read the Budget object
            loadedBudget.initializeTransientFields(); // Rebuild transient fields
            loadedBudget.updateHistories();
            return loadedBudget;
        }
    }

    // Rebuild the transient fields after loading
    private void updateHistories() {
        incomeHistory.clear();
        expenseHistory.clear();
        incomes.forEach(income -> incomeHistory.add("Source: " + income.getSource() + ", Amount: $" + String.format("%.2f", income.getAmount())));
        expenses.forEach(expense -> expenseHistory.add("Category: " + expense.getSource() + ", Amount: $" + String.format("%.2f", expense.getAmount())));
    }
}
