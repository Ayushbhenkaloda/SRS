import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage {

    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(SignUpPage::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        Font smallFont = new Font("SansSerif", Font.BOLD, 12);  // Small font size
        Font mediumFont = new Font("SansSerif", Font.BOLD, 18); // Medium font size
        Font largeFont = new Font("SansSerif", Font.BOLD, 24);
        Font customTitleFont = new Font("Serif", Font.ITALIC | Font.BOLD, 36); // New font style and size

        JFrame frame = new JFrame("Sign Up Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.getContentPane().setBackground(new Color(255,143,171));
//        frame.getContentPane().setBackground(new Color(255,194,209));
//        frame.getContentPane().setBackground(new Color(188,71,73));
        frame.getContentPane().setForeground(new Color(255,255,255));
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        ImageIcon imageIcon = new ImageIcon("APSIT-removebg-preview.png");
//        JLabel imageLabel = new JLabel(imageIcon);
        Image originalImage = imageIcon.getImage();
        // Increase the height and width (e.g., by 50%)
        Image resizedImage = originalImage.getScaledInstance(280, 65, Image.SCALE_SMOOTH); 
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedImageIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;  // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(imageLabel, gbc);

        //title logo
        JLabel titleLabel = new JLabel("<html><span style='font-size: 55px; color: red;'>S</span>tudent Record System</html>");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(customTitleFont);
//        titleLabel.setFont(largeFont);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.anchor=GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);

        //SPACE
        gbc.gridy=2;
        gbc.gridwidth=1;
        frame.add(new JLabel(" "),gbc);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
//        gbc.gridwidth = 1;  // Reset gridwidth
        frame.add(usernameLabel, gbc);

        // Username TextField
        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        frame.add(usernameField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(largeFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(passwordLabel, gbc);

        // Password Field
        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        frame.add(passwordField, gbc);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth=2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(signUpButton, gbc);

        // Add ActionListener to Sign Up Button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Simple validation
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Both fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Here, you would typically handle sign-up logic, e.g., saving the data or checking against a database
                    JOptionPane.showMessageDialog(frame, "Sign-Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Clear fields after successful sign-up
                    frame.setVisible(false);
                    SchoolManagementSystem SchoolManagementSystem = new SchoolManagementSystem();
                    SchoolManagementSystem.setVisible(true);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });

        // Set the frame visible
        frame.setVisible(true);
    }
}
