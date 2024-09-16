import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class CourseViewer extends JFrame {
    private static final Color lightlight = new Color(255, 229, 236);
    private static final Color green1 = new Color(128, 141, 124);
    private static final Color green2 = new Color(89, 116, 69);
    private static final Color green3 = new Color(101, 129, 71);
    private static final Color green4 = new Color(114, 151, 98);

    public CourseViewer() {
        setTitle("Course Viewer");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(lightlight);

        // Course Table
        String[] columnNames = {"Course ID", "Course Name", "Completion (%)"};
        Object[][] data = {
            {"C101", "JAVA", 85},
            {"C102", "C++", 60},
            {"C103", "Oracle", 80},
            {"C104", "Shift", 70},
        };
        JTable courseTable = new JTable(data, columnNames);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        // Progress Circle Panel
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new GridLayout(2, 2));

        Color[] colors = {green1, green2, green3, green4};

        for (int i = 0; i < data.length; i++) {
            String courseName = (String) data[i][1];
            int completion = (Integer) data[i][2];
            progressPanel.add(new CoursePanel(courseName, completion, colors[i % colors.length]));
        }

        add(progressPanel, BorderLayout.EAST);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> dispose());
        add(exitButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseViewer frame = new CourseViewer();
            frame.setVisible(true);
        });
    }
}

class CoursePanel extends JPanel {
    private final String courseName;
    private final int completion;
    private final Color color;

    public CoursePanel(String courseName, int completion, Color color) {
        this.courseName = courseName;
        this.completion = completion;
        this.color = color;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 200));

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(completion);
        progressBar.setStringPainted(true);
        progressBar.setFont(progressBar.getFont().deriveFont(16f));
        progressBar.setUI(new ProgressCircleUI(color));

        add(progressBar, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel(courseName, SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(14f));
        add(nameLabel, BorderLayout.SOUTH);
    }
}

class ProgressCircleUI extends BasicProgressBarUI {
    private final Color progressColor;

    public ProgressCircleUI(Color progressColor) {
        this.progressColor = progressColor;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        int v = Math.max(d.width, d.height);
        d.setSize(v, v);
        return d;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        JProgressBar progressBar = (JProgressBar) c;
        Rectangle rect = SwingUtilities.calculateInnerArea(progressBar, null);
        if (rect.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double start = 90d;
        double degree = 360d * progressBar.getPercentComplete();
        double sz = Math.min(rect.width, rect.height);
        double cx = rect.getCenterX();
        double cy = rect.getCenterY();
        double or = sz * .5;
        double ir = or * .5;
        Shape inner = new Ellipse2D.Double(cx - ir, cy - ir, ir * 2d, ir * 2d);
        Shape outer = new Ellipse2D.Double(cx - or, cy - or, sz, sz);
        Shape sector = new Arc2D.Double(cx - or, cy - or, sz, sz, start, degree, Arc2D.PIE);

        Area foreground = new Area(sector);
        Area background = new Area(outer);
        Area hole = new Area(inner);

        foreground.subtract(hole);
        background.subtract(hole);

        g2.setPaint(new Color(0xDD_DD_DD));
        g2.fill(background);

        g2.setPaint(progressColor);
        g2.fill(foreground);

        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        String text = progressBar.getString();
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g2.drawString(text, (int) (cx - textWidth / 2), (int) (cy + textHeight / 2));

        g2.dispose();
    }
}
