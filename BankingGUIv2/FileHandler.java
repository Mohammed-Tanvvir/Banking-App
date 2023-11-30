import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    // Simulating storage for customer data
    private static Map<String, Customer> customerDatabase = new HashMap<>();

    // Method to create a new customer
    public static Customer createNewCustomer(String name, String email, String address, String pin) {
        Customer newCustomer = new Customer(name, email, address, pin);
        customerDatabase.put(pin, newCustomer);
        return newCustomer;
    }

    // Method to get a customer by PIN
    public static Customer getCustomerByPin(String pin) {
        return customerDatabase.get(pin);
    }
}
