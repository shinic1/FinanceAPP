package FinanceApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private Label totalIncomeLabel;
    @FXML private Label totalExpensesLabel;
    @FXML private Label netBalanceLabel;
    @FXML private ListView<String> incomeListView;
    @FXML private ListView<String> expenseListView;
    @FXML private PieChart budgetPieChart;

    private Budget budget;

    @FXML
    public void initialize() {
        // Any initialization you need goes here, or it can be done after calling setBudget
    }

    /**
     * Sets the budget instance and updates the dashboard UI.
     *
     * @param budget The Budget object to set for this controller.
     */
    public void setBudget(Budget budget) {
        if (budget == null) {
            // Handle error or return early
            return;
        }
        this.budget = budget;
        updateDashboard();
    }

    private void updateDashboard() {
        if (budget == null) return;

        totalIncomeLabel.setText("$" + String.format("%.2f", budget.getTotalIncome()));
        totalExpensesLabel.setText("$" + String.format("%.2f", budget.getTotalExpenses()));
        netBalanceLabel.setText("$" + String.format("%.2f", budget.getNetBalance()));

        // Updating ListViews
        incomeListView.setItems(FXCollections.observableArrayList(budget.getIncomeHistory()));
        expenseListView.setItems(FXCollections.observableArrayList(budget.getExpenseHistory()));

        updatePieChart();
    }

    private void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        double totalIncome = budget.getTotalIncome();
        double totalExpenses = budget.getTotalExpenses();

        if (totalIncome > 0) {
            pieChartData.add(new PieChart.Data("Income", totalIncome));
        }
        if (totalExpenses > 0) {
            pieChartData.add(new PieChart.Data("Expenses", totalExpenses));
        }

        // If both income and expenses are zero, display a message or handle accordingly
        if (pieChartData.isEmpty()) {
            pieChartData.add(new PieChart.Data("No Data", 1)); // Show "No Data" if both income and expenses are 0
        }

        budgetPieChart.setData(pieChartData);
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
            Stage primaryStage = (Stage) budgetPieChart.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
