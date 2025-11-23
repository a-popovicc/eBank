package user;

public class UserSession {

    private static User activeUser;

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void clear() {
        activeUser = null;
    }

}
