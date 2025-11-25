package functionality;

import database.DatabaseController;
import user.Account;
import user.User;
import validation.SignupValidation;
import validation.TextFieldValidation;

import java.util.ArrayList;
import java.util.List;

public class appController implements app {

    private List<User> users;

    public appController() {
        users = new ArrayList<>();
        users=DatabaseController.loadUsersFromJson("database/users.json", users);
    }

    public User login(String email, String password) {

        User temp = new User(email, password);

        for (User u : users) {
            if (u.equals2(temp)) {
                return u; // vracamo pravog korisnika iz liste
            }
        }
        return null;
    }


    @Override
    public User signup(String name, String surname, String email, String password) {

        User newUser = new User(TextFieldValidation.turnFirstLetterToUpperCase(name),
                TextFieldValidation.turnFirstLetterToUpperCase(surname), email, password);

        for (User u : users) {
            if (u.equals1(newUser)) {
                return null;
            }
        }

        users.add(newUser);
        if(!DatabaseController.writeObjectToJson(users, "database/users.json")){
            return null;
        }
        return newUser;
    }

    @Override
    public boolean saveNewAccount(User activeUser) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals1(activeUser)) {
                users.set(i, activeUser);
                break;
            }
        }
        // upiši nazad u JSON
        return DatabaseController.writeObjectToJson(users, "database/users.json");
    }

    public void syncUser(User activeUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals1(activeUser)) {
                users.set(i, activeUser);
                return;
            }
        }
    }

    @Override
    public boolean updateAccount(User activeUser, String accountNumber, String newName) {
        syncUser(activeUser);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals2(activeUser)) {

                // pronađi account za promenu
                for (Account acc : users.get(i).getAccounts()) {
                    if (acc.getAccountNumber().equals(accountNumber)) {
                        acc.setName(newName);
                        break;
                    }
                }
                break;
            }
        }
        return DatabaseController.writeObjectToJson(users, "database/users.json");
    }



}
