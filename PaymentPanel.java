package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PaymentPanel extends JPanel {
    private JComboBox<String> userComboBox;
    private JComboBox<String> calcComboBox;
    private JTextField paymentAmountField;
    private JTextField paymentDateField;
    private JTextField paymentMethodField;
    private JTextArea resultArea;
    private Connection connection;

    public PaymentPanel() {
        setLayout(new BorderLayout(10, 10)); // Main layout with spacing

        // Establish database connection
        connectToDatabase();

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Payment Information"));

        // User Selection
        inputPanel.add(new JLabel("Select User:"));
        userComboBox = new JComboBox<>(fetchUserNames());
        userComboBox.addActionListener(e -> updateCalcOptions());
        inputPanel.add(userComboBox);

        // Calculation Selection
        inputPanel.add(new JLabel("Select Calculation:"));
        calcComboBox = new JComboBox<>();
        inputPanel.add(calcComboBox);

        // Payment Amount
        inputPanel.add(new JLabel("Payment Amount:"));
        paymentAmountField = new JTextField();
        inputPanel.add(paymentAmountField);

        // Payment Date
        inputPanel.add(new JLabel("Payment Date (YYYY-MM-DD):"));
        paymentDateField = new JTextField();
        inputPanel.add(paymentDateField);

        // Payment Method
        inputPanel.add(new JLabel("Payment Method:"));
        paymentMethodField = new JTextField();
        inputPanel.add(paymentMethodField);

        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addPaymentButton = new JButton("Add Payment");
        addPaymentButton.addActionListener(new AddPaymentListener());
        buttonPanel.add(addPaymentButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Result Area
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Payment Records"));
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Load initial data
        updateCalcOptions();
        loadPaymentRecords();
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

    private void updateCalcOptions() {
        String selectedUser = (String) userComboBox.getSelectedItem();
        calcComboBox.removeAllItems();
        if (selectedUser != null) {
            try {
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT calc_id FROM Tax_Calculations WHERE user_id = (SELECT user_id FROM User WHERE name = ?)"
                );
                stmt.setString(1, selectedUser);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    calcComboBox.addItem(String.valueOf(rs.getInt("calc_id")));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error fetching calculations: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadPaymentRecords() {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT p.payment_id, u.name, p.calc_id, p.payment_amount, p.payment_date, p.payment_method " +
                            "FROM Payments p " +
                            "JOIN User u ON p.user_id = u.user_id"
            );
            ResultSet rs = stmt.executeQuery();
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-10s %-20s %-10s %-10s %-15s %-20s\n", "ID", "User", "Calc ID", "Amount", "Date", "Method"));
            sb.append("------------------------------------------------------------------------------------\n");
            while (rs.next()) {
                sb.append(String.format("%-10d %-20s %-10d %-10.2f %-15s %-20s\n",
                        rs.getInt("payment_id"),
                        rs.getString("name"),
                        rs.getInt("calc_id"),
                        rs.getDouble("payment_amount"),
                        rs.getString("payment_date"),
                        rs.getString("payment_method")));
            }
            resultArea.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading payment records: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class AddPaymentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedUser = (String) userComboBox.getSelectedItem();
            String selectedCalcId = (String) calcComboBox.getSelectedItem();
            try {
                double paymentAmount = Double.parseDouble(paymentAmountField.getText());
                String paymentDate = paymentDateField.getText();
                String paymentMethod = paymentMethodField.getText();

                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO Payments (user_id, calc_id, payment_amount, payment_date, payment_method) " +
                                "VALUES ((SELECT user_id FROM User WHERE name = ?), ?, ?, ?, ?)"
                );
                stmt.setString(1, selectedUser);
                stmt.setInt(2, Integer.parseInt(selectedCalcId));
                stmt.setDouble(3, paymentAmount);
                stmt.setString(4, paymentDate);
                stmt.setString(5, paymentMethod);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(PaymentPanel.this, "Payment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Reload payment records
                loadPaymentRecords();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PaymentPanel.this, "Invalid input for payment amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(PaymentPanel.this, "Error adding payment: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}