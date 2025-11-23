package user;

import java.util.List;
import java.util.Objects;

public class User {

    private String name;
    private String surname;
    private String email;
    private String password;
    List<Account> accounts;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public boolean equals1(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(email, user.email);
    }
    public boolean equals2(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

}
