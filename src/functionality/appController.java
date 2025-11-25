package functionality;

import database.DatabaseController;
import user.Account;
import user.User;
import validation.SignupValidation;
import validation.TextFieldValidation;

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


    public User login(String email, String password) {

        User temp = new User(email, password);

        for (User u : users) {
            if (u.equals2(temp)) {
                return u;
            }
        }
        return null;
    }



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
    public Account createNewAccount(User activeUser) {

        if (activeUser == null) return null;

        Account acc = new Account();
        acc.setName("New Account");
        acc.setAccountNumber();

        if (activeUser.getAccounts() == null) {
            activeUser.setAccounts(new ArrayList<>());
        }

        activeUser.getAccounts().add(acc);

        boolean saved = saveNewAccount(activeUser);
        if (!saved) {
            System.out.println("ERROR: Account not saved!");
        }

        return acc;
    }


    public void syncUser(User activeUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals1(activeUser)) {
                users.set(i, activeUser);
                return;
            }
        }
    }


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
    public boolean findAccount(String accountNumber) {
        for(User u : users){
            if(u.getAccounts() != null) {
                for(Account acc : u.getAccounts()) {
                    if (acc.getAccountNumber().equals(accountNumber)) {
                     return false;
                    }
                }
            }
        }
        return true; //nema ga znaci moze
    }



}
