import java.util.ArrayList;
import java.util.List;

// ============================
// Custom Exception Classes
// ============================
class NegativeDepositException extends Exception {
    public NegativeDepositException(String message) {
        super(message);
    }
}

class OverdrawException extends Exception {
    public OverdrawException(String message) {
        super(message);
    }
}

class InvalidAccountOperationException extends Exception {
    public InvalidAccountOperationException(String message) {
        super(message);
    }
}

// ============================
// Observer Pattern - Define Observer Interface
// ============================
interface Observer {
    void update(String message);
}

// Concrete Observer
class TransactionLogger implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Transaction Log: " + message);
    }
}

// ============================
// BankAccount Class (Observable)
// ============================
class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected boolean isActive;
    private List<Observer> observers = new ArrayList<>();

    public BankAccount(String accNum, double initialBalance) {
        this.accountNumber = accNum;
        this.balance = initialBalance;
        this.isActive = true;
    }

    // Attach observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Notify observers
    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void deposit(double amount) throws NegativeDepositException {
        if (amount < 0) {
            throw new NegativeDepositException("Deposit amount cannot be negative.");
        }
        balance += amount;
        notifyObservers("Deposited $" + amount + ". New Balance: $" + balance);
    }

    public void withdraw(double amount) throws OverdrawException, InvalidAccountOperationException {
        if (!isActive) {
            throw new InvalidAccountOperationException("Cannot withdraw from a closed account.");
        }
        if (amount > balance) {
            throw new OverdrawException("Insufficient funds. Cannot withdraw $" + amount);
        }
        balance -= amount;
        notifyObservers("Withdrew $" + amount + ". New Balance: $" + balance);
    }

    public double getBalance() {
        return balance;
    }

    public void closeAccount() {
        isActive = false;
        notifyObservers("Account #" + accountNumber + " has been closed.");
    }
}
