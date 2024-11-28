package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaxCalculatorPanel extends JPanel {
    private JComboBox<String> userComboBox;
    private JTextField incomeField;
    private JTextField deductionField;
    private JTextField taxRateField;
    private JTextArea resultArea;
    private Connection connection;

    public TaxCalculatorPanel() {
        setLayout(new BorderLayout(10, 10)); // Main layout with spacing

        // Establish database connection
        connectToDatabase();

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Information"));

        // User Selection
        inputPanel.add(new JLabel("Select User:"));
        userComboBox = new JComboBox<>(fetchUserNames());
        userComboBox.addActionListener(e -> updateUserDetails());
        inputPanel.add(userComboBox);

        // Income Field
        inputPanel.add(new JLabel("Income:"));
        incomeField = new JTextField();
        incomeField.setEditable(false);
        inputPanel.add(incomeField);

        // Deduction Field
        inputPanel.add(new JLabel("Deduction:"));
        deductionField = new JTextField();
        deductionField.setEditable(false);
        inputPanel.add(deductionField);

        // Tax Rate Field
        inputPanel.add(new JLabel("Tax Rate (%):"));
        taxRateField = new JTextField();
        inputPanel.add(taxRateField);

        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton calculateButton = new JButton("Calculate Tax");
        calculateButton.addActionListener(new CalculateTaxListener());
        buttonPanel.add(calculateButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Result Area
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Tax Calculation Result"));
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Load initial user data
        updateUserDetails();
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tax_calculator_db", "root", "password");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] fetchUserNames() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT name FROM User");
            ResultSet rs = stmt.executeQuery();
            rs.last();
            String[] userNames = new String[rs.getRow()];
            rs.beforeFirst();
            int index = 0;
            while (rs.next()) {
                userNames[index++] = rs.getString("name");
            }
            return userNames;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error fetching users: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return new String[0];
        }
    }

    private void updateUserDetails() {
        String selectedUser = (String) userComboBox.getSelectedItem();
        if (selectedUser != null) {
            try {
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT i.amount AS income, d.amount AS deduction " +
                                "FROM Income_Details i " +
                                "LEFT JOIN Deductions d ON i.user_id = d.user_id " +
                                "WHERE i.user_id = (SELECT user_id FROM User WHERE name = ?)"
                );
                stmt.setString(1, selectedUser);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    incomeField.setText(String.valueOf(rs.getDouble("income")));
                    deductionField.setText(String.valueOf(rs.getDouble("deduction")));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error fetching user details: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class CalculateTaxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double income = Double.parseDouble(incomeField.getText());
                double deduction = Double.parseDouble(deductionField.getText());
                double taxRate = Double.parseDouble(taxRateField.getText());

                // Calculate taxable income and total tax
                double taxableIncome = income - deduction;
                double totalTax = taxableIncome * (taxRate / 100);

                // Display result
                resultArea.setText(String.format(
                        "Tax Calculation Details:\n" +
                                "Income: %.2f\n" +
                                "Deduction: %.2f\n" +
                                "Taxable Income: %.2f\n" +
                                "Tax Rate: %.2f%%\n" +
                                "Total Tax: %.2f",
                        income, deduction, taxableIncome, taxRate, totalTax
                ));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TaxCalculatorPanel.this,
                        "Please enter a valid numeric value for Tax Rate.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
