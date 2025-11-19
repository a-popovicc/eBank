package functionality.logic_controller;

import database.DatabaseController;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class LogicController implements Logic{
    private List<User> users;

    public LogicController() {
        users = new ArrayList<>();
        users=DatabaseController.loadUsersFromJson("database/users.json", users);
    }

    @Override
    public boolean login(String email, String password) {
        //prvo provera unetih
        User user = new User(email, password);
        for(User u : users){
            if(u.equals(user)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signup(String name, String surname, String email, String password) {
        User newUser = new User(name, surname, email, password);

        // Proveri da li korisnik već postoji
        for (User u : users) {
            if (u.equals(newUser)) {
                return false; // korisnik već postoji
            }
        }

        // Dodaj novog korisnika u listu
        users.add(newUser);

        // Upisi **celu listu** u JSON, ne samo novog korisnika
        DatabaseController.writeObjectToJson(users, "database/users.json");

        return true;
    }

}
