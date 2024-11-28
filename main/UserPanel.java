package main;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    public UserPanel() {
        setLayout(new BorderLayout(10, 10)); // Add spacing between main sections

        // User Form with added spacing
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the form

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add space between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        JTextField addressField = new JTextField(20);
        formPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Area Code:"), gbc);
        gbc.gridx = 1;
        JTextField areaCodeField = new JTextField(20);
        formPanel.add(areaCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        JTextField phoneNumberField = new JTextField(20);
        formPanel.add(phoneNumberField, gbc);

        // Table to display users
        String[] columns = {"Name", "Email", "Address", "Area Code", "Phone Number"};
        JTable userTable = new JTable(new Object[0][5], columns);
        JScrollPane tableScrollPane = new JScrollPane(userTable);

        // Add, Edit, Delete buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton);
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);

        // Add components to the panel
        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

