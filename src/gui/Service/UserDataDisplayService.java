package gui.Service;

import gui.SQLServerConnection;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class UserDataDisplayService {

    public DefaultTableModel getUserData(long staffID) throws SQLException, ClassNotFoundException {
        Connection conn = SQLServerConnection.getConnection();

        // Truy vấn thông tin từ staff và Income_Details
        String query = "SELECT s.staff_id, s.name, s.phone, s.email, i.amount " +
                "FROM Staffs s JOIN Income_Details i ON s.staff_id = i.staff_id " +
                "WHERE s.staff_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setLong(1, staffID);
        ResultSet rs = pstmt.executeQuery();

        // Chuẩn bị model cho bảng
        DefaultTableModel model = new DefaultTableModel(new String[]{"Staff ID", "Name", "Phone", "Email", "Salary", "Calculated tax"}, 0);

        if (rs.next()) {
            int sid = rs.getInt("staff_id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            long amount = rs.getLong("amount");

            // Tính thuế bằng TaxCalculator
            TaxCalculator taxCalculator = new TaxCalculator(amount,sid);
            long calculatedTax = taxCalculator.getFinalTax();

            model.addRow(new Object[]{sid, name, phone, email, amount, calculatedTax});
        }

        rs.close();
        pstmt.close();
        conn.close();

        return model;
    }
}


