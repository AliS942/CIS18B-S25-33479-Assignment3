import java.util.Scanner;

public class BankAccountTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // User input for initial balance
            System.out.print("Enter initial balance: ");
            double initialBalance = scanner.nextDouble();
            
            BankAccount account = new BankAccount("123456", initialBalance);
            System.out.println("Bank Account Created: #123456");

            // Attach TransactionLogger
            TransactionLogger logger = new TransactionLogger();
            account.addObserver(logger);

            // Wrap account in SecureBankAccount decorator
            SecureBankAccount secureAccount = new SecureBankAccount(account);

            // Prompt user for deposit
            System.out.print("Enter deposit amount: ");
            double depositAmount = scanner.nextDouble();
            secureAccount.deposit(depositAmount);

            // Prompt user for withdrawal
            System.out.print("Enter withdrawal amount: ");
            double withdrawalAmount = scanner.nextDouble();
            secureAccount.withdraw(withdrawalAmount);

            // Display final balance
            System.out.println("Final Balance: $" + secureAccount.getBalance());

        } catch (NegativeDepositException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (OverdrawException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidAccountOperationException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
