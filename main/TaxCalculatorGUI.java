package main;

import javax.swing.*;

public class TaxCalculatorGUI extends JFrame {
    private MainPanel mainPanel;

    public TaxCalculatorGUI() {
        setTitle("Tax Calculator Database System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new MainPanel(this);
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaxCalculatorGUI frame = new TaxCalculatorGUI();
            frame.setVisible(true);
        });
    }
}

