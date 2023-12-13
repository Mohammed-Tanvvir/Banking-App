
public class Savings extends Accounts {

	public Savings() {
        // Call the super constructor with the initial balance for Savings
        super(500.0); // set the initial balance for Savings
    }
	
	//to txt file
	 public String getAccountType() {
	        return "Savings";
	    }
	
    @Override
    public boolean withdraw(double amount) {
        if (amount >= 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true; // Withdrawal successful
        }
        return false; // Insufficient balance or invalid amount
    }

    
    @Override
    public void deposit(double amount) {
        if (amount >= 0) {
            setBalance(getBalance() + amount);
            // Log the transaction or perform additional operations if needed
        }
    }

    
    @Override
    public boolean transfer(double amount, Accounts recipientAccount) {
        if (recipientAccount instanceof Checking) {
            // Assuming you can transfer from savings to checking
            if (withdraw(amount)) {
                recipientAccount.deposit(amount);
                return true; // Transfer successful
            }
        }
        return false; // Transfer not allowed or unsuccessful
    }

}