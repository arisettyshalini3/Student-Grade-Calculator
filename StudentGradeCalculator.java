// Importing necessary libraries
import javax.swing.*;          // For GUI components
import java.awt.*;             // For layout and GUI styling
import java.awt.event.*;       // For handling button click events

// Main class which extends JFrame to create GUI window
public class StudentGradeCalculator extends JFrame implements ActionListener {

    // Declaring GUI components
    JLabel[] subjectLabels = new JLabel[5];       // Labels for 5 subjects
    JTextField[] subjectFields = new JTextField[5]; // Text fields to enter marks
    JButton btnCalculate, btnClear, btnExit;      // Buttons to perform actions
    JLabel lblTotal, lblAverage, lblGrade;        // Labels to show results

    // Constructor to set up the UI
    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");      // Window title
        setSize(400, 400);                         // Size of window
        setLayout(new GridLayout(10, 2, 5, 5));    // Layout with rows & columns
        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Exit on close

        // Add fields for 5 subjects
        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectFields[i] = new JTextField();
            add(subjectLabels[i]);                 // Add label
            add(subjectFields[i]);                 // Add text field
        }

        // Create buttons and attach event listeners
        btnCalculate = new JButton("Calculate");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        btnCalculate.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        // Add buttons to window
        add(btnCalculate);
        add(btnClear);
        add(btnExit);

        // Add empty labels for results
        lblTotal = new JLabel("Total: ");
        lblAverage = new JLabel("Average: ");
        lblGrade = new JLabel("Grade: ");

        add(lblTotal);
        add(lblAverage);
        add(lblGrade);

        setVisible(true); // Make the window visible
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            int total = 0;
            boolean valid = true;

            // Read marks from all fields
            for (int i = 0; i < 5; i++) {
                try {
                    int marks = Integer.parseInt(subjectFields[i].getText());
                    if (marks < 0 || marks > 100) {
                        throw new NumberFormatException();
                    }
                    total += marks;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Enter valid marks (0-100) in all fields.");
                    valid = false;
                    break;
                }
            }

            // If all inputs are valid, calculate and display result
            if (valid) {
                double average = total / 5.0;
                String grade;

                if (average >= 90) grade = "A";
                else if (average >= 75) grade = "B";
                else if (average >= 50) grade = "C";
                else grade = "F";

                lblTotal.setText("Total: " + total);
                lblAverage.setText("Average: " + average);
                lblGrade.setText("Grade: " + grade);
            }

        } else if (e.getSource() == btnClear) {
            // Clear all fields and reset results
            for (int i = 0; i < 5; i++) {
                subjectFields[i].setText("");
            }
            lblTotal.setText("Total: ");
            lblAverage.setText("Average: ");
            lblGrade.setText("Grade: ");

        } else if (e.getSource() == btnExit) {
            // Exit the application
            System.exit(0);
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}
