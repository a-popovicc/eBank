package gui.transfer_payment;

import functionality.appController;
import gui.acc_card.AccCardController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import user.Account;
import user.AccountSession;
import user.User;
import user.UserSession;

public class TransferController {
    @FXML
    private Label balanceLabel;
    @FXML
    private Label userAccLabel;

    @FXML
    private TextField txtFieldAcc;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldAmount;
    @FXML
    private TextField txtFieldPurpose;

    @FXML
    private Label accLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label purposeLabel;

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnTransfer;

    @FXML
    private Label messageField;
    appController app = appController.getInstance();

    @FXML
    public void initialize(){
        User activeUser= UserSession.getActiveUser();
        Account active= AccountSession.getActiveAccount();
        if(activeUser!=null || active!=null){
            userAccLabel.setText(active.getAccountNumber());
            balanceLabel.setText(active.getBalanceNow()+" "+"RSD");
        }
    }
    @FXML
    public void handleCancel() {
        NavigationController.openNewPageAndClosePrevious2(btnCancel, "/gui/acc/acc.fxml", "Account");
    }
    @FXML
    public void handleTransfer() {
        Account activeAccount= AccountSession.getActiveAccount();
        boolean success= app.validateTransferData(txtFieldAcc.getText(),txtFieldAmount.getText(),messageField);
        if(success){
           if(app.transfer(messageField,activeAccount.getAccountNumber(),txtFieldAcc.getText(),txtFieldAmount.getText())) {
               txtFieldAcc.clear();
               txtFieldName.clear();
               txtFieldAmount.clear();
               txtFieldPurpose.clear();
           }
        }
    }
}
