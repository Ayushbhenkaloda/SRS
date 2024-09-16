import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class GradePage {

    private JFrame frame;
    private JTextField txtStudentID;
    private JTextField txtStudentName;
    private JTextField txtGrade;
    private JTable table;

    // Entry point of the application
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GradePage window = new GradePage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Create the application
    public GradePage() {
        initialize();
    }

    // Initialize the contents of the frame
    private void initialize() {
        frame = new JFrame();

        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground( new Color(255,229,236));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblStudentID = new JLabel("Student ID:");
        panel.add(lblStudentID);

        txtStudentID = new JTextField();
        panel.add(txtStudentID);
        txtStudentID.setColumns(10);

        JLabel lblStudentName = new JLabel("Student Name:");
        panel.add(lblStudentName);

        txtStudentName = new JTextField();
        panel.add(txtStudentName);
        txtStudentName.setColumns(10);

        JLabel lblGrade = new JLabel("Grade:");
        panel.add(lblGrade);

        txtGrade = new JTextField();
        panel.add(txtGrade);
        txtGrade.setColumns(10);

        JButton btnAdd = new JButton("Add Grade");
        btnAdd.setBackground(new Color(188, 71, 73));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGrade();
            }
        });
        panel.add(btnAdd);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(new Color(255,143,171));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Student ID", "Student Name", "Grade"}
        ));
        scrollPane.setViewportView(table);
    }

    // Method to add grade to the table
    private void addGrade() {
        String studentID = txtStudentID.getText();
        String studentName = txtStudentName.getText();
        String grade = txtGrade.getText();


        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{studentID, studentName, grade});

        txtStudentID.setText("");
        txtStudentName.setText("");
        txtGrade.setText("");
    }
}

