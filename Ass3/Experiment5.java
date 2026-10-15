import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        validateAmount(amount);
        balance += amount;
    }

    public void withdraw(double amount) throws InvalidAmountException, 
        InsufficientBalanceException {

        validateAmount(amount);
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        balance -= amount;
    }

    private void validateAmount(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class Experiment5 {
    static Account findAccount(Map<String, Account> accounts, String number)
            throws AccountNotFoundException {
        Account account = accounts.get(number);

        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + number);
        }

        return account;
    }

    static void deposit(Map<String, Account> accounts, String number, double amount)
            throws AccountNotFoundException, InvalidAmountException {
        Account account = findAccount(accounts, number);
        account.deposit(amount);
    }

    static void withdraw(Map<String, Account> accounts, String number, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        Account account = findAccount(accounts, number);
        account.withdraw(amount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();

        accounts.put("1001", new Account("1001", 5000));
        accounts.put("1002", new Account("1002", 2500));

        try {
            System.out.print("Enter account number: ");
            String number = sc.nextLine();

            Account account = findAccount(accounts, number);

            System.out.print("Enter deposit amount: ");
            double depositAmount = Double.parseDouble(sc.nextLine());
            deposit(accounts, number, depositAmount);
            System.out.println("Deposit successful");

            System.out.print("Enter withdrawal amount: ");
            double withdrawalAmount = Double.parseDouble(sc.nextLine());
            withdraw(accounts, number, withdrawalAmount);
            System.out.println("Withdrawal successful");

            System.out.println("Account balance: " + account.getBalance());
        } 
        catch (AccountNotFoundException e) {
            System.out.println("AccountNotFoundException: " + e.getMessage());
        } 
        catch (InvalidAmountException e) {
            System.out.println("InvalidAmountException: " + e.getMessage());
        } 
        catch (InsufficientBalanceException e) {
            System.out.println("InsufficientBalanceException: " + e.getMessage());
        } 
        catch (NumberFormatException e) {
            System.out.println("Invalid numeric input");
        } 
        finally {
            sc.close();
        }
    }
}
