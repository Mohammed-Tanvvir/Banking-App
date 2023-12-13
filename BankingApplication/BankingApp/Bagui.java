import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bagui extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel welcomePanel;
    private JPanel createAccountPanel;
    private JPanel mainMenuPanel;
    private JPanel checkBalancePanel;
    private JPanel withdrawPanel;
    private JPanel depositPanel;
    private JPanel transferPanel;
    
    private JButton backButtonWithdraw;
    private JButton backButtonTransfer;
    private JButton backButtonDeposit;
    private JButton backButtonCheckBalance;

    private JTextField pinInputField;
    private JTextField pinVerifyField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField addressField;
    private String enteredPin;
    private JTextField withdrawAmountField;
    private JTextField depositAmountField;
    private JTextField transferAmountField;

    private Customer currentCustomer;

    public Bagui() {
        initializeComponents();
        createLayout();
        setupListeners();
        setTitle("Banking Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout.show(cardPanel, "Welcome");
        setVisible(true);
    }

    private void initializeComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        //main panels
        welcomePanel = new JPanel(new GridLayout(3, 2));
        createAccountPanel = new JPanel(new GridLayout(5, 2));
        mainMenuPanel = new JPanel(new GridLayout(4, 1));

        // Create new panels for specific functionalities
        checkBalancePanel = new JPanel(new GridLayout(3, 2));
        withdrawPanel = new JPanel(new GridLayout(3, 2));
        depositPanel = new JPanel(new GridLayout(3, 2));
        transferPanel = new JPanel(new GridLayout(3, 2));

        pinInputField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();
        pinVerifyField = new JTextField();

        currentCustomer = null; // Initialize currentCustomer to null
    }

    private void createLayout() {
        // Welcome Panel
    	welcomePanel.add(new JLabel("Welcome to Banking App"));
    	JButton newUserButton = new JButton("New User");
        welcomePanel.add(newUserButton);
    	welcomePanel.add(new JLabel("Enter PIN:"));
    	welcomePanel.add(pinVerifyField); // Corrected line
    	JButton enterPinButton = new JButton("Enter");
    	welcomePanel.add(enterPinButton);

        cardPanel.add(welcomePanel, "Welcome");

        // Create Account Panel
        createAccountPanel.add(new JLabel("Name:"));
        createAccountPanel.add(nameField);
        createAccountPanel.add(new JLabel("Email:"));
        createAccountPanel.add(emailField);
        createAccountPanel.add(new JLabel("Address:"));
        createAccountPanel.add(addressField);
        createAccountPanel.add(new JLabel("PIN:"));
        createAccountPanel.add(pinInputField);

        JButton createAccountButton = new JButton("Create Account");
        createAccountPanel.add(createAccountButton);
        cardPanel.add(createAccountPanel, "CreateAccount");

        // Main Menu Panel
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");

        mainMenuPanel.add(checkBalanceButton);
        mainMenuPanel.add(withdrawButton);
        mainMenuPanel.add(depositButton);
        mainMenuPanel.add(transferButton);
        cardPanel.add(mainMenuPanel, "MainMenu");

        // Check Balance Panel
        checkBalancePanel.add(new JLabel("Checking Balance:"));
        checkBalancePanel.add(new JTextField());
        checkBalancePanel.add(new JLabel("Savings Balance:"));
        checkBalancePanel.add(new JTextField());

        // Withdraw Panel
        withdrawPanel.add(new JLabel("Withdraw Amount:"));
        withdrawAmountField = new JTextField();
        withdrawPanel.add(withdrawAmountField);

        JButton withdrawButtonPanel = new JButton("Withdraw");
        withdrawButtonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(withdrawAmountField.getText());
                boolean success = currentCustomer.withdraw(amount);

                if (success) {
                    updateBalanceFields(currentCustomer);
                    cardLayout.show(cardPanel, "Balance");
                } else {
                    JOptionPane.showMessageDialog(null, "Withdrawal failed. Insufficient balance or invalid amount.");
                }
            }
        });
        withdrawPanel.add(withdrawButtonPanel);
        
     // Deposit Panel
        depositPanel.add(new JLabel("Deposit Amount:"));
        depositAmountField = new JTextField();
        depositPanel.add(depositAmountField);
        
        JButton depositButtonPanel = new JButton("Deposit");
        depositButtonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(depositAmountField.getText());
                currentCustomer.deposit(amount);

                updateBalanceFields(currentCustomer);
                cardLayout.show(cardPanel, "Balance");
            }
        });
        depositPanel.add(depositButtonPanel);

     // Transfer Panel
        transferPanel.add(new JLabel("Transfer Amount:"));
        transferAmountField = new JTextField();
        transferPanel.add(transferAmountField);

        JButton transferButtonPanel = new JButton("Transfer");
        transferButtonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(transferAmountField.getText());
                boolean success = currentCustomer.transfer(amount);

                if (success) {
                    updateBalanceFields(currentCustomer);
                    cardLayout.show(cardPanel, "Balance");
                } else {
                    JOptionPane.showMessageDialog(null, "Transfer failed. Insufficient balance or invalid transfer.");
                }
            }
        });
        transferPanel.add(transferButtonPanel);
        
     // Back button in the Balance Panel
        backButtonWithdraw = new JButton("Back to Main Menu");
        withdrawPanel.add(backButtonWithdraw);

        backButtonTransfer = new JButton("Back to Main Menu");
        transferPanel.add(backButtonTransfer);

        backButtonDeposit = new JButton("Back to Main Menu");
        depositPanel.add(backButtonDeposit);

        backButtonCheckBalance = new JButton("Back to Main Menu");
        checkBalancePanel.add(backButtonCheckBalance);

       
        // Add new panels to the cardPanel
        cardPanel.add(checkBalancePanel, "CheckBalance");
        cardPanel.add(withdrawPanel, "Withdraw");
        cardPanel.add(depositPanel, "Deposit");
        cardPanel.add(transferPanel, "Transfer");

        add(cardPanel);
    }
    

    private void setupListeners() {
        JButton newUserButton = (JButton) welcomePanel.getComponent(1);
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CreateAccount");
            }
        });

        JButton enterPinButton = (JButton) welcomePanel.getComponent(4);
        enterPinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredPin = pinVerifyField.getText();//changed pinInputField
                currentCustomer = FileHandler.getCustomerByPin(enteredPin);
                if (currentCustomer != null) {
                    cardLayout.show(cardPanel, "MainMenu");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect PIN. Please try again.");
                }
            }
        });


        JButton createAccountButton = (JButton) createAccountPanel.getComponent(8);
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredPin = pinInputField.getText(); // Assigns the entered PIN to the variable
                
                // Call createNewCustomer with all required parameters
                currentCustomer = FileHandler.createNewCustomer(
                    nameField.getText(), 
                    emailField.getText(), 
                    addressField.getText(),
                    enteredPin,
                    0.0,  // Default value for checkingInitialBalance
                    0.0   // Default value for savingsInitialBalance
                );
                
                JOptionPane.showMessageDialog(null, "Account created successfully!");
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        JButton checkBalanceButton = (JButton) mainMenuPanel.getComponent(0);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalanceFields(currentCustomer);
                cardLayout.show(cardPanel, "CheckBalance");
            }
        });

        JButton withdrawButton = (JButton) mainMenuPanel.getComponent(1);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Withdraw");
            }
        });

        JButton depositButton = (JButton) mainMenuPanel.getComponent(2);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Deposit");
            }
        });

        JButton transferButton = (JButton) mainMenuPanel.getComponent(3);
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Transfer");
            }
        });
        
     // Add action listeners to the back buttons
        backButtonWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        backButtonTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        backButtonDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });

        backButtonCheckBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
        
        JButton withdrawButtonPanel = (JButton) withdrawPanel.getComponent(2);
        withdrawButtonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to withdraw amount
                double amount = Double.parseDouble(withdrawAmountField.getText());
                boolean success = currentCustomer.withdraw(amount);

                if (success) {
                    updateBalanceFields(currentCustomer);
                    cardLayout.show(cardPanel, "Balance");
                } else {
                    JOptionPane.showMessageDialog(null, "Withdrawal failed. Insufficient balance or invalid amount.");
                }
            }
        });

        JButton depositButtonPanel = (JButton) depositPanel.getComponent(2);
        depositButtonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(depositAmountField.getText());
                currentCustomer.deposit(amount);

                updateBalanceFields(currentCustomer);
                cardLayout.show(cardPanel, "Balance");
            }
        });

        JButton transferButtonPanel = (JButton) transferPanel.getComponent(2);
        transferButtonPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(transferAmountField.getText());
                boolean success = currentCustomer.transfer(amount);

                if (success) {
                    updateBalanceFields(currentCustomer);
                    cardLayout.show(cardPanel, "Balance");
                } else {
                    JOptionPane.showMessageDialog(null, "Transfer failed. Insufficient balance or invalid transfer.");
                }
            }
        });
    }

    private void updateBalanceFields(Customer customer) {
        // Get the initial balances for display
        double checkingInitialBalance = customer.getCheckingInitialBalance();
        double savingsInitialBalance = customer.getSavingsInitialBalance();

        // Update the balance fields in the GUI
        JTextField checkingBalanceField = (JTextField) checkBalancePanel.getComponent(1);
        JTextField savingsBalanceField = (JTextField) checkBalancePanel.getComponent(3);
        checkingBalanceField.setText(String.valueOf(checkingInitialBalance));
        savingsBalanceField.setText(String.valueOf(savingsInitialBalance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Bagui();
            }
        });
    }
}