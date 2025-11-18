package gui.sign_up;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private Button btnSignup;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPassword2;
    @FXML
    private TextField textFieldPasswordConfirm;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label emailLabel2;
    @FXML
    private Label passwordLabel2;
    @FXML
    private Label passwordConfirmLabel2;

    @FXML
    public void openMainPage() {
        try {
            // prvo uzmi trenutni Login Stage i zatvori ga
            Stage Stage = (Stage) btnSignup.getScene().getWindow();
            Stage.close();

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
