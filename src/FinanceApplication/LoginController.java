package FinanceApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField usernameField; // Input field for username
    @FXML
    private PasswordField passwordField; // Input field for password

    private static final String USER_FILE = "users.txt"; // File to store user credentials
    private Map<String, String> users = new HashMap<>(); // Map to store username-password pairs

    /**
     * Called automatically when the controller is loaded.
     * Loads user credentials from the file into memory.
     */
    @FXML
    public void initialize() {
        loadUsers();
    }

    /**
     * Handles login button action.
     * Validates credentials and navigates to the main application view if successful.
     */
    @FXML
    public void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Username and password cannot be empty.");
            return;
        }

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loadMainView();
        } else {
            showError("Invalid username or password.");
        }
    }

    /**
     * Handles registration button action.
     * Registers a new user and saves the credentials to the file.
     */
    @FXML
    public void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Username and password cannot be empty.");
            return;
        }

        if (users.containsKey(username)) {
            showError("Username already exists. Please choose a different username.");
            return;
        }

        users.put(username, password);
        saveUsers();
        showInfo("User registered successfully!");
    }

    /**
     * Loads user credentials from the file into the map.
     */
    private void loadUsers() {
        File file = new File(USER_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        users.put(parts[0], parts[1]);
                    }
                }
            } catch (IOException e) {
                showError("Failed to load user data.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves user credentials to the file.
     */
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            showError("Failed to save user data.");
            e.printStackTrace();
        }
    }

    /**
     * Navigates to the main application view.
     */
    private void loadMainView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow(); // Get the current stage
            stage.setScene(new Scene(root));
            stage.setTitle("Finance Application");
        } catch (IOException e) {
            showError("Unable to load main application view.");
            e.printStackTrace();
        }
    }

    /**
     * Displays an error message in a pop-up alert.
     *
     * @param message The error message to display.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays an informational message in a pop-up alert.
     *
     * @param message The informational message to display.
     */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
