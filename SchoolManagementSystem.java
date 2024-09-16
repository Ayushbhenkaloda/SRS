import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchoolManagementSystem extends JFrame {
    private static final Color TURQUOISE = new Color(0,204,204);
    private static final Color DARK_DEMO = new Color(255,194,209);
    private static final Color LOGOUT_COLOR = new Color(188, 71, 73);
    private static final Color LIGHT_LIGHT = new Color(255, 229, 236);
    private JTabbedPane tabbedPane;

    public SchoolManagementSystem() {
        setTitle("School Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(LIGHT_LIGHT);

        JLabel logoLabel = new JLabel("School/Institution Logo", JLabel.LEFT);
        logoLabel.setOpaque(true);
        logoLabel.setBackground(LIGHT_LIGHT);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setBackground(LIGHT_LIGHT);

        JButton homeButton = new JButton("Home");
        homeButton.setBackground(TURQUOISE);
        homeButton.addActionListener(e -> showHomePage());
        navPanel.add(homeButton);

        JButton studentButton = new JButton("Student");
        studentButton.setBackground(TURQUOISE);
        studentButton.addActionListener(e -> showStudentPage());
        navPanel.add(studentButton);

        JButton courseButton = new JButton("Courses");
        courseButton.setBackground(TURQUOISE);
        courseButton.addActionListener(e -> showCoursePage());
        navPanel.add(courseButton);

        String[] navItems = {"Grades"};
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setBackground(TURQUOISE);
            navButton.addActionListener(new NavigationActionListener(item));
            navPanel.add(navButton);
        }

        headerPanel.add(navPanel, BorderLayout.CENTER);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(LIGHT_LIGHT);
        userPanel.add(new JLabel("Profile Picture"));
        userPanel.add(new JLabel("Username"));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(LOGOUT_COLOR);
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(SchoolManagementSystem.this,
                    "Are you sure you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        userPanel.add(logoutButton);

        headerPanel.add(userPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Main Dashboard
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(LIGHT_LIGHT);

        // Dashboard Tab
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(DARK_DEMO);

        // Search Bar
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(LIGHT_LIGHT);
        searchPanel.add(new JLabel("Search by Name/ID:"));
        JTextField searchField = new JTextField(15);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.white);
        searchPanel.add(searchButton);
        searchPanel.add(new JLabel("Filters:"));
        JComboBox<String> filterBox = new JComboBox<>(new String[]{"Grade Level", "Enrollment Status"});
        filterBox.setBackground(DARK_DEMO);
        searchPanel.add(filterBox);
        dashboardPanel.add(searchPanel, BorderLayout.NORTH);

        // Student List
        String[] columnNames = {"Student ID", "Name", "Grade", "Enrollment Date", "Status", "Actions"};
        Object[][] data = {}; // Populate this with student data as needed
        JTable studentTable = new JTable(data, columnNames);
        studentTable.setForeground(LIGHT_LIGHT);
        dashboardPanel.add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Quick Links
        JPanel quickLinksPanel = new JPanel();
        quickLinksPanel.setBackground(LIGHT_LIGHT);
        JButton addNewStudentButton = new JButton("Add New Student");
        addNewStudentButton.setBackground(TURQUOISE);
        addNewStudentButton.addActionListener(e -> showStudentDetailsForm());
        quickLinksPanel.add(addNewStudentButton);
        JButton generateReportsButton = new JButton("Generate Reports");
        generateReportsButton.setBackground(TURQUOISE);
        quickLinksPanel.add(generateReportsButton);
        JButton courseButton1 = new JButton("Course Enrollment");
        courseButton1.setBackground(TURQUOISE);
        quickLinksPanel.add(courseButton1);

        dashboardPanel.add(quickLinksPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Dashboard", dashboardPanel);

        // Student Details Tab
        JPanel studentDetailsPanel = new JPanel(new GridLayout(4, 1));
        studentDetailsPanel.setBackground(LIGHT_LIGHT);

        // Personal Information
        JPanel personalInfoPanel = new JPanel();
        personalInfoPanel.setBackground(LIGHT_LIGHT);
        personalInfoPanel.add(new JLabel("Student ID:"));
        personalInfoPanel.add(new JTextField(10));
        personalInfoPanel.add(new JLabel("Name:"));
        personalInfoPanel.add(new JTextField(15));
        personalInfoPanel.add(new JLabel("Date of Birth:"));
        personalInfoPanel.add(new JTextField(10));
        personalInfoPanel.add(new JLabel("Gender:"));
        personalInfoPanel.add(new JTextField(10));
        personalInfoPanel.add(new JLabel("Contact Information:"));
        personalInfoPanel.add(new JTextField(20));
        studentDetailsPanel.add(personalInfoPanel);

        // Academic Information
        JPanel academicInfoPanel = new JPanel();
        academicInfoPanel.setBackground(LIGHT_LIGHT);
        academicInfoPanel.add(new JLabel("Current Grade:"));
        academicInfoPanel.add(new JTextField(5));
        academicInfoPanel.add(new JLabel("Enrollment Date:"));
        academicInfoPanel.add(new JTextField(10));
        academicInfoPanel.add(new JLabel("Major/Program:"));
        JComboBox<String> programBox = new JComboBox<>(new String[]{"Program 1", "Program 2"});
        academicInfoPanel.add(programBox);
        programBox.setBackground(DARK_DEMO);
        studentDetailsPanel.add(academicInfoPanel);

        // Performance Summary
        JPanel performanceSummaryPanel = new JPanel();
        performanceSummaryPanel.setBackground(LIGHT_LIGHT);
        performanceSummaryPanel.add(new JLabel("GPA:"));
        performanceSummaryPanel.add(new JTextField(5));
        performanceSummaryPanel.add(new JLabel("Recent Grades:"));
        JTable gradesTable = new JTable(new Object[][]{}, new String[]{"Subject", "Grade", "Date"});
        performanceSummaryPanel.add(new JScrollPane(gradesTable));
        studentDetailsPanel.add(performanceSummaryPanel);

        // Attendance
        JPanel attendancePanel = new JPanel();
        attendancePanel.setBackground(LIGHT_LIGHT);
        attendancePanel.add(new JLabel("Total Days Present:"));
        attendancePanel.add(new JTextField(5));
        attendancePanel.add(new JLabel("Total Days Absent:"));
        attendancePanel.add(new JTextField(5));
        attendancePanel.add(new JLabel("Attendance History:"));
        JTable attendanceTable = new JTable(new Object[][]{}, new String[]{"Date", "Status"});
        attendancePanel.add(new JScrollPane(attendanceTable));
        studentDetailsPanel.add(attendancePanel);

        // Notes & Actions
        JPanel notesActionsPanel = new JPanel();
        notesActionsPanel.setBackground(LIGHT_LIGHT);
        notesActionsPanel.add(new JLabel("Notes:"));
        JTextArea notesTextArea = new JTextArea(5, 30);
        notesActionsPanel.add(new JScrollPane(notesTextArea));

        JButton saveButton = new JButton("Save");
        saveButton.setBackground(TURQUOISE);
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(SchoolManagementSystem.this,
                "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE));
        notesActionsPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(TURQUOISE);
        cancelButton.addActionListener(e -> {
            notesTextArea.setText("");
            JOptionPane.showMessageDialog(SchoolManagementSystem.this,
                    "Changes canceled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        });
        notesActionsPanel.add(cancelButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(TURQUOISE);
        deleteButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(SchoolManagementSystem.this,
                    "Are you sure you want to delete this record?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(SchoolManagementSystem.this,
                        "Record deleted.",
                        "Deleted",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        notesActionsPanel.add(deleteButton);

        studentDetailsPanel.add(notesActionsPanel);

        tabbedPane.addTab("Student Details", studentDetailsPanel);

        // Course Enrollment Tab
        JPanel courseEnrollmentPanel = new JPanel(new GridLayout(2, 1));

        // Available Courses
        JPanel availableCoursesPanel = new JPanel();
        availableCoursesPanel.setBackground(LIGHT_LIGHT);
        JTable availableCoursesTable = new JTable(new Object[][]{},
                new String[]{"Course ID", "Course Name", "Instructor", "Schedule", "Enrollment Status"});
        availableCoursesTable.setBackground(DARK_DEMO);
        availableCoursesPanel.add(new JScrollPane(availableCoursesTable));
        courseEnrollmentPanel.add(availableCoursesPanel);

        // Student’s Current Courses
        JPanel currentCoursesPanel = new JPanel();
        currentCoursesPanel.setBackground(LIGHT_LIGHT);
        JTable currentCoursesTable = new JTable(new Object[][]{},
                new String[]{"Course ID", "Course Name", "Instructor", "Credits", "Status"});
        currentCoursesPanel.add(new JScrollPane(currentCoursesTable));
        courseEnrollmentPanel.add(currentCoursesPanel);

        tabbedPane.addTab("Course Enrollment", courseEnrollmentPanel);

        // Reports Tab
        JPanel reportsPanel = new JPanel(new GridLayout(2, 1));
        reportsPanel.setBackground(LIGHT_LIGHT);

        // Generate Report
        JPanel generateReportPanel = new JPanel();
        generateReportPanel.setBackground(LIGHT_LIGHT);
        generateReportPanel.add(new JLabel("Report Type:"));
        JComboBox<String> attendanceBox = new JComboBox<>(new String[]{"Attendance Report", "Grades Report"});
        generateReportPanel.add(attendanceBox);
        attendanceBox.setBackground(DARK_DEMO);
        generateReportPanel.add(new JLabel("Date Range:"));
        generateReportPanel.add(new JTextField(15));
        JButton generateButton = new JButton("Generate");
        generateButton.setBackground(TURQUOISE);
        generateReportPanel.add(generateButton);
        reportsPanel.add(generateReportPanel);

        // Saved Reports
        JPanel savedReportsPanel = new JPanel();
        savedReportsPanel.setBackground(LIGHT_LIGHT);
        JTable savedReportsTable = new JTable(new Object[][]{},
                new String[]{"Subject", "Lecture Held", "Attended", "Total"});
        savedReportsPanel.add(new JScrollPane(savedReportsTable));
        reportsPanel.add(savedReportsPanel);

        tabbedPane.addTab("Reports", reportsPanel);

        // Settings Tab
        JPanel settingsPanel = new JPanel(new GridLayout(2, 1));
        settingsPanel.setBackground(LIGHT_LIGHT);

        // User Preferences
        JPanel userPreferencesPanel = new JPanel();
        userPreferencesPanel.setBackground(LIGHT_LIGHT);
        userPreferencesPanel.add(new JLabel("Profile Settings:"));
        userPreferencesPanel.add(new JLabel("Notification Preferences:"));
        userPreferencesPanel.add(new JCheckBox("Email Alerts"));
        settingsPanel.add(userPreferencesPanel);

        // System Configuration
        JPanel systemConfigurationPanel = new JPanel();
        systemConfigurationPanel.setBackground(LIGHT_LIGHT);
        systemConfigurationPanel.add(new JLabel("Manage Courses:"));
        JButton addButton1 = new JButton("Add/Edit/Delete Courses");
        addButton1.setBackground(TURQUOISE);
        systemConfigurationPanel.add(addButton1);
        systemConfigurationPanel.add(new JLabel("Manage Users:"));
        JButton addButton2 = new JButton("Add/Edit/Delete Users");
        addButton2.setBackground(TURQUOISE);
        systemConfigurationPanel.add(addButton2);
        settingsPanel.add(systemConfigurationPanel);

        tabbedPane.addTab("Settings", settingsPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void showHomePage() {
        SwingUtilities.invokeLater(() -> {
            ProfileManager profileManager = new ProfileManager();
            profileManager.setVisible(true);
        });
    }

    private void showStudentPage() {
        SwingUtilities.invokeLater(() -> {
            StudentRecordViewer studentRecordViewer = new StudentRecordViewer();
            studentRecordViewer.setVisible(true);
        });
    }

    private void showCoursePage() {
        SwingUtilities.invokeLater(() -> {
            CourseViewer courseViewer = new CourseViewer();
            courseViewer.setVisible(true);
        });
    }

    private void showStudentDetailsForm() {
        SwingUtilities.invokeLater(() -> {
            StudentDetailsForm studentDetailsForm = new StudentDetailsForm();
            studentDetailsForm.setVisible(true);
        });
    }

    private static class NavigationActionListener implements ActionListener {
        private final String tabName;

        public NavigationActionListener(String tabName) {
            this.tabName = tabName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Navigation button clicked: " + tabName);
            // Implement navigation logic if needed
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SchoolManagementSystem frame = new SchoolManagementSystem();
            frame.setVisible(true);
        });
    }
}
