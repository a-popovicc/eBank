package functionality;

import database.DatabaseController;
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
        DatabaseController.writeObjectToJson(users, "database/users.json");

        return newUser;
    }

}
