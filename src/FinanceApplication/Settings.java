package FinanceApplication;

public class Settings {
    private static Settings instance;
    
    private String currency;
    private boolean isLightMode;
    private boolean notificationsEnabled;

    // Private constructor to prevent instantiation
    private Settings() {
        // Default values
        this.currency = "USD";
        this.isLightMode = true;
        this.notificationsEnabled = true;
    }

    // Get the single instance of the Settings
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    // Getters and setters for the settings
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isLightMode() {
        return isLightMode;
    }

    public void setLightMode(boolean isLightMode) {
        this.isLightMode = isLightMode;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }
}
