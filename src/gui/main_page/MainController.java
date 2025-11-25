package gui.main_page;

import functionality.appController;
import gui.acc_card.AccCardController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import user.Account;
import user.User;
import user.UserSession;

import java.util.ArrayList;


public class MainController {

    @FXML
    private VBox accountContainer;
    @FXML
    private Label emailLabel;
    @FXML
    private Label UserNameLabel;
    @FXML
    private Button btnCreateNew;
    appController app = new appController();


    @FXML
    public void initialize() {
        User active = UserSession.getActiveUser();

        if (active != null) {
            UserNameLabel.setText(active.getName() + " " + active.getSurname());
            emailLabel.setText(active.getEmail());
            if(active.getAccounts()!=null){
                for (Account acc : active.getAccounts()) {
                    Parent card = NavigationController.loadAccountCard(acc.getName(), acc.getAccountNumber());
                    accountContainer.getChildren().add(card);
                }

            }
        }
    }


    @FXML
    private void handleBtnCreateNew() {

        Account newAccount = new Account();
        newAccount.setName("New Account");

        // 1) Kreiraj GUI karticu
        Parent card = NavigationController.loadEmptyAccountCard(newAccount);
        accountContainer.getChildren().add(card);

        User active = UserSession.getActiveUser();
        if (active.getAccounts() == null) {
            active.setAccounts(new ArrayList<>());
        }
        active.getAccounts().add(newAccount);

        // 4) Sacuvaj izmene preko AppController instance
        boolean saved = app.saveNewAccount(active);

        if (!saved) {
            System.out.println("Error: Account NOT saved!");
        }
    }


}
