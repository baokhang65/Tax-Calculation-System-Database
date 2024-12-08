package gui.Service;

import gui.SQLServerConnection;
import java.sql.*;

public class UserIncomeUpdateService {
    public boolean updateIncome(long staff_id, long newAmount) throws SQLException, ClassNotFoundException {
        Connection conn = SQLServerConnection.getConnection();
        String updateQuery = "UPDATE Income_Details SET amount = ? WHERE Staff_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(updateQuery);
        pstmt.setLong(1, newAmount);
        pstmt.setLong(2, staff_id);

        int rows = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return rows > 0;
    }
    public void updateTax(long staff_id, double newAmount) throws SQLException, ClassNotFoundException {
        Connection conn = SQLServerConnection.getConnection();
        String updateQuery = "UPDATE Tax_Calculations SET total_tax = ? WHERE Staff_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(updateQuery);
        pstmt.setDouble(1, newAmount);
        pstmt.setLong(2, staff_id);

        int rows = pstmt.executeUpdate();

        pstmt.close();
        conn.close();

    }
}


