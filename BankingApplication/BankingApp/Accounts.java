public abstract class Accounts {
    private double balance;

    public Accounts(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getInitialBalance() {
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //withdraw method
    public boolean withdraw(double amount) {
        if (amount >= 0 && amount <= balance) {
            balance -= amount;
            return true; // Withdrawal successful
        }
        return false; // Insufficient balance or invalid amount
    }
    
    //deposit method
    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        }
    }

    //transfer method
    public boolean transfer(double amount, Accounts recipientAccount) {
        if (withdraw(amount)) {
            recipientAccount.deposit(amount);
            return true; // Transfer successful
        }
        return false; // Insufficient balance or invalid amount
        
     //to add balances to txt file   
    }public abstract String getAccountType();


}