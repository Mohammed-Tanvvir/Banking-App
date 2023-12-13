import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private String name;
    private String email;
    private String address;
    private String pin;
    private double checkingInitialBalance;
    private double savingsInitialBalance;

    public CustomerDTO(String name, String email, String address, String pin,
            double checkingInitialBalance, double savingsInitialBalance) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.pin = pin;
        this.checkingInitialBalance = checkingInitialBalance;
        this.savingsInitialBalance = savingsInitialBalance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPin() {
        return pin;
    }
    
    public double getCheckingInitialBalance() {
        return checkingInitialBalance;
    }

    public double getSavingsInitialBalance() {
        return savingsInitialBalance;
    }
}