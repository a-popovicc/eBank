package gui.sign_up;

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

public class SignupController {

    @FXML
    private Button btnSignup2;
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
    private void handleBtnSignup2() {
        LogicController k=new LogicController();
        if(k.signup(textFieldName.getText(),textFieldSurname.getText(),textFieldEmail.getText(),textFieldPassword2.getText())){
            Stage loginStage = (Stage) btnSignup2.getScene().getWindow();

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

}
