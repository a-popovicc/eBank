package gui.log_in;
import functionality.logic_controller.LogicController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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


    private boolean passwordVisible = false; // stanje lozinke

    @FXML
    public void initialize() {
        // sinhronizacija tekstova oba polja
        textFieldPassword.textProperty().bindBidirectional(passwordFieldEnc.textProperty());

        // dodaj akciju dugmetu
        btnViewPassword.setOnAction(event -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        if (passwordVisible) {
            // Sakrij lozinku: pokaži PasswordField
            textFieldPassword.setVisible(false);
            textFieldPassword.setManaged(false);

            passwordFieldEnc.setVisible(true);
            passwordFieldEnc.setManaged(true);

            passwordVisible = false;
        } else {
            // Prikaži lozinku: pokaži TextField
            textFieldPassword.setVisible(true);
            textFieldPassword.setManaged(true);

            passwordFieldEnc.setVisible(false);
            passwordFieldEnc.setManaged(false);

            passwordVisible = true;
        }
    }

    LogicController k = new LogicController();

    @FXML
    private void handleBtnLogin2() {
        if (k.login(textFieldEmail.getText(), textFieldPassword.getText())) {
            NavigationController.openNewPageAndClosePrevious(btnLogin2,"/gui/mainPage/main.fxml","Main Page");
        }
    }


}

