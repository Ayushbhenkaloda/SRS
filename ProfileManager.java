import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ProfileManager extends JFrame {
    private static final Color FRAME_COLOR = new Color(234, 242, 215); 
    private JLabel profilePicLabel;
    private JTextField studentIdField, nameField, dobField, genderField, contactField;
    private JTextField yearField, classField;
    private JButton uploadButton, saveButton, exitButton;

    public ProfileManager() {
        setTitle("Profile");
        setSize(500, 700); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        getContentPane().setBackground(FRAME_COLOR);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        // Profile Picture Section
        profilePicLabel = new JLabel("Profile Picture");
        profilePicLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        profilePicLabel.setPreferredSize(new Dimension(100, 125)); 
        profilePicLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(profilePicLabel, gbc);

        uploadButton = new JButton("Upload Picture");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image image = imageIcon.getImage().getScaledInstance(100, 125, Image.SCALE_SMOOTH);
                    profilePicLabel.setIcon(new ImageIcon(image));
                }
            }
        });
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(uploadButton, gbc);
        
        // Basic Info Section
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Student ID:"), gbc);
        studentIdField = new JTextField(20);
        gbc.gridx = 1;
        add(studentIdField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Date of Birth:"), gbc);
        dobField = new JTextField(20);
        gbc.gridx = 1;
        add(dobField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Gender:"), gbc);
        genderField = new JTextField(20);
        gbc.gridx = 1;
        add(genderField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Contact Information:"), gbc);
        contactField = new JTextField(20);
        gbc.gridx = 1;
        add(contactField, gbc);
        
        // Year and Class Section
        gbc.gridx = 0;
        gbc.gridy = 7; 
        add(new JLabel("Year:"), gbc);
        yearField = new JTextField(20);
        gbc.gridx = 1;
        add(yearField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8; 
        add(new JLabel("Class:"), gbc);
        classField = new JTextField(20);
        gbc.gridx = 1;
        add(classField, gbc);
        
        // Save and Exit Button Section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Profile Saved!");
            }
        });
        buttonPanel.add(saveButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }
    
    public void showProfile() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfileManager().setVisible(true);
            }
        });
    }
}
