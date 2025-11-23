package functionality;

import user.User;

public interface app {
    /**
     * Works on the first check with the login GUI,
     * verifies whether the user exists in the database and whether the entered data is correct.
     *
     * @param email
     * @param password
     * @return true if there is this user with this data in database or false
     * if there is not
     */
    public User login(String email, String password);

    /**
     * Works on the first check with the signup GUI,
     * verifies whether the user exists in the database and whether the entered data is correct.
     * If entered data is correct new obj User is created in database
     *
     * @param name
     * @param surname
     * @param email
     * @param password
     * @return false if the User already exists in database or true
     * if the User is just created
     */
    public User signup(String name, String surname, String email, String password);


}
