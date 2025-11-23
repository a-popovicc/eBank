package validation;

import javafx.scene.control.Label;

public class SignupValidation {
    public static boolean validateSignup(
            String name,
            String surname,
            String email,
            String pass,
            String confirmPass,
            Label nameError,
            Label surnameError,
            Label emailError,
            Label passError,
            Label passConfirmError) {

        boolean ok = true;

        if (!TextFieldValidation.validateName(name)) {
            nameError.setText("Name must contain at least tree characters");
            nameError.setStyle("-fx-text-fill: red;");
            ok = false;
        }else{
            nameError.setText("Name");
            nameError.setStyle("-fx-text-fill: black");
        }

        if (!TextFieldValidation.validateName(surname)) {
            surnameError.setText("Name must contain at least tree characters");
            surnameError.setStyle("-fx-text-fill: red;");
            ok = false;
        }else{
            surnameError.setText("Surname");
            surnameError.setStyle("-fx-text-fill: black");
        }

        if (!TextFieldValidation.validateEmail(email)) {
            emailError.setText("Wrong email address");
            emailError.setStyle("-fx-text-fill: red;");
            ok = false;
        }else {
            emailError.setText("Email");
            emailError.setStyle("-fx-text-fill: black");
        }

        if (!TextFieldValidation.validatePassword(pass)) {
            passError.setText("Min 6 chars and at least one non-digit char");
            passError.setStyle("-fx-text-fill: red;");
            ok = false;
        }else {
            passError.setText("Password");
            passError.setStyle("-fx-text-fill: black");
        }
        if (!TextFieldValidation.confirmPassword(pass, confirmPass)) {
            passConfirmError.setText("Passwords don't match");
            passConfirmError.setStyle("-fx-text-fill: red;");
            ok = false;
        }else{
            passConfirmError.setText("Passwords confirm");
            passConfirmError.setStyle("-fx-text-fill: black");
        }

        return ok;
    }


}
