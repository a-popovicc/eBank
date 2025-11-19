package gui.log_in;
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
    private TextField emailTextField;
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


    @FXML
    private void handleBtnLogin2() {
        Stage loginStage = (Stage) btnLogin2.getScene().getWindow();

        NavigationController.openInNewWindow(
                loginStage,
                "/gui/mainPage/main.fxml",
                "Main Page",
                () -> {
                    // ZATVORI SVE PRETHODNE STAGE-OVE (uključujući Welcome)
                    Stage.getWindows().stream()
                            .filter(window -> window instanceof Stage)
                            .forEach(window -> {
                                if (window.isShowing()) window.hide();
                            });
                }
        );
    }

}

