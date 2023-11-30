public class Customer {
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
        this.checking = new Checking();
        this.savings = new Savings();
    }

    // Getter methods for name, email, address, and pin

    public Checking getChecking() {
        return checking;
    }

    public Savings getSavings() {
        return savings;
    }
}