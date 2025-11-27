package functionality;

import database.DatabaseController;
import javafx.scene.control.Label;
import user.Account;
import user.AccountSession;
import user.User;
import validation.SignupValidation;
import validation.TextFieldValidation;
import validation.TransferDataValidation;

import java.util.ArrayList;
import java.util.List;

public class appController {

    private static appController instance;

    private List<User> users;

    private appController() {
        users = DatabaseController.loadUsersFromJson("database/users.json", new ArrayList<>());
    }

    public static appController getInstance() {
        if (instance == null) {
            instance = new appController();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    // -------------------------
    // LOGIN & SIGNUP OSTAVLJENI
    // -------------------------
    public User login(String email, String password) {
        User temp = new User(email, password);
        for (User u : users) {
            if (u.equals2(temp)) return u;
        }
        return null;
    }

    public User signup(String name, String surname, String email, String password) {
        User newUser = new User(
                TextFieldValidation.turnFirstLetterToUpperCase(name),
                TextFieldValidation.turnFirstLetterToUpperCase(surname),
                email,
                password
        );

        for (User u : users) {
            if (u.equals1(newUser)) return null;
        }

        users.add(newUser);
        if(!DatabaseController.writeObjectToJson(users, "database/users.json")) {
            return null;
        }
        return newUser;
    }

    // -------------------------
    // NOVA LOGIKA — NEMA PETLJI
    // -------------------------

    private boolean saveUsers() {
        return DatabaseController.writeObjectToJson(users, "database/users.json");
    }

    public boolean saveNewAccount(User activeUser) {
        // activeUser već jeste referenca iz users liste → dovoljno je samo saveUsers()
        return saveUsers();
    }

    public Account createNewAccount(User activeUser) {
        if (activeUser == null) return null;

        Account acc = new Account();
        acc.setName("New Account");
        acc.setAccountNumber();

        if (activeUser.getAccounts() == null) {
            activeUser.setAccounts(new ArrayList<>());
        }

        activeUser.getAccounts().add(acc);

        if (!saveUsers()) {
            System.out.println("ERROR: Account not saved!");
        }

        return acc;
    }

    public boolean updateAccount(User activeUser, String accountNumber, String newName) {

        for (Account acc : activeUser.getAccounts()) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                acc.setName(newName);
                break;
            }
        }

        return saveUsers();
    }

    public boolean findAccount(String accountNumber) {
        for (User u : users) {
            if (u.getAccounts() != null) {
                for (Account acc : u.getAccounts()) {
                    if (acc.getAccountNumber().equals(accountNumber)) {
                        return false; // već postoji
                    }
                }
            }
        }
        return true;
    }

    public Account findAccount1(String accountNumber) {
        for (User u : users) {
            if (u.getAccounts() != null) {
                for (Account acc : u.getAccounts()) {
                    if (acc.getAccountNumber().equals(accountNumber)) {
                        return acc;
                    }
                }
            }
        }
        return null;
    }

    // Validacije ostaju iste
    public boolean validateSignup(String name,
                                  String surname,
                                  String email,
                                  String pass,
                                  String confirmPass,
                                  Label nameError,
                                  Label surnameError,
                                  Label emailError,
                                  Label passError,
                                  Label passConfirmError) {
        return SignupValidation.validateSignup(name, surname, email, pass, confirmPass,
                nameError, surnameError, emailError, passError, passConfirmError);
    }

    public boolean validateTransferData(String accNumber, String amount,Label message) {
        return TransferDataValidation.validaTransferData(accNumber, amount, message);
    }

    public boolean transfer(Label message, String fromAccountNumber, String toAccountNumber, String amountStr) {

        // 1) Pretvori amount
        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            return false;
        }

        // 2) Account sa kog se skida (iz sesije)
        Account from = AccountSession.getActiveAccount();
        if (from == null) return false;

        // Bezbednosna provera da je taj account stvarno od usera
        if (!from.getAccountNumber().equals(fromAccountNumber)) {
            return false;
        }

        // 3) Account na koji se prebacuje (traži se kroz sve korisnike)
        Account to = findAccount1(toAccountNumber);
        if (to == null) {
            return false; // ne postoji
        }

        // 4) Da li ima dovoljno novca?
        if (from.getBalanceNow() < amount) {
            message.setText("Insufficient funds!");
            return false;
        }

        // 5) Snimi prethodne vrednosti — da grafovi i istorija rade
        from.setBalanceBefore(from.getBalanceNow());
        to.setBalanceBefore(to.getBalanceNow());

        // 6) Izvrši transfer
        from.setBalanceNow(from.getBalanceNow() - amount);
        to.setBalanceNow(to.getBalanceNow() + amount);
        message.setText("Transfer successful!");
        message.setStyle("-fx-text-fill: green;");

        // 7) Zapiši u JSON
        return saveUsers();
    }

}

