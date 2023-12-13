public class Customer{
    private String name;
    private String email;
    private String address;
    private String pin;
    private Checking checking;
    private Savings savings;

    public Customer(String name, String email, String address, String pin) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.pin = pin;

        // Initialize Checking and Savings objects
        this.checking = new Checking();
        this.savings = new Savings();
    }

    public double getCheckingInitialBalance() {
        return checking.getInitialBalance();
    }

    public double getSavingsInitialBalance() {
        return savings.getInitialBalance();
    }
    
    public Checking getChecking() {
        return checking;
    }

    public Savings getSavings() {
        return savings;
    }

    public boolean withdraw(double amount) {
        return checking.withdraw(amount);
    }

    public void deposit(double amount) {
        checking.deposit(amount);
    }

    public boolean transfer(double amount) {
        // Assuming I want to transfer between checking and savings
        return checking.transfer(amount, savings);
    }
}