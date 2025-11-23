package gui.acc_card;

import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AccCardController {

    @FXML
    private Label accNameLabel;
    @FXML
    private Button btnAccount;

    @FXML
    public void handleBtnAccount() {
        NavigationController.openNewPageAndClosePrevious2(btnAccount,"/gui/acc/acc.fxml","Account Page");
    }
}
