package gui.sign_up;

import functionality.appController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import user.User;
import user.UserSession;
import validation.SignupValidation;

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


    appController app = appController.getInstance();


    @FXML
    private void handleBtnSignup2() {
        boolean valid = app.validateSignup(
                textFieldName.getText(),
                textFieldSurname.getText(),
                textFieldEmail.getText(),
                textFieldPassword2.getText(),
                textFieldPasswordConfirm.getText(),
                nameLabel,
                surnameLabel,
                emailLabel2,
                passwordLabel2,
                passwordConfirmLabel2);

        if (!valid) {
            return; // prekini
        }
        User loggedUser = app.signup(textFieldName.getText(), textFieldSurname.getText(), textFieldEmail.getText(), textFieldPassword2.getText());

        if (loggedUser != null) {

            UserSession.setActiveUser(loggedUser);
            NavigationController.openNewPageAndClosePrevious(btnSignup2,"/gui/main_page/main.fxml","Main Page");

        }else{
            emailLabel2.setText("Error: User with this e-mail already exists");
            emailLabel2.setStyle("-fx-text-fill: red;");
        }
    }


}
