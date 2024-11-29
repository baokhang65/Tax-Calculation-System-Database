package main;

import javax.swing.*;
import java.awt.*;

public class DeductionPanel extends JPanel {
    public DeductionPanel() {
        setLayout(new BorderLayout(10, 10)); // Add spacing between sections

        // Deduction Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(new JLabel("Deduction Type:"), gbc);
        gbc.gridx = 1;
        JTextField deductionTypeField = new JTextField(15);
        formPanel.add(deductionTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Deduction Date:"), gbc);
        gbc.gridx = 1;
        JTextField deductionDateField = new JTextField(15);
        formPanel.add(deductionDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        JTextField amountField = new JTextField(15);
        formPanel.add(amountField, gbc);

        // Add, Edit, Delete Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton);
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);

        // Wrapper Panel for Form and Buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Deduction Table
        String[] columns = {"Deduction ID", "Type", "Date", "Amount"};
        JTable deductionTable = new JTable(new Object[0][4], columns); // Empty data for now
        JScrollPane tableScrollPane = new JScrollPane(deductionTable);

        // Add components to main layout
        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER); // Table in the center
        add(bottomPanel, BorderLayout.SOUTH);      // Form and buttons at the bottom
    }
}