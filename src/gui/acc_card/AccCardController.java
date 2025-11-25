package gui.acc_card;

import functionality.appController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import user.Account;
import user.User;
import user.UserSession;

public class AccCardController {
    @FXML
    private TextField textFieldAccName;
    @FXML
    private Button btnAccount;
    appController app = new appController();


    @FXML
    public void initialize() {
        textFieldAccName.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                // polje JE izgubilo fokus → znamo da je edit završen
                saveAccNameInstantly();
            }
        });

        // Ako hoće da se pamti i kad pritisne ENTER
        textFieldAccName.setOnAction(e -> saveAccNameInstantly());
    }
    private void saveAccNameInstantly() {

        User activeUser = UserSession.getActiveUser();

        boolean success= app.updateAccount(activeUser, btnAccount.getText() ,textFieldAccName.getText());
        if(success) {

        }

    }



    @FXML
    public void setAccountData(String accName, String accNumber) {
        textFieldAccName.setText(accName);
        btnAccount.setText(accNumber);
    }

    @FXML
    public void handleBtnAccount() {
        NavigationController.openNewPageAndClosePrevious2(btnAccount,"/gui/acc/acc.fxml","Account Page");
    }
}
