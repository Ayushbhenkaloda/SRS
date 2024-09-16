import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentDetailsForm extends JFrame {

    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField dobField;
    private JTextField contactField;
    private JTextArea addressArea;
    private JComboBox<String> genderComboBox;

    // Database URL and credentials
    private static final String DB_URL = "jdbc:sqlite:student_details.db";

    public StudentDetailsForm() {
        setTitle("Student Details Form");
        setSize(800, 350);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Set the background color of the content pane
        getContentPane().setBackground(new Color(255, 194, 209));

        // Initialize form fields
        studentIdField = new JTextField();
        nameField = new JTextField();
        dobField = new JTextField();
        contactField = new JTextField();
        addressArea = new JTextArea(3, 20);
        genderComboBox = new JComboBox<>(new String[] {"Male", "Female", "Other", "Rather not say"});

        // Add form fields and labels
        add(new JLabel("Student ID:"));
        add(studentIdField);

        add(new JLabel("Name:"));
        add(nameField);

        add(new JLabel("Date of Birth:"));
        add(dobField);

        add(new JLabel("Gender:"));
        add(genderComboBox);

        add(new JLabel("Contact Information:"));
        add(contactField);

        add(new JLabel("Address:"));
        add(addressArea);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(188, 71, 73)); // Set background color
        submitButton.setForeground(Color.BLACK); // Set text color to white for contrast
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(new SubmitActionListener());
        add(submitButton);

        // Setup database
        setupDatabase();
    }

    private void setupDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                + "student_id TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "dob TEXT NOT NULL, "
                + "gender TEXT NOT NULL, "
                + "contact_info TEXT NOT NULL, "
                + "address TEXT NOT NULL"
                + ");";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStudentToDatabase(String studentId, String name, String dob, String gender, String contactInfo, String address) {
        String insertSQL = "INSERT INTO students (student_id, name, dob, gender, contact_info, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, dob);
            pstmt.setString(4, gender);
            pstmt.setString(5, contactInfo);
            pstmt.setString(6, address);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student details added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student details.");
        }
    }

    private class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = studentIdField.getText();
            String name = nameField.getText();
            String dob = dobField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String contactInfo = contactField.getText();
            String address = addressArea.getText();

            if (studentId.isEmpty() || name.isEmpty() || dob.isEmpty() || contactInfo.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(StudentDetailsForm.this, "Please fill all fields.");
            } else {
                addStudentToDatabase(studentId, name, dob, gender, contactInfo, address);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentDetailsForm form = new StudentDetailsForm();
            form.setVisible(true);
        });
    }
}

