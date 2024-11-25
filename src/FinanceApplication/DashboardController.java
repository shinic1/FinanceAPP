package FinanceApplication;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class DashboardController {

    @FXML private Label totalIncomeLabel;
    @FXML private Label totalExpensesLabel;
    @FXML private Label netBalanceLabel;
    @FXML private ListView<String> incomeListView;
    @FXML private ListView<String> expenseListView;
    @FXML private PieChart budgetPieChart;

    private Budget budget = new Budget(); // Initialize a new Budget object
    @FXML
    private TextField incomeField;

    public void setBudget(Budget budget) {
        this.budget = budget;
        updateDashboard(); // Initial update with the provided budget data
    }

    public void updateDashboard() {
        if (budget == null) return;

        totalIncomeLabel.setText("$" + budget.getTotalIncome());
        totalExpensesLabel.setText("$" + budget.getTotalExpenses());
        netBalanceLabel.setText("$" + budget.getNetBalance());

        incomeListView.setItems(FXCollections.observableArrayList(budget.getIncomeHistory()));
        expenseListView.setItems(FXCollections.observableArrayList(budget.getExpenseHistory()));

        updatePieChart();
    }

    private void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        double totalIncome = budget.getTotalIncome();
        double totalExpenses = budget.getTotalExpenses();
        if (totalIncome > 0) pieChartData.add(new PieChart.Data("Income", totalIncome));
        if (totalExpenses > 0) pieChartData.add(new PieChart.Data("Expenses", totalExpenses));
        budgetPieChart.setData(pieChartData);
    }
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
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleRefresh() {
        updateDashboard();
    }
    @FXML
    public void goToDictionary() {
        loadView("Dictionary.fxml", "Financial Dictionary");
    }


    @FXML
    public void handleBack() {
        try {
            // Load the main menu scene (MainMenu.fxml) when the back button is clicked
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent mainMenuRoot = loader.load();
            Scene mainMenuScene = new Scene(mainMenuRoot);

            // Set the main menu scene on the primary stage
            Stage primaryStage = (Stage) budgetPieChart.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu"); // Optional: set the title to "Main Menu"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
