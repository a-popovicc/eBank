package validation;

import functionality.appController;
import javafx.scene.control.Label;

public class TransferDataValidation {
    private static final appController app = appController.getInstance();

    public  static boolean validaTransferData(String accNumber, String amountStr, Label messageField){
        boolean ok = true;
        if(app.findAccount(accNumber)) {
            messageField.setText("Account does not exists");
            ok = false;
        }
        if(!TextFieldValidation.validateAmount(amountStr)) {
            messageField.setText("Enter the amount correctly");
            ok = false;
        }
        double amount=Double.parseDouble(amountStr);
        if (amount <= 0) {
            ok = false;
            messageField.setText("Enter the amount correctly");
        }
        return ok;
    }
}
