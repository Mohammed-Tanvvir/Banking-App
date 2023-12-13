import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    private static final String FILE_PATH = "customer_data.txt";
    private static Map<String, CustomerDTO> customerDatabase;

    static {
        customerDatabase = loadCustomersFromFile();
    }

    public static Customer createNewCustomer(String name, String email, String address, String pin, Double checkingInitialBalance, Double savingsInitialBalance) {
        CustomerDTO newCustomerDTO = new CustomerDTO(name, email, address, pin, checkingInitialBalance, savingsInitialBalance);
        customerDatabase.put(pin, newCustomerDTO);
        saveCustomersToFile();
        return convertToCustomer(newCustomerDTO);
    }

    public static Customer getCustomerByPin(String pin) {
        CustomerDTO customerDTO = customerDatabase.get(pin);
        return (customerDTO != null) ? convertToCustomer(customerDTO) : null;
    }

    
    private static void saveCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (CustomerDTO customerDTO : customerDatabase.values()) {
                String line = String.format("%s;%s;%s;%s;%s;%s",
                        customerDTO.getName(), customerDTO.getEmail(),
                        customerDTO.getAddress(), customerDTO.getPin(),
                        customerDTO.getCheckingInitialBalance(), customerDTO.getSavingsInitialBalance());//new line for balances
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Customer data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, CustomerDTO> loadCustomersFromFile() {
        Map<String, CustomerDTO> loadedCustomers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {//adjusted length for balances
                    CustomerDTO customerDTO = new CustomerDTO(
                            parts[0], parts[1], parts[2], parts[3],
                            Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));//added line for balances
                    loadedCustomers.put(parts[3], customerDTO);
                }
            }
        } catch (IOException | NumberFormatException e) {
            // If the file doesn't exist or has invalid data, return an empty map
            return new HashMap<>();
        }
        return loadedCustomers;
    }

    private static Customer convertToCustomer(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getAddress(),
                customerDTO.getPin()
        );
    }
}