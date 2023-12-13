public class Checking extends Accounts {
  
	public Checking() {
        // Call the super constructor with the initial balance for Checking
        super(1000.0); // set the initial balance for Checking 
    }
	
	//to txt file
	@Override
    public String getAccountType() {
        return "Checking";
    }


    @Override
    public boolean withdraw(double amount) {
        if (amount >= 0 && (amount <= getBalance() )) {
            setBalance(getBalance() - amount);
            return true; // Withdrawal successful
        }
        return false; // Insufficient balance or invalid amount
    }

    
    @Override
    public void deposit(double amount) {
        if (amount >= 0) {
            setBalance(getBalance() + amount);
            
        }
    }

    @Override
    public boolean transfer(double amount, Accounts recipientAccount) {
        if (recipientAccount instanceof Savings) {
            // Assuming you can transfer from checking to savings
            if (withdraw(amount)) {
                recipientAccount.deposit(amount);
                return true; // Transfer successful
            }
        }
        return false; // Transfer not allowed or unsuccessful
    }
}
