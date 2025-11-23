package gui.transfer_payment;

import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransferController {
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

    /**
     * Otvara prethodni Stage-main i zatvara trenutni
     *
     */
    @FXML
    public void handleCancel() {
        NavigationController.openNewPageAndClosePrevious2(btnCancel, "/gui/acc/acc.fxml", "Main Page");
    }
}
