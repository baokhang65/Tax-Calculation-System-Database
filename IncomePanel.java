package main;

import javax.swing.*;
import java.awt.*;

public class IncomePanel extends JPanel {
    public IncomePanel() {
        setLayout(new BorderLayout(10, 10)); // Add spacing between sections

        // Income Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the form

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(new JLabel("Income Type:"), gbc);
        gbc.gridx = 1;
        JTextField incomeTypeField = new JTextField(15);
        formPanel.add(incomeTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        JTextField amountField = new JTextField(15);
        formPanel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Income Level:"), gbc);
        gbc.gridx = 1;
        JTextField incomeLevelField = new JTextField(15);
        formPanel.add(incomeLevelField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Income Date:"), gbc);
        gbc.gridx = 1;
        JTextField incomeDateField = new JTextField(15);
        formPanel.add(incomeDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Source:"), gbc);
        gbc.gridx = 1;
        JTextField sourceField = new JTextField(15);
        formPanel.add(sourceField, gbc);

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

        // Income Table
        String[] columns = {"Income ID", "Type", "Amount", "Level", "Date", "Source"};
        JTable incomeTable = new JTable(new Object[0][6], columns); // Empty data for now
        JScrollPane tableScrollPane = new JScrollPane(incomeTable);

        // Add components to main layout
        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER); // Table in the center
        add(bottomPanel, BorderLayout.SOUTH);      // Form and buttons at the bottom
    }
}