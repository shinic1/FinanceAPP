package FinanceApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DictionaryController {

    @FXML
    private Label definitionLabel;

    private FinancialDictionary dictionary = new FinancialDictionary();

    @FXML
    public void showBudgetDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Budget"));
    }

    @FXML
    public void showIncomeDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Income"));
    }

    @FXML
    public void showExpenseDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Expense"));
    }

    @FXML
    public void showSavingsDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Savings"));
    }

    @FXML
    public void showInvestmentDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Investment"));
    }

    @FXML
    public void showDebtDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Debt"));
    }
    
    @FXML
    public void showInterestDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Interest"));
    }
    
    @FXML
    public void showPrincipalDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Principal"));
    }
    
    
    //
    
    @FXML
    public void showAssetDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Asset"));
    }
    
    @FXML
    public void showLiabilityDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Liability"));
    }
    
    @FXML
    public void showEquityDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Equity"));
    }
    
    @FXML
    public void showStockDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Stock"));
    }
    
    @FXML
    public void showBondDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Bond"));
    }
    @FXML
    public void showMutualFundDefinition() {
        definitionLabel.setText(dictionary.getDefinition("MutualFund"));
    }
    @FXML
    public void showEftDefinition() {
        definitionLabel.setText(dictionary.getDefinition("EFT"));
    }
    @FXML
    public void showPortfolioDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Portfolio"));
    }
    
    @FXML
    public void showDividendDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Dividend"));
    }
    
    //
    @FXML
    public void showCheckingDefinition() {
        definitionLabel.setText(dictionary.getDefinition("CheckingAccount"));
    }
    
    @FXML
    public void showSavingDefinition() {
        definitionLabel.setText(dictionary.getDefinition("SavingsAccount"));
    }
    
    @FXML
    public void showCertificateDefinition() {
        definitionLabel.setText(dictionary.getDefinition("CertificateofDeposit"));
    }
    
    @FXML
    public void showOverdraftDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Overdraft"));
    }
    
    @FXML
    public void showAprDefinition() {
        definitionLabel.setText(dictionary.getDefinition("APR"));
    }
    @FXML
    public void showApyDefinition() {
        definitionLabel.setText(dictionary.getDefinition("APY"));
    }
    
    //
    @FXML
    public void showCreditScoreDefinition() {
        definitionLabel.setText(dictionary.getDefinition("CreditScore"));
    }
    
    //
    @FXML
    public void showCreditReportDefinition() {
        definitionLabel.setText(dictionary.getDefinition("CreditReport"));
    }
    
    @FXML
    public void showMortgageDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Mortgage"));
    }
    
    @FXML
    public void showRefinancingDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Refinancing"));
    }
    
    @FXML
    public void showCollateralDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Collateral"));
    }
    
    //
    @FXML
    public void showTaxableDefinition() {
        definitionLabel.setText(dictionary.getDefinition("TaxableIncome"));
    }
    
    @FXML
    public void showDeductionDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Deduction"));
    }
    
    @FXML
    public void showTaxCreditDefinition() {
        definitionLabel.setText(dictionary.getDefinition("TaxCredit"));
    }
    
    @FXML
    public void showCapitalGainDefinition() {
        definitionLabel.setText(dictionary.getDefinition("CapitalGainsTax"));
    }
    
    @FXML
    public void showEstateDefinition() {
        definitionLabel.setText(dictionary.getDefinition("EstateTax"));
    }
    
    //
    @FXML
    public void show401Definition() {
        definitionLabel.setText(dictionary.getDefinition("401(k)"));
    }
    
    @FXML
    public void showIraDefinition() {
        definitionLabel.setText(dictionary.getDefinition("IRA"));
    }
    
    @FXML
    public void showRothiraDefinition() {
        definitionLabel.setText(dictionary.getDefinition("RothIRA"));
    }
    
    @FXML
    public void showPensionDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Pension"));
    }
    
    //
    @FXML
    public void showInflationDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Inflation"));
    }
    
    @FXML
    public void showDeflationDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Deflation"));
    }
    
    @FXML
    public void showRecessionDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Recession"));
    }
    
    @FXML
    public void showGdpDefinition() {
        definitionLabel.setText(dictionary.getDefinition("GDP"));
    }
    
    //
    @FXML
    public void showPremiumDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Premium"));
    }
    
    @FXML
    public void showDeductibleDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Deductible"));
    }
    
    @FXML
    public void showCoverageDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Coverage"));
    }
    
    @FXML
    public void showClaimDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Claim"));
    }
    
    //
    @FXML
    public void showHedgeDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Hedge"));
    }
    
    @FXML
    public void showLeverageDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Leverage"));
    }
    
    @FXML
    public void showLiquidityDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Liquidity"));
    }
    
    @FXML
    public void showYeildDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Yield"));
    }
    
    @FXML
    public void showCapExDefinition() {
        definitionLabel.setText(dictionary.getDefinition("CapitalExpenditure"));
    }
    
    @FXML
    public void showDepreciationDefinition() {
        definitionLabel.setText(dictionary.getDefinition("Depreciation"));
    }
    
    
    

    // Method to handle the Back button
    @FXML
    public void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent mainMenuRoot = loader.load();
            Scene mainMenuScene = new Scene(mainMenuRoot);

            // Get the current stage and set the main menu scene
            Stage primaryStage = (Stage) definitionLabel.getScene().getWindow();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu"); // Optional: set the title to "Main Menu"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
