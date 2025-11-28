package gui.log_in;
import functionality.appController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import user.User;
import user.UserSession;

public class LoginController {

    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordFieldEnc;
    @FXML
    private Label emailLabel;
    @FXML
    private Button btnViewPassword;
    @FXML
    private Button btnLogin2;


    private boolean passwordVisible = false;

    @FXML
    public void initialize() {

        textFieldPassword.textProperty().bindBidirectional(passwordFieldEnc.textProperty());
        btnViewPassword.setOnAction(event -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        if (passwordVisible) {
            textFieldPassword.setVisible(false);
            textFieldPassword.setManaged(false);

            passwordFieldEnc.setVisible(true);
            passwordFieldEnc.setManaged(true);

            passwordVisible = false;
        } else {
            textFieldPassword.setVisible(true);
            textFieldPassword.setManaged(true);

            passwordFieldEnc.setVisible(false);
            passwordFieldEnc.setManaged(false);

            passwordVisible = true;
        }
    }

    appController app = appController.getInstance();


    @FXML
    private void handleBtnLogin2() {
        try {
            User loggedUser = app.login(textFieldEmail.getText(), textFieldPassword.getText());
            if (loggedUser != null) {

                UserSession.setActiveUser(loggedUser);
                NavigationController.openNewPageAndClosePrevious(btnLogin2, "/gui/main_page/main.fxml", "Main Page");

            } else {
                emailLabel.setStyle("-fx-text-fill: red");
                passwordLabel.setStyle("-fx-text-fill: red");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

