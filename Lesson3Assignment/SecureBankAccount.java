// ============================
// Decorator Pattern - SecureBankAccount
// ============================
class SecureBankAccount extends BankAccount {
    private BankAccount wrappedAccount;

    public SecureBankAccount(BankAccount account) {
        super(account.accountNumber, account.balance);
        this.wrappedAccount = account;
    }

    @Override
    public void withdraw(double amount) throws OverdrawException, InvalidAccountOperationException {
        if (amount > 500) {
            throw new InvalidAccountOperationException("Cannot withdraw more than $500 per transaction.");
        }
        wrappedAccount.withdraw(amount);
    }

    @Override
    public void deposit(double amount) throws NegativeDepositException {
        wrappedAccount.deposit(amount);
    }

    @Override
    public double getBalance() {
        return wrappedAccount.getBalance();
    }
}
