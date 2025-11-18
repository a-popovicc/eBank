package gui.transfer_payment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        try {
            Stage Stage = (Stage) btnCancel.getScene().getWindow();
            Stage.close();

            // učitaj FXML glavne stranice
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainPage/main.fxml"));
            Parent root = loader.load();

            // kreiraj novi Stage za glavnu stranu
            Stage mainStage = new Stage();
            mainStage.setTitle("Main Page");
            mainStage.setScene(new Scene(root));
            mainStage.show();


        } catch (Exception e) {

        }
    }
}
