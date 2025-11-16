package gui.welcome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;     // za Scene
import javafx.stage.Stage;    // za Stage
import javafx.scene.control.Button;

public class WelcomeController {
  @FXML
    private Button btnLogin;
  @FXML
    private Button btnSignup;
  @FXML
    private void handleLoginButton() {
        try {
            // Blokiraj dugmad da se spreči više otvaranja
            btnLogin.setDisable(true);
            btnSignup.setDisable(true);
            // Učitaj drugi FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/log_in/Log-in.fxml"));
            Parent root = fxmlLoader.load();

            // Kreiraj novu scenu i prozor
            Stage stage = new Stage();
            stage.setTitle("Log-in");
            stage.setScene(new Scene(root));
            stage.show();
            // Kada se novi prozor zatvori, ponovo uključi dugmad
            stage.setOnHiding(event -> {
                btnLogin.setDisable(false);
                btnSignup.setDisable(false);
            });
        } catch (Exception e) {

        }
    }
    @FXML
    private void handleSignupButton(){
        try {
            // Blokiraj dugmad da se spreči više otvaranja
            btnLogin.setDisable(true);
            btnSignup.setDisable(true);
            // Učitaj drugi FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/sign_up/Sign-up.fxml"));
            Parent root = fxmlLoader.load();

            // Kreiraj novu scenu i prozor
            Stage stage = new Stage();
            stage.setTitle("Sign-up");
            stage.setScene(new Scene(root));
            stage.show();
            // Kada se novi prozor zatvori, ponovo uključi dugmad
            stage.setOnHiding(event -> {
                btnLogin.setDisable(false);
                btnSignup.setDisable(false);
            });
        } catch (Exception e) {

        }
    }
}
