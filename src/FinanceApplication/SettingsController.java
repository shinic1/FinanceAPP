package FinanceApplication;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private ComboBox<String> currencyComboBox;
    @FXML
    private RadioButton lightModeRadio;
    @FXML
    private RadioButton darkModeRadio;
    @FXML
    private CheckBox enableNotificationsCheckBox;

    private Settings settings;

    @FXML
    public void initialize() {
        settings = new Settings();
        loadSettings();

        // Populate the ComboBox with currency options
        currencyComboBox.setItems(FXCollections.observableArrayList("USD", "EUR", "GBP", "JPY", "AUD"));
    }

    /**
     * Loads the saved settings when the settings screen is opened.
     */
    private void loadSettings() {
        currencyComboBox.getSelectionModel().select(settings.getCurrency());
        if (settings.isLightMode()) {
            lightModeRadio.setSelected(true);
        } else {
            darkModeRadio.setSelected(true);
        }
        enableNotificationsCheckBox.setSelected(settings.isNotificationsEnabled());
    }

    /**
     * Handles the save action when the Save button is clicked.
     */
    @FXML
    public void handleSaveSettings() {
        // Save the selected settings
        settings.setCurrency(currencyComboBox.getSelectionModel().getSelectedItem());
        settings.setLightMode(lightModeRadio.isSelected());
        settings.setNotificationsEnabled(enableNotificationsCheckBox.isSelected());

        // Example: Save these settings to a configuration file or preferences storage
        System.out.println("Settings Saved:");
        System.out.println("Currency: " + settings.getCurrency());
        System.out.println("Theme Mode: " + (settings.isLightMode() ? "Light" : "Dark"));
        System.out.println("Notifications Enabled: " + settings.isNotificationsEnabled());

        // Close the settings window
        Stage stage = (Stage) currencyComboBox.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the cancel action when the Cancel button is clicked.
     */
    @FXML
    public void handleCancel() {
        // Close the settings window without saving
        Stage stage = (Stage) currencyComboBox.getScene().getWindow();
        stage.close();
    }
}
