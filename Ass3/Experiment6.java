import java.util.Scanner;

class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

class LoginSystem {
    private final String username = "admin";
    private final String password = "Admin@123";
    private final int maxAttempts = 3;
    private int failedAttempts = 0;
    private boolean locked = false;

    public void login(String enteredUsername, String enteredPassword)
            throws InvalidUsernameException, InvalidPasswordException, AccountLockedException {

        if (locked) {
            throw new AccountLockedException("Account is locked");
        }

        if (!username.equals(enteredUsername)) {
            failedAttempts++;
            checkLock();
            throw new InvalidUsernameException("Invalid username");
        }

        if (!password.equals(enteredPassword)) {
            failedAttempts++;
            checkLock();
            throw new InvalidPasswordException("Invalid password");
        }

        failedAttempts = 0;
        System.out.println("Login successful");
    }

    private void checkLock() throws AccountLockedException {
        if (failedAttempts >= maxAttempts) {
            locked = true;
            throw new AccountLockedException("Maximum failed attempts reached");
        }
    }
}

public class Experiment6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginSystem system = new LoginSystem();

        try {
            for (int attempt = 1; attempt <= 3; attempt++) {
                System.out.print("Username: ");
                String username = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                try {
                    system.login(username, password);
                    break;
                } 
                catch (InvalidUsernameException e) {
                    System.out.println("Invalid username");
                } 
                catch (InvalidPasswordException e) {
                    System.out.println("Invalid password");
                } 
                catch (AccountLockedException e) {
                    System.out.println("Account locked");
                    break;
                }
            }
        } 
        finally {
            sc.close();
            System.out.println("Login process completed");
        }
    }
}
