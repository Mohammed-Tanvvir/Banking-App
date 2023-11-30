public class BankingMain {
    public static void main(String[] args) {
        System.out.println("Banking Project class instantiated");

        Bagui bankingGUI = new Bagui();
        //bankingGUI.print(); // This will print the class name and initialization details.
        Customer customer = new Customer();
        customer.print();
        FileHandler fileHandler = new FileHandler();
        fileHandler.print();
        ATM atm = new ATM();
        atm.print();
        Accounts accounts = new Accounts();
        accounts.print(); // This will print the class name and account types.

        Checking checkingsAccount = new Checking();
        checkingsAccount.print();        
        Savings savingsAccount = new Savings();
        savingsAccount.print(); 

        // Continue with your banking application logic here.
    }
}
//When you run the BankingMain class, it will initialize the various classes and print their names to indicate they are initialized. You can further develop the methods and properties for each class as required for your banking project.





