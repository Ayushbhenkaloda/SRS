import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StudentRecordViewer extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(240, 248, 255); 
    private static final Color TABLE_COLOR = new Color(255, 229, 236);
    private static final Color random= new Color(199, 91, 122);
       private JTable studentTable;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public StudentRecordViewer() {
        setTitle("Student Record Viewer");
        setSize(1500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        String[] columnNames = {"Student ID", "Student Name", "Fees", "Status", "Defaulter Status"};
        Object[][] data = {
            {23104037, "Prathamesh Bhoir", "53k", "Pending", "No"},
            {23104038, "Sarvesh Chudji", "1.18l", "Paid", "Yes"},
            {23104039, "Neha", "1.18L", "Paid", "No"},
            {23104036, "Atharva", "1.18L", "Paid", "Yes"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        studentTable = new JTable(model);
        rowSorter = new TableRowSorter<>(model);
        studentTable.setRowSorter(rowSorter);
        studentTable.setPreferredScrollableViewportSize(new Dimension(750, 300));
        studentTable.setFillsViewportHeight(true);
        studentTable.setBackground(Color.WHITE); 

        // Apply custom cell renderer
        studentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 3) { // Assuming column 3 is "Status"
                    String status = (String) value;
                    if ("Pending".equalsIgnoreCase(status)) {
                        cell.setBackground(random); 
                    } else {
                        cell.setBackground(TABLE_COLOR); 
                    }
                } else {
                    cell.setBackground(TABLE_COLOR); 
                }
                return cell;
            }
        });

        TableColumn column;
        for (int i = 0; i < studentTable.getColumnCount(); i++) {
            column = studentTable.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(200); 
                    break;
                case 1:
                    column.setPreferredWidth(200); 
                    break;
                case 2:
                    column.setPreferredWidth(100); 
                    break;
                case 3:
                    column.setPreferredWidth(150); 
                    break;
                case 4:
                    column.setPreferredWidth(150); 
                    break;
            }
        }

        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        JTextField searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            private void search(String query) {
                if (query.isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
                }
            }
        });

        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);

        add(searchPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentRecordViewer frame = new StudentRecordViewer();
            frame.setVisible(true);
        });
    }
}
