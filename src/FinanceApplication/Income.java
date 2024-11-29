package FinanceApplication;
public class Income {
    private String source; // The source of the income (e.g., salary, allowance)
    private double amount; // The amount of income received

    /*
     Constructor to initialize an Income object with a source and amount.
     @param source the source of the income
     @param amount the amount of income received
     */
    public Income(String source, double amount) {
        this.source = source; // Assign the source to the instance variable
        this.amount = amount; // Assign the amount to the instance variable
    }
   
   
    public String getSource() {
        return source; // Returns the source of the income
    }

 
    public double getAmount() {
        return amount; // Returns the amount of income
    }
    
    @Override
    public String toString() {
        return "Source: " + source + ", Amount: $" + String.format("%.2f", amount);
    }
}
