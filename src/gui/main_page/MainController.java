package gui.main_page;

import gui.acc_card.AccCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import user.User;
import user.UserSession;

public class MainController {

    @FXML
    private VBox accountContainer;
    @FXML
    private Label emailLabel;
    @FXML
    private Label UserNameLabel;
    @FXML
    private Button btnCreateNew;


    @FXML
    public void initialize() {
        User active = UserSession.getActiveUser();

        if (active != null) {
            UserNameLabel.setText(active.getName() + " " + active.getSurname());
            emailLabel.setText(active.getEmail());
        }
    }


    @FXML
    private void handleBtnCreateNew() {
        try {
            // putanja do kartice (promeni ako je u drugom folderu)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/acc_card/AccCard.fxml"));

            Parent card = loader.load();

            // Ako kartica ima svoj kontroler, ovde ga možeš dohvatiti:
            //AccCardController controller = loader.getController();
           // controller.setOnOpen(() -> openNextGUI());

            accountContainer.getChildren().add(card);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
