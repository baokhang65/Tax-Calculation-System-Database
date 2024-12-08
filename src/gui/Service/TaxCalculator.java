package gui.Service;
import gui.SQLServerConnection;
import java.sql.*;
public class TaxCalculator {
    private long grossIncome;
    private long StaffID;
    // Define the limits for the insurances
    private static final long SOCIAL_INSURANCE_LIMIT = 3744000;
    private static final long HEALTH_INSURANCE_LIMIT = 702000;
    private static final long UNEMPLOYMENT_INSURANCE_LIMIT = 992000;

    public TaxCalculator(long grossIncome, int staffID) {
        this.grossIncome = grossIncome;
        this.StaffID = staffID;
    }
    public TaxCalculator(long grossIncome){
        this.grossIncome = grossIncome;
    }
    public long getSocialInsurance() {
        long socialInsurance = (long) (grossIncome * 0.08);
        return Math.min(socialInsurance, SOCIAL_INSURANCE_LIMIT);
    }

    public long getHealthInsurance() {
        long healthInsurance = (long) (grossIncome * 0.015);
        return Math.min(healthInsurance, HEALTH_INSURANCE_LIMIT);
    }

    public long getUnemploymentInsurance() {
        long unemploymentInsurance = (long) (grossIncome * 0.01);
        return Math.min(unemploymentInsurance, UNEMPLOYMENT_INSURANCE_LIMIT);
    }

    public long getPreTaxIncome() {
        return grossIncome - getSocialInsurance() - getHealthInsurance() - getUnemploymentInsurance();
    }
    
    public long getDependentDeductionAmount() throws SQLException, ClassNotFoundException {
        Connection conn = SQLServerConnection.getConnection();

        String sql = "SELECT SUM(ds.Number_of_times_deducted*d.deduction_amount) AS dependentDeduction " +
                    "FROM Deduction_For_Family_Circumstance_Staffs ds " +
                    "JOIN Deductions d ON d.deduction_id = ds.deduction_id " +
                    "WHERE d.deduction_id = 2 AND ds.Staff_id = ? ";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, this.StaffID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("dependentDeduction");
            } else {
                return 0; 
            }
        }
    }
    
    public long getPersonalDeductionAmount() throws SQLException, ClassNotFoundException {
        Connection conn = SQLServerConnection.getConnection();

        String sql = "SELECT SUM(ds.Number_of_times_deducted * d.deduction_amount) AS personalDeduction " +
                "FROM Deduction_For_Family_Circumstance_Staffs ds " +
                "JOIN Deductions d ON ds.deduction_id = d.deduction_id " +
                "WHERE ds.staff_id = ? AND d.deduction_id = 1 ";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, this.StaffID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("personalDeduction");
            } else {
                return 0; 
            }
        }
    }
    
    public long getTaxableIncome() throws SQLException, ClassNotFoundException {
        long preTaxIncome = getPreTaxIncome();
        return Math.max(preTaxIncome - getPersonalDeductionAmount() - getDependentDeductionAmount(), 0);
    }

    public long[] calculateTaxBrackets() throws SQLException, ClassNotFoundException {
        long taxableIncome = getTaxableIncome();
        long[] taxBrackets = {5000000, 5000000, 8000000, 14000000, 20000000, 28000000, Long.MAX_VALUE};
        double[] taxRates = {0.05, 0.10, 0.15, 0.20, 0.25, 0.30, 0.35};
        long[] bracketTaxValues = new long[taxRates.length];
        long remainingTaxableIncome = taxableIncome;

        for (int i = 0; i < taxBrackets.length && remainingTaxableIncome > 0; i++) {
            long incomeInBracket = Math.min(remainingTaxableIncome, taxBrackets[i]);
            bracketTaxValues[i] = (long) (incomeInBracket * taxRates[i]);
            remainingTaxableIncome -= incomeInBracket;
        }

        return bracketTaxValues;
    }

    public long getFinalTax() throws SQLException, ClassNotFoundException {
        long[] bracketTaxValues = calculateTaxBrackets();
        long finalTax = 0;
        for (long taxValue : bracketTaxValues) {
            finalTax += taxValue;
            
        }
        return finalTax;
    }

    // Thêm phương thức getter cho grossIncome
    public long getGrossIncome() {
        return grossIncome;
    }
    public long getStaffId(){
        return StaffID;
    }
}
