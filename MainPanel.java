package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainPanel extends JPanel {
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private TaxCalculatorGUI parentFrame;

    public MainPanel(TaxCalculatorGUI parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        // Navigation Panel
        navigationPanel = new JPanel(new GridLayout(6, 1));
        navigationPanel.setPreferredSize(new Dimension(150, 0));
        add(navigationPanel, BorderLayout.WEST);

        // Add navigation buttons
        String[] buttons = {"Users", "Income", "Deductions", "Tax Calculator", "Payments", "Audit Logs"};
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new NavigationButtonListener());
            navigationPanel.add(button);
        }

        // Content Panel
        contentPanel = new JPanel();
        add(contentPanel, BorderLayout.CENTER);

        // Set initial content panel
        showUsersPanel();
    }

    private void showUsersPanel() {
        UserPanel userPanel = new UserPanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(userPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showIncomePanel() {
        IncomePanel incomePanel = new IncomePanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(incomePanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showDeductionPanel() {
        DeductionPanel deductionPanel = new DeductionPanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(deductionPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showTaxCalculatorPanel() {
        TaxCalculatorPanel taxCalculatorPanel = new TaxCalculatorPanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(taxCalculatorPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showPaymentPanel() {
        PaymentPanel paymentPanel = new PaymentPanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(paymentPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private class NavigationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Users":
                    showUsersPanel();
                    break;
                case "Income":
                    showIncomePanel();
                    break;
                case "Deductions":
                    showDeductionPanel();
                    break;
                case "Tax Calculator":
                    showTaxCalculatorPanel();
                    break;
                case "Payments":
                    showPaymentPanel();
                    break;
                // Add cases for other panels like Audit Logs, etc.
                default:
                    contentPanel.removeAll();
                    contentPanel.add(new JLabel("Feature under development..."));
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    break;
            }
        }
    }
}