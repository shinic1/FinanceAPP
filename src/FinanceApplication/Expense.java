package FinanceApplication;

public class Expense {
  
    private String source;  // Source of the expense
    private double amount;  // Amount of the expense

    // Constructor to initialize expense with source and amount
    public Expense(String source, double amount) {
        this.source = source;
        this.amount = amount;
    }

    // Getters for source and amount
    public String getSource() {
        return source;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Source: " + source + ", Amount: $" + String.format("%.2f", amount);
    }
}
