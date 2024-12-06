package gui;

import logic.TaxCalculator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxDetailsPanel extends JPanel {
    private TaxCalculator taxCalculator;

    public TaxDetailsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Main title
        JLabel titleLabel = new JLabel("Personal Income Tax Details (VND)", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 177, 79));
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        // Personal income tax details table
        long grossSalary = 20000000;
        this.taxCalculator = new TaxCalculator(grossSalary);
        String[][] taxData = {
                {"GROSS Salary", String.format("%,d", grossSalary)},
                {"Social Insurance (8%)", String.format("%,d", taxCalculator.getSocialInsurance())},
                {"Health Insurance (1.5%)", String.format("%,d", taxCalculator.getHealthInsurance())},
                {"Unemployment Insurance (1%)", String.format("%,d", taxCalculator.getUnemploymentInsurance())},
                {"Pre-tax Income", String.format("%,d", taxCalculator.getPreTaxIncome())},
                {"Personal Deduction", String.format("%,d", taxCalculator.getPersonalDeductions())},
                {"Taxable Income", String.format("%,d", taxCalculator.getTaxableIncome())},
                {"Personal Income Tax (*)", String.format("%,d", taxCalculator.getFinalTax())}
        };
        String[] columnNames = {"Description", "Amount (VND)"};
        JTable taxTable = new JTable(taxData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        taxTable.setSelectionBackground(Color.WHITE);
        taxTable.setSelectionForeground(Color.BLACK);
        taxTable.setBackground(Color.WHITE);
        taxTable.setForeground(Color.BLACK);
        taxTable.setGridColor(Color.GRAY); // Set grid color to distinguish cells
        taxTable.setShowGrid(true); // Enable grid lines to separate cells
        taxTable.setRowHeight(25); // Set row height to make cells larger

        // Center align the content of the table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < taxTable.getColumnCount(); i++) {
            taxTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Bold and change the color of the table header to white
        JTableHeader header = taxTable.getTableHeader();
        taxTable.getTableHeader().setReorderingAllowed(false);
        taxTable.getTableHeader().setResizingAllowed(false);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(0, 177, 79));
        header.setForeground(Color.WHITE);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(taxTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(600, 220)); // Remove borders to eliminate white spaces

        // Tax rate details table
        long[] taxBrackets = taxCalculator.calculateTaxBrackets();
        String[][] rateData = {
                {"Up to 5 million VND", "5%", String.format("%,d", taxBrackets[0])},
                {"Over 5 million VND to 10 million VND", "10%", String.format("%,d", taxBrackets[1])},
                {"Over 10 million VND to 18 million VND", "15%", String.format("%,d", taxBrackets[2])},
                {"Over 18 million VND to 32 million VND", "20%", String.format("%,d", taxBrackets[3])},
                {"Over 32 million VND to 52 million VND", "25%", String.format("%,d", taxBrackets[4])},
                {"Over 52 million VND to 80 million VND", "30%", String.format("%,d", taxBrackets[5])},
                {"Over 80 million VND", "35%", String.format("%,d", taxBrackets[6])}
        };
        String[] rateColumnNames = {"Tax Bracket", "Tax Rate", "Amount Due"};
        JTable rateTable = new JTable(rateData, rateColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        rateTable.setSelectionBackground(Color.WHITE);
        rateTable.setSelectionForeground(Color.BLACK);
        rateTable.setBackground(Color.WHITE);
        rateTable.setForeground(Color.BLACK);
        rateTable.setGridColor(Color.GRAY); // Set grid color to distinguish cells
        rateTable.setShowGrid(true); // Enable grid lines to separate cells
        rateTable.setRowHeight(28); // Set row height to make cells larger

        // Center align the content of the tax rate table cells
        for (int i = 0; i < rateTable.getColumnCount(); i++) {
            rateTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Bold and change the color of the tax rate table header to white
        JTableHeader rateHeader = rateTable.getTableHeader();
        rateTable.getTableHeader().setReorderingAllowed(false);
        rateTable.getTableHeader().setResizingAllowed(false);
        rateHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rateHeader.setBackground(new Color(0, 177, 79));
        rateHeader.setForeground(Color.WHITE);
        DefaultTableCellRenderer rateHeaderRenderer = (DefaultTableCellRenderer) rateHeader.getDefaultRenderer();
        rateHeaderRenderer.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane rateScrollPane = new JScrollPane(rateTable);
        rateScrollPane.getViewport().setBackground(Color.WHITE);
        rateScrollPane.setBorder(BorderFactory.createEmptyBorder());
        rateScrollPane.setPreferredSize(new Dimension(600, 230)); // Remove borders to eliminate white spaces

        // Panel containing both tables
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createEmptyBorder()); // Remove borders to eliminate white spaces
        tablePanel.add(scrollPane);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between tables
        tablePanel.add(rateScrollPane);

        add(tablePanel, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 50));
        backButton.setBackground(new Color(0, 177, 79));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "viewCard");
            }
        });

        // Panel containing the Back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
