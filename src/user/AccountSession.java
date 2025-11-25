package user;

public class AccountSession {
    private static Account activeAccount;

    public static void setActiveAccount(Account acc) {
        activeAccount = acc;
    }

    public static Account getActiveAccount() {
        return activeAccount;
    }

    public static void clear() {
        activeAccount = null;
    }
}
