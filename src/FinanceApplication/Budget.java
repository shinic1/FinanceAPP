package FinanceApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Budget {

    private List<Income> incomes;
    private List<Expense> expenses;
    private ObservableList<String> incomeHistory;
    private ObservableList<String> expenseHistory;

    public Budget() {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        incomeHistory = FXCollections.observableArrayList();
        expenseHistory = FXCollections.observableArrayList();
    }

    public void addIncome(Income income) {
        incomes.add(income);
        // Add formatted income entry to incomeHistory
        incomeHistory.add("Source: " + income.getSource() + ", Amount: $" + String.format("%.2f", income.getAmount()));
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        // Add formatted expense entry to expenseHistory
        expenseHistory.add("Category: " + expense.getSource() + ", Amount: $" + String.format("%.2f", expense.getAmount()));
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalIncome() {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getNetBalance() {
        return getTotalIncome() - getTotalExpenses();
    }

    // Getters for ObservableLists for formatted income and expense history
    public ObservableList<String> getIncomeHistory() {
        return incomeHistory;
    }

    public ObservableList<String> getExpenseHistory() {
        return expenseHistory;
    }
    
 // Save budget data to a file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(incomeHistory);
            oos.writeObject(expenseHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load budget data from a file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            incomeHistory = (ObservableList<String>) ois.readObject();
            expenseHistory = (ObservableList<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
