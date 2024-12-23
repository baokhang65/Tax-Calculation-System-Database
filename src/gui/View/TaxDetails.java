/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.View;
import gui.Service.TaxCalculator;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.sql.SQLException;

/**
 *
 * @author LAPTOP
 */
public class TaxDetails extends javax.swing.JPanel {
    private TaxCalculator taxCalculator;

    /**
     * Creates new form TaxDetails
     */
    public TaxDetails() {
        initComponents();
        myIniComponents();
        
    }
    public void updateTaxDetails(TaxCalculator taxCalculator) throws SQLException, ClassNotFoundException {
        this.taxCalculator = taxCalculator;

        // Cập nhật bảng jTable1
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        model1.setValueAt(String.format("%,d", taxCalculator.getGrossIncome()), 0, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getSocialInsurance()), 1, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getHealthInsurance()), 2, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getUnemploymentInsurance()), 3, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getPreTaxIncome()), 4, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getPersonalDeductionAmount()), 5, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getDependentDeductionAmount()), 6, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getTaxableIncome()), 7, 1);
        model1.setValueAt(String.format("%,d", taxCalculator.getFinalTax()), 8, 1);

        // Cập nhật bảng jTable2
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        long[] taxBrackets = taxCalculator.calculateTaxBrackets();
        for (int i = 0; i < taxBrackets.length && i < model2.getRowCount(); i++) {
            model2.setValueAt(String.format("%,d", taxBrackets[i]), i, 2);
        }
    }
        // Phương thức để lấy panel TaxDetails từ Main
    

    public void myIniComponents(){
        jTable1.getTableHeader().setBackground(new java.awt.Color(204,204,204));
        jTable1.getTableHeader().setForeground(new java.awt.Color(102,102,102));
        jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jTable2.getTableHeader().setBackground(new java.awt.Color(204,204,204));
        jTable2.getTableHeader().setForeground(new java.awt.Color(102,102,102));
        jTable2.getTableHeader().setFont(new java.awt.Font("Segoe UI Semibold", 0, 18));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jCheckBox1.setText("jCheckBox1");

        setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(102, 102, 102));
        jButton3.setText("Back");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 51, 0));
        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 177, 79));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Personal Income Details (VND)");

        jScrollPane2.setBackground(new java.awt.Color(0, 177, 79));

        jTable2.setBackground(new java.awt.Color(255, 255, 255));
        jTable2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(102, 102, 102));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Up to 5 million VND", "5%", null},
                {"Over 5 million to 10 million VND", "10%", null},
                {"Over 10 million to 18 million VND", "15%", null},
                {"Over 18 million to 32 million VND", "20%", null},
                {"Over 32 million to 52 million VND", "25%", null},
                {"Over 52 million to 80 million VND", "30%", null},
                {"Over 80 million VND", "35%", null}
            },
            new String [] {
                "Tax Bracket", "Tax Rate", "Amount Due"
            }
        ));
        jTable2.setRowHeight(25);
        jTable2.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(240);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(100000);
            jTable2.getColumnModel().getColumn(1).setMinWidth(90);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(1000);
        }

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(102, 102, 102));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"GROSS Salary", null},
                {"Social Insurance (8%)", null},
                {"Health Insurance (1.5%)", null},
                {"Unemployment Insurance (1%)", null},
                {"Pre-tax Income", null},
                {"Personal Deduction", null},
                {"Dependent Deduction", ""},
                {"Taxable Income", null},
                {"Personal Income Tax (*)", null}
            },
            new String [] {
                "Description", "Amount"
            }
        ));
        jTable1.setToolTipText("");
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setRowHeight(25);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setShowGrid(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) (this.getParent().getLayout());
        cardLayout.show(this.getParent(), "viewCard");
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
