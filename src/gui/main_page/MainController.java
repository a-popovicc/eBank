package gui.main_page;

import functionality.appController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import user.Account;
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
    appController app = appController.getInstance();


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

        User active = UserSession.getActiveUser();

        // App kontroler odradi sve poslove oko domen logike
        Account newAccount = app.createNewAccount(active);

        if (newAccount == null) {
            System.out.println("Error: Account creation failed.");
            return;
        }

        // GUI samo prikazuje rezultat
        Parent card = NavigationController.loadEmptyAccountCard(newAccount);

        accountContainer.getChildren().add(card);
    }



}
