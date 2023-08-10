import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginAuthenticationSystem extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private Map<String, String> userDatabase; // In-memory database for demo purposes

    public LoginAuthenticationSystem() {
        setTitle("Login Authentication System");
        setSize(300, 200); // Slightly increased height to fit registration button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userDatabase = new HashMap<>();

        // Adding a demo user for testing
        userDatabase.put("user123", "password123"); // Hashed and salted password

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                authenticateUser(username, new String(password));
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                registerUser(username, new String(password));
            }
        });

        setLayout(new GridLayout(4, 2)); // Increased rows to add registration button
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(loginButton);
        add(new JLabel()); // Empty label for spacing
        add(registerButton); // Registration button
    }

    private void authenticateUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            String storedPassword = userDatabase.get(username);

            // In a real scenario, you would use secure password hashing and comparison
            if (storedPassword.equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect password!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "User not found.");
        }
    }

    private void registerUser(String username, String password) {
        if (!userDatabase.containsKey(username)) {
            // In a real scenario, you would use secure password hashing before storing
            userDatabase.put(username, password);
            JOptionPane.showMessageDialog(this, "Registration successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginAuthenticationSystem().setVisible(true);
            }
        });
    }
}
