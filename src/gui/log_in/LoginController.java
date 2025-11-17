package gui.log_in;
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
    public void openMainPage() {
        try {
            // prvo uzmi trenutni Login Stage i zatvori ga
            Stage loginStage = (Stage) btnLogin2.getScene().getWindow();
            loginStage.close();

            // učitaj FXML glavne stranice
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainPage/main.fxml"));
            Parent root = loader.load();

            // kreiraj novi Stage za glavnu stranu
            Stage mainStage = new Stage();
            mainStage.setTitle("Main Page");
            mainStage.setScene(new Scene(root));
            mainStage.show();

            // zatvori sve preostale Stage-ove (npr. Welcome Stage)
            Stage.getWindows().stream()
                    .filter(window -> window instanceof Stage && window != mainStage)
                    .forEach(window -> ((Stage) window).close());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
