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
    private JPanel balancePanel;

    private JTextField pinInputField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField addressField;
    private String enteredPin;

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

        welcomePanel = new JPanel(new GridLayout(3, 1));
        createAccountPanel = new JPanel(new GridLayout(5, 2));
        mainMenuPanel = new JPanel(new GridLayout(3, 1));
        balancePanel = new JPanel(new GridLayout(3, 2));

        pinInputField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();

        currentCustomer = null; // Initialize currentCustomer to null
    }

    private void createLayout() {
        // Welcome Panel
        welcomePanel.add(new JLabel("Welcome to Banking App"));

        // PIN Input Panel
        JPanel pinInputPanel = new JPanel();
        pinInputPanel.add(new JLabel("Enter PIN:"));
        pinInputPanel.add(pinInputField);
        JButton enterPinButton = new JButton("Enter");
        pinInputPanel.add(enterPinButton);

        welcomePanel.add(pinInputPanel);

        JButton newUserButton = new JButton("New User");
        welcomePanel.add(newUserButton);
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

        mainMenuPanel.add(checkBalanceButton);
        mainMenuPanel.add(withdrawButton);
        mainMenuPanel.add(depositButton);
        cardPanel.add(mainMenuPanel, "MainMenu");

         // Balance Panel
        JLabel checkingBalanceLabel = new JLabel("Checking Balance:");
        JLabel savingsBalanceLabel = new JLabel("Savings Balance:");
        JTextField checkingBalanceField = new JTextField();
        JTextField savingsBalanceField = new JTextField();
        
        checkingBalanceField.setEditable(false);
        savingsBalanceField.setEditable(false);
        
        balancePanel.add(checkingBalanceLabel);
        balancePanel.add(checkingBalanceField);
        balancePanel.add(savingsBalanceLabel);
        balancePanel.add(savingsBalanceField);
        
        // Back button in the Balance Panel
        JButton backButton = new JButton("Back to Main Menu");
        balancePanel.add(backButton);
        
        cardPanel.add(balancePanel, "Balance");
        
        add(cardPanel);
        }
        
 private void setupListeners() {
          JButton newUserButton = (JButton) welcomePanel.getComponent(2);
          newUserButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  cardLayout.show(cardPanel, "CreateAccount");
              }
          });
      
          JButton enterPinButton = (JButton) welcomePanel.getComponent(1).getParent().getComponent(2);
          enterPinButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  enteredPin = pinInputField.getText(); // Assigns the entered PIN to the variable
                  // NEED method to check the PIN, replace "checkPin" with logic eventually!
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
                  // Still NEED a method to create a new customer, replace "createNewCustomer" with logic
                  currentCustomer = FileHandler.createNewCustomer(nameField.getText(), emailField.getText(), addressField.getText(), enteredPin);
                  JOptionPane.showMessageDialog(null, "Account created successfully!");
                  cardLayout.show(cardPanel, "MainMenu");
              }
          });
      

        JButton checkBalanceButton = (JButton) mainMenuPanel.getComponent(0);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCustomer != null) {
                    updateBalanceFields(currentCustomer);
                    cardLayout.show(cardPanel, "Balance");
                }
            }
        });

        JButton withdrawButton = (JButton) mainMenuPanel.getComponent(1);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCustomer != null) {
                    updateBalanceFields(currentCustomer);
                    // Implement withdraw logic here
                    // After completing the withdraw, can update the balance fields and show the balance panel
                    cardLayout.show(cardPanel, "Balance");
                }
            }
        });

        JButton depositButton = (JButton) mainMenuPanel.getComponent(2);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCustomer != null) {
                    updateBalanceFields(currentCustomer);
                    // Implement deposit logic here
                    // After completing the deposit, can update the balance fields and show the balance panel
                    cardLayout.show(cardPanel, "Balance");
                }
            }
        });

       JButton backButton = (JButton) balancePanel.getComponent(balancePanel.getComponentCount() - 1);
                        backButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cardLayout.show(cardPanel, "MainMenu");
                            }
                        });
                
    }

    private void updateBalanceFields(Customer customer) {
        // methods to get checking and savings balances, need to replace with logic
        double checkingBalance = customer.getChecking().getBalance();
        double savingsBalance = customer.getSavings().getBalance();
        JTextField checkingBalanceField = (JTextField) balancePanel.getComponent(1);
        JTextField savingsBalanceField = (JTextField) balancePanel.getComponent(3);
        checkingBalanceField.setText(String.valueOf(checkingBalance));
        savingsBalanceField.setText(String.valueOf(savingsBalance));
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
