package gui.welcome;

import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController {


  @FXML
    private Button btnLogin;
  @FXML
    private Button btnSignup;

    @FXML
    public void handleLoginButton(){
        btnLogin.setDisable(true);
        btnSignup.setDisable(true);

        NavigationController.openInNewWindow(
                "/gui/log_in/Log-in.fxml",
                "Log-in",
                () -> {
                    btnLogin.setDisable(false);
                    btnSignup.setDisable(false);
                }
        );
    }

    @FXML
    public void handleSignupButton(){
      btnLogin.setDisable(true);
      btnSignup.setDisable(true);

        NavigationController.openInNewWindow(
                "/gui/sign_up/Sign-up.fxml",
                "Sign-up",
                () -> {
                    btnLogin.setDisable(false);
                    btnSignup.setDisable(false);
                }
        );
    }

}
